/*
 * Registrant user data object.
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var RegistrantSchema = new Schema({
	firstname: String,
    lastName: String,
    email: String,
    events : [{ type: Schema.Types.ObjectId, ref: 'Event' }],
    linkedInProfile : {type: Schema.Types.ObjectId, ref: 'LinkedInProfile'},
    facebookProfile : {type: Schema.Types.ObjectId, ref: 'FacebookProfile'}
});


module.exports = mongoose.model('Registrant', RegistrantSchema);