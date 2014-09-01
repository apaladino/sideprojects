var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var PolicySchema = new Schema({
	_serviceKey : { type: String, ref: 'Service' },
    title : String,
    enabled : Boolean,
    beginTime : Date,
    downDelay : Number,
    frequency : String
});

module.exports = mongoose.model('Policy', PolicySchema);