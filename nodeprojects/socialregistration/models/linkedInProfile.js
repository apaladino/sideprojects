/*
 * LinkedIn Profile data object.
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var LinkedInProfileSchema = new Schema({
	_registrantKey : { type: String, ref: 'Registrant' },
    profile: Object
});


module.exports = mongoose.model('LinkedInProfile', LinkedInProfileSchema);