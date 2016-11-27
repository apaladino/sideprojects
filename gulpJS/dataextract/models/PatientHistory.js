var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var PatientHistorySchema = new Schema({
	PatientHistorySchemaId: Number,
	phid: String,
	pid: String,
	doctors: String,
	symptoms: String,
	diagnosis: String,
	prescription: String,
	status: String
});


module.exports = mongoose.model('PatientHistory', PatientHistorySchema);