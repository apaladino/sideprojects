var Registrant = require('../models/registrant');
var Event = require('../models/event');
var LinkedInProfile = require('../models/linkedInProfile');
var LinkedInCompanyProfile = require('../models/linkedInCompanyProfile');
var expectedState = 'AP2014Maine';
var API_KEY = '750c8k09ptha6k';
var SECRET_KEY = 'ECInL187WE272dAa';
var request = require('request');
var regIncludes = 'events linkedInProfile';

function createNewRegistrant(firstName, lastName, email) {
    return new Registrant({
        firstname: firstName,
        lastName: lastName,
        email: email
    });
}

function createNewEvent(registrantKey, eventId, eventTitle, startTime, endTime) {
    return new Event({
        _registrantKey: registrantKey,
        eventId: eventId,
        eventTitle: eventTitle,
        startTime: startTime,
        endTime: endTime
    });
}

function createNewLinkedInProfile(registrantKey, profile) {
    return new LinkedInProfile({
        _registrantKey: registrantKey,
        firstName: profile.firstName,
        lastName: profile.lastName,
        emailAddress: profile.emailAddress,
        pictureUrl: profile.pictureUrl,
        industry: profile.industry,
        summary: profile.summary
    });
}

function createLinkedInPositions(profileKey, profile) {

    var positions = profile.positions.values;
    var linkedInPositions = [];

    var companyIds = [];

    for (var i = 0; i < positions.length; i++) {

        // check for duplicate company id

        console.log("Position: " + JSON.stringify(positions[i].company));

        var companyProfile = new LinkedInCompanyProfile({
            _profileKey: profileKey,
            industry: positions[i].company.industry,
            name: positions[i].company.name,
            size: positions[i].company.size,
            ticker: positions[i].company.ticker,
            type: positions[i].company.type,
            isCurrent: positions[i].isCurrent,
            startDate: positions[i].startDate,
            title: positions[i].title,
            summary: positions[i].summary
        });
        console.log("Saving profile");

        companyProfile.save(function (err) {
            if (err) {
                console.log("Unable to save company profile. " + err);
                return;
            }

        });

        console.log("company profile saved. " + JSON.stringify(companyProfile));
        linkedInPositions.push(companyProfile);

        break;

    }

    return linkedInPositions;
}

