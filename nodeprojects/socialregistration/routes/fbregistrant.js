var Registrant = require('../models/registrant');
var Event = require('../models/event');

function createEvent(req){

}
exports.registerWithFacebook = function(req, res){
    console.log("POST /registrant/facebook request received. " + JSON.stringify(req.body));

    var eventId = req.body.eventId;
    var eventTitle = req.body.eventTitle;
    var eventStartTime = req.body.eventStartTime;
    var eventEndTime = req.body.eventEndTime;

    var fbProfile = req.body.fbProfile;

    var firstName = fbProfile.first_name;
    var lastName = fbProfile.lastName;
    var email = fb.profile.email;
    var fbLink = fbProfile.link;
    var locale = fbProfile.locale;
    var timezone = fbProfile.timezone;
    var pictureUrl = "";
    if(req.body.fbPicture && req.body.fbPicture.data){
        pictureUrl = req.body.fbPicture.data.url;
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


    res.send("SUCCESS: " + JSON.stringify(req.body));

    /*
     {"eventInfo":{"eventId":"1",
     "eventTitle":"event 1",
     "eventStartTime":"1/1/1",
     "eventEndTime":"2/2/2"},
     "fbProfile":{"first_name":"Andy","last_name":"Paladino","age_range":{"min":"21"},"email":"andy_paladino@yahoo.com","link":"https://www.facebook.com/app_scoped_user_id/10202568243153260/","locale":"en_US","timezone":"-7","id":"10202568243153260"},"fbPicture":{"data":{"url":"https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xaf1/v/t1.0-1/c29.29.361.361/s50x50/216698_1878834376544_5190992_n.jpg?oh=177b1ff485b12057985ba1bf291a812d&oe=548464DA&__gda__=1418491536_7372157251a37d827d94e3e508abffcc","is_silhouette":"false"}}}

    */
}