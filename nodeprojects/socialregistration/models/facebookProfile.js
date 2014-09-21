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