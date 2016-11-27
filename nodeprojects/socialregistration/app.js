
/**
 * Module dependencies.
 */

var express = require('express');
var routes = require('./routes');
var registrant = require('./routes/registrants');
var fbregistrant = require('./routes/fbregistrant');
var linkedInRegistrant = require('./routes/linkedInRegistrant');
var event = require('./routes/event');
var http = require('http');
var path = require('path');
var mongoose = require('mongoose');
var app = express();

// all environments
app.set('port', process.env.PORT || 8000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.json());
app.use(express.urlencoded());
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(require('less-middleware')(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

//mongoose
mongoose.connect('mongodb://localhost/socialregdb');


app.get('/', routes.index);
app.post('/registrant', registrant.addRegistrant);
app.post('/registrant/linkedin', linkedInRegistrant.registerWithLinkedIn);
app.post('/registrant/facebook', fbregistrant.registerWithFacebook);
app.get('/registrant/:reg_id', registrant.getRegistrantByID);
app.get('/rest/registrant', registrant.getRegistrant);
app.get('/rest/linkedin/authenticate', registrant.authenticateLinkedInUser);
app.get('/rest/linkedin/register', registrant.registerLinkedInUser);
app.get('/views/createEvent', event.getCreateEventView);
app.get('/views/socialRegistration', registrant.getSocialRegistrationView);
app.get('/views/addRegistrant', registrant.getAddRegistrantView);
app.get('/views/getRegistrantByEmail', registrant.getRegistrantByEmailView);
app.get('/views/getRegistrantByID', registrant.getRegistrantByIDView);
app.get('/events/:event_id', event.loadEventSettingsView);
app.post('/events', event.createNewEvent);
app.get('/views/eventSettings', event.getEventByIDView);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
