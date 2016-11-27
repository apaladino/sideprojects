var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var PatientInfoSchema = new Schema({
	patientInfoSchemaId: Number,
	pid: String,
	firstName: String,
	middleName: String,
	lastName: String,
	phone: String,
	gender: String,
	maritalStatus: String,
	dob: String,
	nextOfKin: String,
	nextOfKinContacts: String,
	dateOfRegistration: String,
	residence: String,
    history: [{ type: Schema.Types.ObjectId, ref: 'PatientHistory' }]

});


module.exports = mongoose.model('PatientInfo', PatientInfoSchema);