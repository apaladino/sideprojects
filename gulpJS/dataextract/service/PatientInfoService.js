var PatientInfo = require('../models/PatientInfo');
var PatientHistory = require('../models/PatientHistory');

var LineByLineReader = require('line-by-line');
   

exports.loadPatientInfoData = function(){

	PatientInfo.find().count(function(err, count){
	    console.log("Number of PatientInfo docs: ", count );
	    if(count == 0){
	    	// load patient data
	    	loadPatientsFromCsv();
	    }else{
	    	console.log("PatientInfo records already loaded.");
	    }
	});	
}

exports.updatePatientHistory = function(pid, phistory){
	console.log("lookupPatientInfoByPID: " + pid);
	/*
	 * var linkedInProfile = LinkedInProfile.findOne({'_id' : registrant.linkedInProfile._id})
	 */
	var pinfo = PatientInfo.findOne({'pid' : pid}, function (err, patient) {
	    
		if(err)
			console.log("Error occurred while looking up patient info." + err);
		else{
			if(!patient)
				console.log("no patiend record found for pid:" + pid);
			else{
				phistory.save(function(err, phist){
                    if(err){
                        console.log("Error saving new history.");
                    }
                    
                    patient.history.push(phist);
                    patient.save();
				});
			}		
		}
	});
	
	return pinfo;
}

function createPatientInfoFromCsvLine(line){
	
	var tokens = line.split(',');
	
	var pinfo = new PatientInfo({
		pid: tokens[0],
		firstName: tokens[1],
        middleName: tokens[2],
        lastName: tokens[3],
        phone: tokens[4],
        gender: tokens[5],
        maritalStatus: tokens[6],
        dob: tokens[7],
        residence: tokens[8],
        nextOfKin: tokens[9],
        nextOfKinContacts: tokens[10],
        dateOfRegistration: tokens[11]
    });
	
	return pinfo;
	
}

function loadPatientsFromCsv(){
	console.log("loadPatientsFromCsv...");

 	lr = new LineByLineReader('./data/patients.csv');

	lr.on('error', function (err) {
		console.log("Error: " +   err);
	});

	lr.on('line', function (line) {
		console.log("Line: " + line);

		if(line.indexOf('PID, FIRSTNAME, MIDDLENAME') < 0){
			var pinfo = createPatientInfoFromCsvLine(line);
			pinfo.save(function (err) {
	            if (err) {
	                console.log("Unable to save patient info. " + err);
	                return;
	            }

	        });
		}
	});

	lr.on('end', function () {
		console.log("end.");
	});
}