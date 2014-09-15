/*
 * LinkedIn Profile data object.
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var LinkedInProfileSchema = new Schema({
	_registrantKey : { type: String, ref: 'Registrant' },
    emailAddress : String,
    firstName : String,
    lastName : String,
    pictureUrl : String,
    summary : String,
    positions: [{type: Schema.Types.ObjectId, ref: 'LinkedInCompanyProfile'}]
});


module.exports = mongoose.model('LinkedInProfile', LinkedInProfileSchema);