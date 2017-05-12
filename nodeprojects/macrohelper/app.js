var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var http = require('http');
var mongoose = require('mongoose');
var Search = require('./routes/Search');
var FileParser = require('./FileParser');
var SearchUtil = require('./SearchUtil');
var masterDir = '/Users/Andy.Paladino/Downloads/tmpfiles';
var title = "Macro Helper Search Tool";

//mongoose
//mongoose.connect('mongodb://localhost/macrohelperdb');
/*mongoose.connect('mongodb://localhost/macrohelper',function(){
    mongoose.connection.db.dropDatabase();
});*/

//load macros
var fileParser = new FileParser(masterDir);
var macros = fileParser.parseMacroFiles();
var searchUtil = new SearchUtil(macros);

//var app = express();
var app = module.exports.app = exports.app = express();


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.set('port', process.env.PORT || 9999);

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


//app.get("/", Search.getSearchPage);
app.get("/", function(req, res){
    res.render('main', { "title" : title});
});

app.post("/search", function(req, res){
	
	console.log("Searching for macros");
	var searchVal = req.body.searchVal;
	var errorMsg = "";
	
	if(!searchVal || searchVal.trim() == ""){
		errorMsg = "Missing search string";
	}
	
	var matches = searchUtil.searchMacros(searchVal);
	
	res.render("searchResults", {"title" : title,  "errorMsg": errorMsg,  "macros": matches});
})


//catch 404 and forward to error handler
app.use(function(req, res, next) {
var err = new Error('Not Found');
err.status = 404;
next(err);
});

//error handlers

//development error handler
//will print stacktrace
if (app.get('env') === 'development') {
app.use(function(err, req, res, next) {
 res.status(err.status || 500);
 res.render('error', {
   message: err.message,
   error: err
 });
});
}

//production error handler
//no stacktraces leaked to user
app.use(function(err, req, res, next) {
res.status(err.status || 500);
res.render('error', {
 message: err.message,
 error: {}
});
});


http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});


module.exports = app;


