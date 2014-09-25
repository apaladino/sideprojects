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

    print(eventId + "," + firstName + "," +
       lastName + "," + email + "," + fbLink + "," + timezone +","+ pictureUrl  +"," + ageRange);


    console.log("Looking for event: " + eventId);

    Event.findOne({ "_id" : eventId}, function(err, event){
        if(err){
            console.log("Unexpected error: " + err);
            res.status(500);
            res.send("Unexpected error: " + err);
            return;
        }

        if(!(typeof event === 'undefined') && event){
            // event found
            console.log("Located event.");
            console.log("Looking up registrant " + email);

            Registrant.findOne({ 'email' : email})
            .populate(regIncludes)
            .exec(function(err, registrant){

                if(err){
                    console.log("Unexpected Error: " + err);
                    res.status(500);
                    res.send("Unexpected Error: " + err);
                    return;
                }

                if(!(typeof registrant === 'undefined') && registrant){
                    // registrant found
                    console.log("registrant: " + email + " found.");

                }else{
                    // not found
                    console.log("registrant: " + email + " not found. Creating new registrant.");
                    var newRegistrant = registrantService.createNewRegistrant(firstName, lastName, email);

                    newRegistrant.save(function (err, registrant) {
                        if (err) {
                            handleError("Error: unable to save registrant: " + email + ".", res);
                            return;
                        }

                        // new registrant saved, now save their facebook profile
                        var theirProfile =  registrantService.createNewFacebookProfile(firstName, lastName,
                                email, fbLink, locale, timezone, pictureUrl, ageRange);

                        theirProfile.save(function (err, newProfile) {
                            if (err) {
                                handleError("Unable to save Facebook profile. Error: " + err, res);
                                return;
                            }

                            console.log("Created new Profile " + JSON.stringify(theirProfile));
                            registrant.facebookProfile = theirProfile;

                            event.registrants.push(registrant);
                            event.save(function(err){
                                if(err){
                                    handleError("Unable to add registrant to existing event. Error: " + err, res);
                                }

                                registrant.events.push(event);

                                registrant.save(function(err, newProfile){
                                    if(err){
                                        handleError("Unable to save registrant. Error: " + err, res);
                                    }

                                     console.log("Updated registrant: " + email + " to event: " + eventId);
                                    res.send("Added registrant: " + email + " to event: " + eventId);
                                    return;
                                });

                                console.log("Added registrant: " + email + " to event: " + eventId);
                                res.send("Added registrant: " + email + " to event: " + eventId);
                                return;
                            });

                        });

                    });

                }
            });
        }else{
            console.log("Event with eventId: " + eventId + " not found.");
            res.status(404);
            res.send("Event not found.");
            return;
        }
    });

}


function handleError(msg, res){
    console.log(msg);
    res.send(msg);
}