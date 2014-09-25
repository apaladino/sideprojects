/*
 * Event data object. This can be any type of event
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EventSchema = new Schema({
	organizerKey : { type: String, ref: 'Organizer' },
	organizer : {type: Schema.Types.ObjectId, ref: 'Organizer'},
	title : String,
	description : String,
	startDate : Date,
	startTime : String,
	startTimeAMPM : String,
	endTime : String,
	endTimeAMPM : String,
    registrants: [{ type: Schema.Types.ObjectId, ref: 'Registrant' }]

});


module.exports = mongoose.model('Event', EventSchema);