var express = require('express');
var router = express.Router();
var Macro = require('../model/Macro');



exports.getSearchPage = function(req, res){
	
	var macroQuery = Macro.find();
	
	macroQuery.select('name');

	// execute the query at a later time
	macroQuery.exec(function (err, macro) {
	  if (err) return handleError(err);
	  console.log('%s macros found.', macro.name);
	})
	
	
    res.render('main', { "title" : "Macro Helper Search Tool"});
};

