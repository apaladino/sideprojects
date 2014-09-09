<<<<<<< HEAD
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


=======
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


>>>>>>> 0439cc304672925803b0547d1a6618e472e5ce47
module.exports = mongoose.model('Service', ServiceSchema);