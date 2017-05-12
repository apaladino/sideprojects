var Macro = require('./model/Macro');
var nl = require('os').EOL;
var fs = require('fs');
var FileData = require('./model/FileData');
var JSUtil = require('./JSUtil')

function MacroParser(){
	
	this.loadMacros = function(filename){
		
		console.log("Loading macros for file: " + filename);
		
		var commentsStarted = false;
		var endTagFound = false;
		var macroFound = false;
		var comments = [];
		var body = [];
		var macroName;
		var macroFullName;
		var macros = [];
		var contents = fs.readFileSync(filename, 'utf8').toString().split("\n");
		var type = 'vm';

		//go through the contents array in reverse order and parse 
		var j = 0;
		for(var i = contents.length-1; i >= 0; i--){
			
			var line = contents[i].trim();
			
			
			if(i==0){
				if(line != ""){
					//console.log("adding comments");
					comments.push(encode(line + "\r"));
				}
				
				//console.log("adding macro: ");
				var newMacro = new Macro(macroName, macroFullName, filename, comments, body, type);
				body = [];
				comments = [];
				macros.push(newMacro);
				continue;
			}
			
			
			if(line == ""){
				continue;
			}
			
			if(line.indexOf("#end") ==0 ){
				//console.log("end found");
				endTagFound = true;
				
				if(macroFound){
					//console.log("Adding Macro " );
					// next end tag preceding macro
					var newMacro = new Macro(macroName, macroFullName, filename, comments, body, type);
					
					macros.push(newMacro);
					macroName = "";
					macroFullName = "";
					macroFound = false;
					commentsStarted = false;
					body = [];
					comments = [];
					
					//console.log("adding body");
					body.push(encode(contents[i] + "\r"));
					continue;
				}
			}

			if(macroFound && (line.indexOf("#end") < 0 || line == "" || i==0)){
				//console.log("comment found: " );
				comments.push(encode(line + "\r"));
			}else{
				if(endTagFound && !macroFound){
					//console.log("adding body: ");
					body.push(encode(contents[i] + "\r"));
				}	
			}
			
			
			
			
			if(line.indexOf("#macro") ==0){
				//console.log("macro found");
				macroFound = true;
				macroName = line.split("(")[1].trim().split(" ")[0].trim();
				macroFullName = line;
			}
		}// end for loop
		return macros;
	};
	
	function encode(body){
		return body; // body.replace(/\t/g, "&nbsp;&nbsp;");
	}
	
}


module.exports = MacroParser;