exports.registerWithLinkedIn = function (req, res) {
    console.log("POST '/registrant/linkedin " + JSON.stringify(req.body));

    var eventId = req.body.eventId;
    var eventTitle = req.body.eventTitle;
    var eventStartTime = req.body.eventStartTime;
    var eventEndTime = req.body.eventEndTime;
    var firstName = req.body.firstName;
    var lastName = req.body.lastName;
    var email = req.body.email;
    var pictureUrl = req.body.pictureUrl;
    var profile = req.body.profile;

    res.send("event: " + eventId + "<br/>" + JSON.stringify(profile));

    Registrant.findOne({
        'email': email
    }).populate(regIncludes).exec(function (err, registrant) {
                if (err) {
                    res.send("Unexpected error: " + err);
                }

                if ((!registrant) || typeof registrant == "undefined") {
                    // not found
                    console.log("Registrant: " + email + " not found. Creating new Registrant Schema.");

                    var newRegistrant = createNewRegistrant(firstName, lastName, email);

                    newRegistrant.save(function (err) {
                        if (err) {
                            console.log("Error saving registrant. Error: " + err);

                            res.send("Error: unable to save registrant: " + email + ".");
                            return;
                        }

                        // new registrant saved, now save their linkedIn profile
                        var theirProfile = createNewLinkedInProfile(newRegistrant._id, profile);
                        theirProfile.save(function (err) {
                            if (err) {
                                console.log("Unable to save linkedIn profile. Error: " + err);
                                res.send("Error: Unable to persist LinkedIn profile.");
                                return;
                            }
                        });

                        console.log("Created new Profile " + JSON.stringify(theirProfile));

                        // linkedIn profile saved, add company profiles
                        var positions = createLinkedInPositions(theirProfile._id, profile);
                        console.log("positions created: " + JSON.stringify(positions));
                        theirProfile.positions = positions;
                        theirProfile.save();
                        newRegistrant.linkedInProfile = theirProfile;

                        if (eventId && eventTitle) {
                            // optional event information supplied.
                            var newEvent = createNewEvent(newRegistrant._id, eventId, eventTitle, eventStartTime,
                                    eventEndTime);

                            // save new event
                            console.log("Creating new event. ");
                            newEvent.save(function (err) {
                                if (err) {
                                    console.log("Error saving new event for registrant " + email + ". Error: " + err);
                                    res.send("Error: registrant saved, but unable to add event.");
                                    return;
                                }
                            });

                            newRegistrant.events.push(newEvent);
                            newRegistrant.save();
                        }

                        console.log("new registrant created.");

                        res.send("Registrant " + email + " successfully added.");

                    });

                } else {
                    console.log("Registrant: " + email + " already exists. " +
                            JSON.stringify(registrant.linkedInProfile));

                    if (!eventId) {
                        res.send("Error: User: " + email + " already exists.");
                        return;
                    }

                    // check to see if user already has registered for this particular event
                    var isRegistered = false;
                    if (registrant.events && registrant.events.length > 0) {
                        for (var i = 0; i < registrant.events.length; i++) {
                            if (registrant.events[i].eventId === eventId) {
                                isRegistered = true;
                                break;
                            }
                        }
                    }

                    if (!isRegistered) {
                        //
                        // look for existing event with same eventId and associate new user with
                        // this event
                        //

                        // TODO: Event.findOne({ 'eventId' : eventId}).

                        // optional event information supplied.
                        console.log("creating new event.");
                        var newEvent = new Event({
                            _registrantKey: registrant._key,
                            eventId: eventId,
                            eventTitle: eventTitle,
                            startTime: eventStartTime,
                            endTime: eventEndTime
                        });

                        // save new event
                        newEvent.save(function (err) {
                            if (err) {
                                console.log("Error saving new event for registrant " + email + ". Error: " + err);
                                res.send("Error: registrant saved, but unable to add event.");
                                return;
                            }

                        });

                        registrant.events.push(newEvent);

                        // add linked in profile
                        var theirProfile = new LinkedInProfile({
                            _registrantKey: registrant._key,
                            profile: profile
                        });
                        theirProfile.save(function (err) {
                            if (err) {
                                console.log("Error saving linked in profile. Error: " + err);
                                res.send("Error: registrant saved, but unable to add linkedIn profile");
                                return;
                            }

                            console.log("Event created successfully.");

                            if (!registrant.linkedInProfile && theirProfile) {
                                console.log("Linked in profile is missing from user. Updating their linkedIn profile");
                                registrant.linkedInProfile = theirProfile;
                            }

                            registrant.save();
                            res.send("Registrant: " + email + " successfully registered.");

                        });

                    } else {
                        res.send("User " + email + " already registered for this event.");
                        return;
                    }
                }
            });

};

exports.registerLinkedInUser = function (req, res) {
    res.send("registerLinkedInUser: " + JSON.stringify(req.query));
};

/*
 * authenticate linked in user and get access token.
 */
exports.authenticateLinkedInUser = function (req, res) {

    console.log("Register via linked request recieved. " + JSON.stringify(req.query));
    var code = req.query.code;
    var state = req.query.state;
    var error = req.query.error;
    var errorDescription = req.query.error_description;

    var accessToken = req.query.access_token;

    // {"expires_in":5184000,"access_token":"AQXdSP_W41_UPs5ioT_t8HESyODB4FqbkJ8LrV_5mff4gPODzOYR"}
    if (error) {
        res.send("Error: " + errorDescription);
    }

    if (!accessToken) {
        //
        // Get Authorization Code
        //
        if (!state || !(state === expectedState)) {
            console.log("State does not match. Possible CRF attack.");
            // TODO: message the user.
        }

        console.log("User authorized request. Attempting to get access_token for REST API calls.");

        var path = '/uas/oauth2/accessToken?grant_type=authorization_code';
        path = path + '&code=' + code + '&redirect_uri=http://localhost:8000/rest/linkedin/authenticate';
        path = path + '&client_id=' + API_KEY + '&client_secret=' + SECRET_KEY;
        var url = 'https://www.linkedin.com' + path;
        console.log("POST " + url);

        request.post(url, function (error, response, body) {
            if (error) {
                console.log("error trying to post: " + error);
            }
            if (!error && response.statusCode == 200) {
                console.log(body);

            }
            res.send(body);
        });
    } else {
        //
        // GOT Access Token
        //
        res.send(JSON.stringify(req.params));
    }

}

