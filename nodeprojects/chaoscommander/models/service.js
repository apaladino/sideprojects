var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var ServiceSchema = new Schema({
	_accountKey : { type: String, ref: 'Account' },
    title: String,
    host: String,
    startCommand: String,
    stopCommand: String,
    enabled: Boolean,
    policies : [{ type: Schema.Types.ObjectId, ref: 'Policy' }]
});


module.exports = mongoose.model('Service', ServiceSchema);