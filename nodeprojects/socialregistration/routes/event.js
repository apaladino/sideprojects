/*
 *  Handles all event relate requests.
 */
var Organizer = require('../models/organizer');
var Event = require('../models/event');
var registrantService = require('../models/service/registrantService');

exports.getEventByIDView = function(req, res){
    res.render("getEventByIDView");
};

exports.loadEventSettingsView = function(req, res){

    var eventId = req.params.event_id;

    console.log("GET /events/event_id request received. EventID: " + eventId);

    Event.findOne({"_id" : eventId})
        .populate("organizer").exec(function(err, event){
        if(err){
            console.log("Unable to retrieve event by id. " + err);
            res.status(500);
            res.send("Unable to retrieve event.");
            return;
        }

        if(event && !(typeof event === "undefined")){
            // event found
            console.log("found event.");
            res.status(200);
            res.render("eventSettingsPage", {"event":event});
            return;
        }

        // event not found
        console.log("Unable to find event for eventId: " + eventId);
        res.status(404);
        res.send("Event not found.");
    });
}
/*
    Create a new event
*/
exports.createNewEvent = function(req, res){
    console.log("POST /events request recieved. " + JSON.stringify(req.body, undefined, 2));

    var organizerKey = req.body.organizerKey;
    var organizerEmail = req.body.organizerEmail;
    var title = req.body.title;
    var description = req.body.description;
    var startDate = req.body.startDate;
    var startTime = req.body.startTime;
    var startTimeAMPM = req.body.startTimeAMPM;
    var endTime = req.body.endTime;
    var endTimeAMPM = req.body.endTimeAMPM;
    var postToFacebook = req.body.postToFacebook;
    var postToLinkedIn = req.body.postToLinkedIn;

    var d = new Date(startDate + " " + startTime + " " + startTimeAMPM);

    // look up organizer
    console.log("Looking up ogranizer.");
    Organizer.findOne({'organizerId': organizerKey })
        .populate("events")
        .exec(function (err, organizer) {

            if(err){
                res.status(500);
                res.send("Error while looking up organizer.");
            }

            console.log("**" + typeof organizer + "--" + JSON.stringify(organizer));
            if((typeof organizer === "undefined") || (!organizer)){
                // not found

                var newOrganizer = new Organizer({
                    organizerId : organizerKey,
                    organizerEmail : organizerEmail
                });

                newOrganizer.save(function(err, org){
                    if(err){
                        res.status(500);
                        res.send("Unable to save new organizer.");
                        return;
                    }

                    if(org){
                        console.log("new organizer created. Generating new events.");

                        var newEvent = new Event({
                            organizerKey : organizerKey,
                            organizer : org,
                            title : title,
                            description : description,
                            startDate :  startDate,
                            startTime : startTime,
                            startTimeAMPM : startTimeAMPM,
                            endTime : endTime,
                            endTimeAMPM : endTimeAMPM
                        });

                        newEvent.save(function(err, event){
                            if(err){
                                console.log("Error saving new event.");
                            }

                            if(event){
                                console.log("New event created. Adding to organizer event.");

                                org.events.push(event);
                                org.save(function(err){
                                    if(err){
                                        console.log("error adding new event to organizer.");
                                        res.status(500);
                                        res.send("Unable to add new event to organizer.");
                                        return;
                                    }

                                    console.log("finished. " + JSON.stringify(org));
                                    res.status(201);
                                    res.render("eventSettingsPage", { 'event' : event,
                                     "postToFacebook" : postToFacebook,
                                     "postToLinkedIn" : postToLinkedIn});
                                    return;
                                });

                            }else{
                                // error creating event
                                res.status(500);
                                res.send("Unable to create event.");
                            }
                        });

                    }else{
                        // error creating organizer
                        res.status(500);
                        res.send("Unable to create organizer.");
                    }
                });


            }else{
                // organizer already exists.
                console.log("Organizer already exists for organizerKey: " + organizerKey);
                console.log("Looking up event for organizer");

                Event.findOne({organizerKey: organizerKey,
                    title : title,
                    startDate : startDate,
                    startTime : startTime }, function(err, event){
                    if(err){
                        res.status(500);
                        res.send("Error looking up event for organizer.");
                        return;
                    }

                    if((!event) || (typeof event === "undefined")){
                        // not found
                        console.log("Event does not exist. Creating new Event.");
                         var newEvent = new Event({
                            organizerKey : organizerKey,
                            organizer : organizer,
                            title : title,
                            description : description,
                            startDate :  startDate,
                            startTime : startTime,
                            startTimeAMPM : startTimeAMPM,
                            endTime : endTime,
                            endTimeAMPM : endTimeAMPM
                        });

                        newEvent.save(function(err, event){
                            if(err){
                                console.log("Error saving new event.");
                            }

                            if(event){
                                console.log("New event created. Adding to organizer event.");

                                organizer.events.push(event);
                                organizer.save(function(err){
                                    if(err){
                                        console.log("error adding new event to organizer.");
                                        res.status(500);
                                        res.send("Unable to add new event to organizer.");
                                        return;
                                    }

                                    console.log("finished. " + JSON.stringify(organizer));
                                    res.status(201);
                                    res.render("eventSettingsPage", { 'event' : event,
                                     "postToFacebook" : postToFacebook,
                                     "postToLinkedIn" : postToLinkedIn});
                                    return;
                                });

                            }else{
                                // error creating event
                                res.status(500);
                                res.send("Unable to create event.");
                            }
                        });
                    }else{
                        // duplicate event.
                        // error creating event
                        res.status(500);
                        res.send("Unable to create event. Event already exists.");
                    }
                });

            }

        });


}


/*
    Load the create event view
 */
exports.getCreateEventView = function(req, res){
    res.render('createEvent');
}