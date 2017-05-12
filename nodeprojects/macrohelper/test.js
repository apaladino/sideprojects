var nl = require('os').EOL;
var fs = require('fs');
var FileData = require('./model/FileData');
var JSUtil = require('./JSUtil')
var filename = 'C:/Users/Andy.Paladino/Downloads/tmpfiles/temp.vm';


var contents = fs.readFileSync(filename, 'utf8').toString().split("\n");


function Macro(name, fullName, filename, comments, body){
	this.name = name;
	this.fullName = fullName;
	this.filename = filename;
	this.comments = comments;
	this.body = body;
};

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
	
	return body.replace(/\r/g,"<br/>").replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;");
}

console.log(body);

var body = encode(body);
console.log(body);