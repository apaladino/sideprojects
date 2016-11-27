var PatientInfoService = require('./PatientInfoService');
var PatientHistoryService = require('./PatientHistoryService');
var PatientInfo = require('../models/PatientInfo');
var PatientHistory = require('../models/PatientHistory');

exports.searchPatientRecords = function(req, res){
	var searchType = req.query.searchType;
	var searchField = req.query.searchField;
	var searchQuery = req.query.searchQuery;
	var useRegex = req.query.useRegex;
	
	console.log("useRegex: " + useRegex);
	var queryObj = {};
	if(useRegex == 'YES')
		queryObj[searchField] = new RegExp('^' + searchQuery);
	else
		queryObj[searchField] = searchQuery;
	
	console.log(JSON.stringify(queryObj));
	
	
	if(searchType === 'Personal'){
		// Patient Info search
		PatientInfo.find(queryObj, function(err, patientRecords){
			
				if(err)
					console.log("error: " + err);
				else{
					
					res.render('patientSearch', { title: 'Patient Information Search Results', "searchType" :  searchType, 
						"searchField" : searchField, "searchQuery" : searchQuery, 
						errorMsg : err, records : patientRecords });
				}
			});
	}else{
		// Patient History search
		PatientHistory.find(queryObj, function(err, patientRecords){
			
			if(err)
				console.log("error: " + err);
			else{
				
				res.render('patientHistorySearch', { title: 'Patient History Search Results', "searchType" :  searchType, 
					"searchField" : searchField, "searchQuery" : searchQuery, 
					errorMsg : err, records : patientRecords });
			}
		});
		
	}
	console.log("searchPatientRecords: " + searchType + ", " + searchField + ", " + searchQuery);
	
	
	
	
}