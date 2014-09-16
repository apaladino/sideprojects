/*
 * Event data object. This can be any type of event
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EventSchema = new Schema({
	_registrantKey : { type: String, ref: 'Registrant' },
    eventId: Number,
    eventTitle: String,
    startTime: Date,
    endTime: Date,
    registrants: [{ type: Schema.Types.ObjectId, ref: 'Registrant' }]
});


module.exports = mongoose.model('Event', EventSchema);