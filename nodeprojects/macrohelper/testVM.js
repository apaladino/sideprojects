var nl = require('os').EOL;
var fs = require('fs');
var FileData = require('./model/FileData');
var JSUtil = require('./JSUtil')
var filename = 'C:/Users/Andy.Paladino/Downloads/tmpfiles/temp.vm';
var Macro = require('./model/Macro');


var contents = fs.readFileSync(filename, 'utf8').toString().split("\n");



var commentsStarted = false;
var endTagFound = false;
var macroFound = false;
var comments = [];
var body = [];
var macroName;
var macroFullName;

var macros = [];

//go through the contents array in reverse order and parse 
var j = 0;
for(var i = contents.length-1; i >= 0; i--){
	
	if(i==0){
		if(line != ""){
			console.log("adding comments");
			comments.push(line);
		}
		
		console.log("adding macro: ");
		var newMacro = new Macro(macroName, macroFullName,filename, comments, body);
		body = [];
		comments = [];
		macros.push(newMacro);
		continue;
	}
	
	var line = contents[i].trim();
	console.log("*** " + i + ":        line: " + line);
	
	if(line == ""){
		continue;
	}
	
	if(line.indexOf("#end") ==0 ){
		console.log("end found");
		endTagFound = true;
		
		if(macroFound){
			console.log("Adding Macro " );
			// next end tag preceding macro
			var newMacro = new Macro(macroName, macroFullName,filename, comments, body);
			macros.push(newMacro);
			macroName = "";
			macroFullName = "";
			macroFound = false;
			commentsStarted = false;
			body = [];
			comments = [];
			
			console.log("adding body");
			body.push(line);
			continue;
		}
	}

	if(macroFound && (line.indexOf("#end") < 0 || line == "" || i==0)){
		console.log("comment found: " );
		comments.push(line);
	}else{
		if(endTagFound && !macroFound){
			console.log("adding body: ");
			body.push(contents[i]);
		}	
	}
	
	
	
	
	if(line.indexOf("#macro") ==0){
		console.log("macro found");
		macroFound = true;
		macroName = line.split("(")[1].trim().split(" ")[0].trim();
		macroFullName = line;
	}
}// end for loop

console.log(JSON.stringify(macros, null, 2));
