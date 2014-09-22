var Registrant = require('../models/registrant');
var Event = require('../models/event');
var registrantService = require('../models/service/registrantService');
var LinkedInProfile = require('../models/linkedInProfile');
var LinkedInCompanyProfile = require('../models/linkedInCompanyProfile');
var expectedState = 'AP2014Maine';
var API_KEY = '750c8k09ptha6k';
var SECRET_KEY = 'ECInL187WE272dAa';
var request = require('request');
var regIncludes = 'events linkedInProfile facebookProfile';

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
    }).populate("events")
    .populate("linkedInProfile")
    .populate("positions")
    .populate("facebookProfile").exec(function (err, registrant) {
                if (err) {
                    res.send("Unexpected error: " + err);
                }

                console.log("registrant: " + JSON.stringify(registrant));

                if ((!registrant) || typeof registrant == "undefined") {
                    res.status(404);
                    res.send("Error: Registrant: " + email + " not found.");
                } else {

                    if(registrant.linkedInProfile && registrant.linkedInProfile.positions){
                        var linkedInProfile = LinkedInProfile.findOne({'_id' : registrant.linkedInProfile._id})
                        .populate("positions").exec(function (err, profile){
                            if(err){
                                console.log("Unable to retreive linkedIn profile.");
                                return;
                            }
                            console.log("Looked up user linked in profile. " + JSON.stringify(profile));
                            registrant.linkedInProfile = profile;
                            res.status(200);
                            res.send(JSON.stringify(registrant));
                        });
                    }else{
                        res.status(200);
                        res.send(JSON.stringify(registrant));
                    }
                }
            });
}

exports.getRegistrant = function (req, res) {

    console.log("GET /registrant " + JSON.stringify(req.query));

    var email = req.query.email;

    Registrant.findOne({
        'email': email
    }).populate(regIncludes)
    .populate("positions").exec(function (err, registrant) {
                if (err) {
                    res.send("Unexpected error: " + err);
                }

                console.log("registrant: " + JSON.stringify(registrant));

                if ((!registrant) || typeof registrant == "undefined") {
                    res.status(404);
                    res.send("Error: Registrant: " + email + " not found.");
                } else {
                     if(registrant.linkedInProfile && registrant.linkedInProfile.positions){
                        var linkedInProfile = LinkedInProfile.findOne({'_id' : registrant.linkedInProfile._id})
                        .populate("positions").exec(function (err, profile){
                            if(err){
                                console.log("Unable to retreive linkedIn profile.");
                                return;
                            }
                            console.log("Looked up user linked in profile. " + JSON.stringify(profile));
                            registrant.linkedInProfile = profile;
                            res.status(200);
                            res.send(JSON.stringify(registrant));
                        });
                    }else{
                        res.status(200);
                        res.send(JSON.stringify(registrant));
                    }
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


function handleError(msg, res){
    console.log(msg);
    res.send(msg);
}