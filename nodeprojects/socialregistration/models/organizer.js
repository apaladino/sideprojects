/*
 * Organizer data object. This can be any type of Organizer
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var OrganizerSchema = new Schema({
	organizerId: Number,
    organizerEmail: String,
    events : [{ type: Schema.Types.ObjectId, ref: 'Organizer' }]
});


module.exports = mongoose.model('Organizer', OrganizerSchema);