exports.getRegistrantByID = function (req, res) {
    console.log("GET /registrant/reg_id " + req.params.reg_id);

    var regId = req.params.reg_id;

    Registrant.findOne({
        '_id': regId
    }).populate(regIncludes).exec(function (err, registrant) {
                if (err) {
                    res.send("Unexpected error: " + err);
                }

                console.log("registrant: " + JSON.stringify(registrant));

                if ((!registrant) || typeof registrant == "undefined") {
                    res.status(404);
                    res.send("Error: Registrant: " + email + " not found.");
                } else {
                    res.status(200);
                    res.send(JSON.stringify(registrant));
                }

            });
}

exports.getRegistrant = function (req, res) {

    console.log("GET /registrant " + JSON.stringify(req.query));

    var email = req.query.email;

    Registrant.findOne({
        'email': email
    }).populate(regIncludes).exec(function (err, registrant) {
                if (err) {
                    res.send("Unexpected error: " + err);
                }

                console.log("registrant: " + JSON.stringify(registrant));

                if ((!registrant) || typeof registrant == "undefined") {
                    res.status(404);
                    res.send("Error: Registrant: " + email + " not found.");
                } else {
                    res.status(200);
                    res.send(JSON.stringify(registrant));
                }

            });
}

exports.addRegistrant = function (req, res) {

    console.log("POST /registrants " + JSON.stringify(req.body));

    var firstName = req.body.firstName;
    var lastName = req.body.lastName;
    var email = req.body.email;

    //
    // Validate registrant information
    //
    if (!firstName || typeof firstName == "undefined") {
        res.send("Error: Registrant first name is missing.");
        return;
    }

    if (!lastName || typeof lastName == "undefined") {
        res.send("Error: Registrant last name is missing.");
        return;
    }

    if (!email || typeof email == "undefined") {
        res.send("Error: Registrant email is missing.");
        return;
    }

    //
    // Validate optional event information
    //
    var eventId = req.body.eventId;
    var eventTitle = req.body.eventTitle;
    var eventStartTime = req.body.eventStartTime;
    var eventEndTime = req.body.eventEndTime;

    if ((eventId || eventTitle || eventStartTime || eventEndTime) &&
            (!eventId || !eventTitle || !eventStartTime || !eventEndTime)) {
        res.send("Error: All optional event information must be supplied.");
        return;
    }

    // look up registrant by email.
    Registrant.findOne({
        'email': email
    }, function (err, registrant) {
        if (err) {
            res.send("Unexpected error: " + err);
        }

        console.log("registrant: " + JSON.stringify(registrant));

        if ((!registrant) || typeof registrant == "undefined") {
            // not found
            console.log("Registrant: " + email + " not found. Creating new Registrant Schema.");

            var newRegistrant = new Registrant({
                firstname: firstName,
                lastName: lastName,
                email: email
            });

            newRegistrant.save(function (err) {
                if (err) {
                    console.log("Error saving registrant. Error: " + err);
                    res.send("Error: unable to save registrant: " + email + ".");
                    return;
                }
            });

            if (eventId) {
                // optional event information supplied.
                var newEvent = new Event({
                    _registrantKey: newRegistrant._key,
                    eventId: eventId,
                    eventTitle: eventTitle,
                    startTime: eventStartTime,
                    endTime: eventEndTime
                });

                newEvent.save(function (err) {
                    if (err) {
                        console.log("Error saving new event for registrant " + email + ". Error: " + err);
                        res.send("Error: registrant saved, but unable to add event.");
                        return;
                    }
                });

                newRegistrant.events.push(newEvent);
                newRegistrant.save();

            }

            res.send("Registrant " + email + " successfully added.");
        } else {
            console.log("Registrant: " + email + " already exists");
            res.send("Error: User: " + email + " already exists.");
        }
    });

};