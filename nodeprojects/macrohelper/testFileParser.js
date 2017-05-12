var FileParser = require('./FileParser');

var masterDir = '/Users/Andy.Paladino/Downloads/tmpfiles';

var fileParser = new FileParser(masterDir);
var macros = fileParser.parseMacroFiles();

var localStorage;

if (typeof localStorage === "undefined" || localStorage === null) {
	  var LocalStorage = require('node-localstorage').LocalStorage;
	  localStorage = new LocalStorage('./scratch');
}

var storedMacros=localStorage.getItem('macros');
console.log("Retreived " + storedMacros.length + " items from storage");
