var PatientInfo = require('../models/PatientInfo');
var PatientHistory = require('../models/PatientHistory');
var PatientInfoService = require('./PatientInfoService');

var LineByLineReader = require('line-by-line');
   

exports.loadPatientHistoryData = function(){

	PatientHistory.find().count(function(err, count){
	    console.log("Number of PatientHistory docs: ", count );
	    if(count == 0){
	    	// load patient data
	    	loadPatientHistoryFromCsv();
	    }else{
	    	console.log("PatientHistory records already loaded.");
	    }
	});	
}

exports.reloadPatientHistory = function(){
	console.log("Reloading patient history");
	PatientHistory.remove({})
		.cursor()
		.on('end', function() { 
		  loadPatientHistoryFromCsv(); 
	});
}

exports.loadPatientHistoryFromCsv = function(){
	console.log("loadPatientHistoryFromCsv...");

 	lr = new LineByLineReader('./data/history.csv');

	lr.on('error', function (err) {
		console.log("Error: " +   err);
	});

	lr.on('line', function (line) {
		console.log("Line: " + line);

		if(line.indexOf('PID, FIRSTNAME, MIDDLENAME') < 0){
			var phistory = createPatientHistoryFromCsvLine(line);
			console.log("phistory: " + JSON.stringify(phistory));
			
			// look up patient history based on pid
			PatientInfoService.updatePatientHistory(phistory.pid, phistory);
			
			
		}
	});

	lr.on('end', function () {
		console.log("end.");
	});
}

function createPatientHistoryFromCsvLine(line){
	
	var tokens = line.split(',');
	
	var phistory = new PatientHistory({
		phid: tokens[0],
		pid: tokens[1],
		doctors: tokens[2],
		symptoms: tokens[3],
		diagnosis: tokens[4],
		prescription: tokens[5],
		status: tokens[6]
	});
	
	return phistory;
	
}
