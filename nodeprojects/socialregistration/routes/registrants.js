var Registrant = require('../models/registrant');
var Event = require('../models/event');

exports.getRegistrantByID = function(req, res){
	console.log("GET /registrant/reg_id " + req.params.reg_id);
	
	var regId = req.params.reg_id;
	
	Registrant.findOne({ '_id': regId })
		.populate('events')
		.exec(function (err, registrant) {
		  if (err){
			  res.send("Unexpected error: " + err);
		  }
		  
		  console.log("registrant: " + JSON.stringify(registrant));
		  
		  if((!registrant) || typeof registrant == "undefined"){
			  res.status(404);
			  res.send("Error: Registrant: " + email + " not found.");
		  }else{
			  res.status(200);
			  res.send(JSON.stringify(registrant));
		  }
		  
		});
}

exports.getRegistrant = function(req, res){
	
	console.log("GET /registrant " + JSON.stringify(req.query));
	
	var email = req.query.email;
	
	Registrant.findOne({ 'email': email })
		.populate('events')
		.exec(function (err, registrant) {
		  if (err){
			  res.send("Unexpected error: " + err);
		  }
		  
		  console.log("registrant: " + JSON.stringify(registrant));
		  
		  if((!registrant) || typeof registrant == "undefined"){
			  res.status(404);
			  res.send("Error: Registrant: " + email + " not found.");
		  }else{
			  res.status(200);
			  res.send(JSON.stringify(registrant));
		  }
		  
	});
}

exports.addRegistrant = function(req, res){
	
	console.log("POST /registrants " + JSON.stringify(req.body));
	
	var firstName = req.body.firstName;
	var lastName = req.body.lastName;
	var email = req.body.email;
	
	// 
	// Validate registrant information
	//
	if(!firstName || typeof firstName == "undefined"){
		res.send("Error: Registrant first name is missing.");
		return;
	}
	
	if(!lastName || typeof lastName == "undefined"){
		res.send("Error: Registrant last name is missing.");
		return;
	}
	
	if(!email || typeof email == "undefined"){
		res.send("Error: Registrant email is missing.");
		return;
	}
	
	//
	// Validate optional event information
	//
	var eventId = req.body.eventId;
	var eventTitle = req.body.eventTitle;
	var eventStartTime = req.body.eventStartTime;
	var eventEndTime = req.body.eventEndTime;
	
	if((eventId || eventTitle || eventStartTime || eventEndTime) &&
			(!eventId || !eventTitle || !eventStartTime || !eventEndTime)){
		res.send("Error: All optional event information must be supplied.");
		return;
	}
	
	// look up registrant by email.
	Registrant.findOne({ 'email': email },function (err, registrant) {
	  if (err){
		  res.send("Unexpected error: " + err);
	  }
	  
	  console.log("registrant: " + JSON.stringify(registrant));
	  
	  if((!registrant) || typeof registrant == "undefined"){
		  // not found
		  console.log("Registrant: " + email + " not found. Creating new Registrant Schema.");
		 
		  var newRegistrant = new Registrant({
			   firstname: firstName,
			   lastName: lastName,
			   email: email 
		  });
		  
		  newRegistrant.save(function(err) {
	            if (err) {
	            	console.log("Error saving registrant. Error: " + err);
	                res.send("Error: unable to save registrant: " + email +".");
	                return;
	            }
	        });
		  
		  
		  if(eventId){
  			  // optional event information supplied.
  			  var newEvent = new Event({
  				_registrantKey : newRegistrant._key,
  				eventId: eventId,
  			    eventTitle: eventTitle,
  			    startTime: eventStartTime,
  			    endTime: eventEndTime});
  			  
  			  newEvent.save(function(err){
  				  if(err){
  					  console.log("Error saving new event for registrant " + email + ". Error: " + err);
  					  res.send("Error: registrant saved, but unable to add event.");
  					  return;
  				  }
  			  });
  			  
  			newRegistrant.events.push(newEvent);
  			newRegistrant.save();
  			  
  		  }

		  res.send("Registrant " + email + " successfully added.");
	  }else{
		  console.log("Registrant: " + email + " already exists");
		  res.send("Error: User: " + email + " already exists.");
	  }
	});
	
};