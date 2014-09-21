var Registrant = require('../models/registrant');
var Event = require('../models/event');
var registrantService = require('../models/service/registrantService');
var facebookProfile = require('../models/facebookProfile');
var regIncludes = 'events facebookProfile';

function print(value){
    console.log(value);
}
exports.registerWithFacebook = function(req, res){
    console.log("POST /registrant/facebook request received. " + JSON.stringify(req.body));

    var eventInfo = req.body.eventInfo;
    var fbProfile = req.body.fbProfile;
    var fbPicture = req.body.fbPicture;

    var eventId = eventInfo.eventId;
    var eventTitle = eventInfo.eventTitle;
    var eventStartTime = eventInfo.eventStartTime;
    var eventEndTime = eventInfo.eventEndTime;
    var firstName = fbProfile.first_name;
    var lastName = fbProfile.last_name;
    var email = fbProfile.email;

    var fbLink = fbProfile.link;
    var locale = fbProfile.locale;
    var timezone = fbProfile.timezone;
    var pictureUrl = "";
    if(fbPicture && fbPicture.data){
        pictureUrl = fbPicture.data.url;
    }

    var ageRange = "";
        if(fbProfile.age_range){

       if(fbProfile.age_range.min){
          age_range = fbProfile.age_range.min;
       }

       if(fbProfile.age_range.max){
          if(ageRange.length > 0){
             ageRange += "-";
          }
          ageRange += fbProfile.age_range.max;
       }
    }

    print(eventId + "," + eventTitle + ", " + eventStartTime + "," + eventEndTime + "," + firstName + "," +
       lastName + "," + email + "," + fbLink + "," + timezone +","+ pictureUrl  +"," + ageRange);

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

                // new registrant saved, now save their facebook profile
                var theirProfile =  registrantService.createNewFacebookProfile(firstName, lastName,
                        email, fbLink, locale, timezone, pictureUrl, ageRange);

                theirProfile.save(function (err) {
                    if (err) {
                        handleError("Unable to save Facebook profile. Error: " + err, res);
                        return;
                    }
                });

                console.log("Created new Profile " + JSON.stringify(theirProfile));

               theirProfile.save();
               newRegistrant.facebookProfile = theirProfile;

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

                    // save new event
                    console.log("Creating new event. ");
                    newEvent.registrants.push(newRegistrant);
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
                    JSON.stringify(registrant.facebookProfile));

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
                        });

                        return;
                    }

                });

                // optional event information supplied.
                console.log("creating new event.");
                var newEvent = registrantService.createNewEvent(registrant._key, eventId, eventTitle, eventStartTime, eventEndTime);

                // save new event
                newEvent.save(function (err) {
                    if (err) {
                        handleError("Error saving new event for registrant " + email + ". Error: " + err, res);
                        return;
                    }
                });

                registrant.events.push(newEvent);

                // add facebook profile
                var theirProfile =  registrantService.createNewFacebookProfile(firstName, lastName, email, fbLink,
                            locale, timezone, pictureUrl, ageRange);

                theirProfile.save(function (err) {
                    if (err) {
                        handleError("Error: registrant saved, but unable to add linkedIn profile. Error: "
                                + err, res);
                        return;
                    }

                    console.log("Event created successfully.");

                    if (!registrant.facebookProfile && theirProfile) {
                        console.log("Linked in profile is missing from user. Updating their linkedIn profile");
                        registrant.facebookProfile = theirProfile;
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


    res.send("SUCCESS: " + JSON.stringify(req.body));
}


function handleError(msg, res){
    console.log(msg);
    res.send(msg);
}