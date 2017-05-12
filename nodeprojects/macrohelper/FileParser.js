// Class which loads all of the JS and VM files and parses their data
var LocalStorage = require('node-localstorage').LocalStorage;
var FileData = require('./model/FileData');
var FileLoader = require('./FileLoader');
var JSUtilObj = require('./JSUtil');
var MacroParser = require('./MacroParser');


//var masterDir = '/ATMCodeBase';

var fileLoader = new FileLoader();
var jsUtil = new JSUtilObj();


function DataMap(){
	this.vmMacros = new Object();
	this.jsDataMap = new Object();

};


function FileParser(masterDir){
	
	this.masterDir = masterDir;
	this.parseMacroFiles = function(){
		
		if(!masterDir || masterDir == ""){
			console.log("Macro master directory not defined.");
			exit;
			
		}
		console.log("Parsing macro files in directory: " + masterDir);
		var fileNames = fileLoader.loadFileNames(masterDir);
		console.log("Retrieved " + fileNames.length +  " file names.");

		var macros = parseFileNames(fileNames);
		saveMacros(macros);
		return macros;
	};
	
	function saveMacros(macros){
		console.log("Saving " + macros.length + " macros to DB. " + JSON.stringify(macros, null, 2));
		
		for(var i=0; i < macros.length; i++){
			var macro = macros[i];
			//console.log("saving macro: " + macro.name);
			//macro.save();
		}
	}
	
	function parseFileNames(fileNames){
		//console.log("parsing files.");

		
		// define a fileName data map
		
	    var allMacros = [];

		// loop through each file name and build the dataMap
		for(var i=0; i < fileNames.length; i++){
			
			var filename = fileNames[i];
			var type = getFileTypeFromName(filename);
			if(type == 'VM'){
				macroParser = new MacroParser();
				var macros = macroParser.loadMacros(filename);
				if(macros && macros.length > 0){
					for(var j=0; j < macros.length; j++){
						allMacros.push(macros[j]);
					}
				}
			}else{
				console.log("skipping js file: " + filename);
			}
		}

		return allMacros;
	}


	function getFileTypeFromName(filename){
		
		if(jsUtil.endsWith(filename,'.js')){
			return "JS";
		}

		if(jsUtil.endsWith(filename,".vm")){
		   return "VM";
		}

		throw "Invalid file type for file: " + filename;
	}
	
	/*function sortByName(macros){
		return macros.sort(function(a,b){
			return a.name.localeCompare(b.name);
		});

	}*/

}

module.exports = FileParser;



