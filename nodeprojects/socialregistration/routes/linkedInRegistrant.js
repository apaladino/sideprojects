var Registrant = require('../models/registrant');
var Event = require('../models/event');
var registrantService = require('../models/service/registrantService');
var LinkedInProfile = require('../models/linkedInProfile');
var LinkedInCompanyProfile = require('../models/linkedInCompanyProfile');
var expectedState = 'AP2014Maine';
var API_KEY = '750c8k09ptha6k';
var SECRET_KEY = 'ECInL187WE272dAa';
var request = require('request');
var regIncludes = 'events linkedInProfile';

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
                    handleError("Unexpected error: " + err, res);
                    return;
                }

                if ((!registrant) || typeof registrant == "undefined") {
                    // not found
                    console.log("Registrant: " + email + " not found. Creating new Registrant Schema.");

                    var newRegistrant = registrantService.createNewRegistrant(firstName, lastName, email);

                    newRegistrant.save(function (err) {
                        if (err) {
                            handleError("Error: unable to save registrant: " + email + ".", res);
                            return;
                        }

                        // new registrant saved, now save their linkedIn profile
                        var theirProfile = registrantService.createNewLinkedInProfile(newRegistrant._id, profile);
                        theirProfile.save(function (err) {
                            if (err) {
                                handleError("Unable to save linkedIn profile. Error: " + err, res);
                                return;
                            }
                        });

                        console.log("Created new Profile " + JSON.stringify(theirProfile));

                        // linkedIn profile saved, add company profiles
                        var positions = registrantService.createLinkedInPositions(theirProfile._id, profile);
                        console.log("positions created: " + JSON.stringify(positions));
                        theirProfile.positions = positions;
                        theirProfile.save();
                        newRegistrant.linkedInProfile = theirProfile;

                        if (eventId && eventTitle) {

                            // look up the event so see if it exists already and if so
                            // add the user to the event
                            Event.findOne({'eventId':eventId}).exec(function(err, event){
                                if(err){
                                    handleError("Unable to look up event by eventId. Error: " + err, res);
                                }

                                if(event && typeof event != "undefined"){
                                    // event already exists
                                    event.registrants.push(registrant);
                                    event.save(function(err){
                                        handleError("Unable to add registrant to existing event. Error: " + err, res);

                                        console.log("Added registrant: " + email + " to event: " + eventTitle);
                                        res.send("Added registrant: " + email + " to event: " + eventTitle);
                                        return;
                                    });
                                }
                            });

                            // optional event information supplied.
                            var newEvent = registrantService.createNewEvent(newRegistrant._id, eventId, eventTitle, eventStartTime,
                                    eventEndTime);

                            newEvent.registrants.push(newRegistrant);
                            // save new event
                            console.log("Creating new event. ");

                            newEvent.save(function (err) {
                                if (err) {
                                    handleError("Error saving new event for registrant " + email + ". Error: " + err, res);
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
                    var isRegistered = registrantService.isUserRegisteredForEvent(registrant, eventId)

                    if (!isRegistered) {
                        //
                        // look for existing event with same eventId and associate new user with
                        // this event
                        //

                        // look up the event so see if it exists already and if so
                        // add the user to the event
                        Event.findOne({'eventId':eventId}).exec(function(err, event){
                            if(err){
                                handleError("Unable to look up event by eventId. Error: " + err, res);
                            }

                            if(event && typeof event != "undefined"){
                                // event already exists
                                event.registrants.push(registrant);
                                event.save(function(err){
                                    handleError("Unable to add registrant to existing event. Error: " + err, res);

                                    console.log("Added registrant: " + email + " to event: " + eventTitle);
                                    res.send("Added registrant: " + email + " to event: " + eventTitle);
                                    return;
                                });
                            }

                        });

                        // optional event information supplied.
                        console.log("creating new event.");
                        var newEvent = registrantService.createNewEvent(registrant._key, eventId, eventTitle, eventStartTime, eventEndTime);
                        newEvent.registrants.push(registrant);
                        // save new event
                        newEvent.save(function (err) {
                            if (err) {
                                handleError("Error saving new event for registrant " + email + ". Error: " + err, res);
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
                                handleError("Error: registrant saved, but unable to add linkedIn profile. Error: "
                                        + err, res);
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
