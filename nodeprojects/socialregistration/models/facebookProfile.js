/*
 * Facebook Profile data object.
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var FacebookProfileSchema = new Schema({
	_registrantKey : { type: String, ref: 'Registrant' },
    emailAddress : String,
    firstName : String,
    lastName : String,
    pictureUrl : String,
    ageRange : Object,
    fbLink : String,
    locale : String,
    timezone : String
});


module.exports = mongoose.model('FacebookProfile', FacebookProfileSchema);

/*
{
   "eventInfo":{
      "eventId":"1",
      "eventTitle":"event 1",
      "eventStartTime":"1/1/1",
      "eventEndTime":"2/2/2"
   },
   "fbProfile":{
      "first_name":"Andy",
      "last_name":"Paladino",
      "age_range":{
         "min":"21"
      },
      "email":"andy_paladino@yahoo.com",
      "link":"https://www.facebook.com/app_scoped_user_id/10202568243153260/",
      "locale":"en_US",
      "timezone":"-7",
      "id":"10202568243153260"
   },
   "fbPicture":{
      "data":{
         "url":"https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xaf1/v/t1.0-1/c29.29.361.361/s50x50/216698_1878834376544_5190992_n.jpg?oh=177b1ff485b12057985ba1bf291a812d&amp;oe=548464DA&amp;__gda__=1418491536_7372157251a37d827d94e3e508abffcc",
         "is_silhouette":"false"
      }
   }
}*/