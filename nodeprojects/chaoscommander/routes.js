var passport = require('passport');
var Account = require('./models/account');
var Service = require('./models/service');
var Policy = require('./models/policy');
var Connection = require('./models/connection');
var fs = require('fs');
var _exec = require('child_process').exec;
var title = 'Chaos Commander';

module.exports = function(app) {

	app.get('/', function(req, res) {
		res.render('index', {
			user : req.user,
			'title' : title
		});
	});

	app.get('/register', function(req, res) {
		res.render('register', {
			'title' : title
		});
	});

	app.post('/register',
					function(req, res) {
						Account
								.register(
										new Account({
											username : req.body.username
										}),
										req.body.password,
										function(err, account) {
											if (err) {
												console
														.log("Error occurred while trying to register with account: "
																+ err);
												return res.render('register', {
													account : account,
													'title' : title,
													'errMsg' : err.message
												});
											}

											passport
													.authenticate('local')
													(
															req,
															res,
															function() {
																// create a snap shot directory for new user
																var path = __dirname + '/snapshots/' + req.user.username;
																fs.mkdir(path,function(e){
																    if(e && e.code === 'EEXIST'){
																        console.log("Unable to create directory: " + path + ", directory already exists.");
																    } else {
																        console.log("Directory: " + path + " created.");
																    }
																});
																res.redirect('/workspace');
															});
										});
					});

	app.get('/workspace', function(req, res, next) {

		if (!req.user) {
			console.log("User not found in session, redirecting to '/'");
			res.statusCode = 302;
			res.setHeader("Location", "/");
			res.end();
			return;
		}

		if(typeof req.user.services != "undefined"){
			Account
			.findOne({ _id: req.user._id })
			.populate('services')
			.exec(function (err, account) {
				console.log("Looking up user services");
				res.render('workspace', {
					user : req.user,
					services : account.services,
					'title' : title
				});
			});
		}else{
			res.render('workspace', {
				user : req.user,
				services : [],
				'title' : title
			});
		}
		
		
			
	});

	app.get('/login', function(req, res) {
		res.render('login', {
			user : req.user,
			'title' : title
		});
	});

	app.post('/login', passport.authenticate('local'), function(req, res) {

		console.log("looking up user: " + JSON.stringify(req.user));
		res.redirect('/workspace');
	});

	app.get('/logout', function(req, res) {
		req.logout();
		res.redirect('/');
	});

	app.get('/services/delete', function(req, res){
		console.log("Delte service request received: " +
				JSON.stringify(req.query));
		

        try{
        	var serviceId = req.query.serviceId;
            console.log("Looking up service information for serviceId: " + serviceId);
            
            Service
			.findOne({ _id: serviceId })
			.populate('policies')
			.remove(function (err, service) {
				if(err){
					console.log("Error deleting service. " + 
							JSON.stringify(err));
					
				}
				res.redirect('/workspace');
			});
           
            
        }catch(err){
            console.log(err);
            res.send(err);
        }


		
	});
	
    app.post('/services', function(req, res) {
        console.log('Create service request received: '
                + JSON.stringify(req.body) + ", userkey: "
                + JSON.stringify(req.user));

        var serviceTitle = req.body.serviceTitle;
        var hostName = req.body.hostName;
        var startCommand = req.body.startCommand;
        var stopCommand = req.body.stopCommand;
        var userKey = req.user._id;
        
        var service = new Service({
            _accountKey : userKey,
            title : serviceTitle,
            host : hostName,
            startCommand : startCommand,
            stopCommand : stopCommand,
            enabled : true
        });
       
        service.save(function(err) {
            if (err) {
                console.log("unable to save service.");
            }
        });

        req.user.services.push(service);
        req.user.save();
        res.send(service);

    });
    

    app.post('/services/settings', function(req, res) {
        console.log('Edit service settings request received: '
                + JSON.stringify(req.body) + ", userkey: "
                + JSON.stringify(req.user));

        var serviceId = req.body.serviceId;
        var serviceTitle = req.body.serviceTitle;
        var hostName = req.body.hostName;
        var startCommand = req.body.startCommand;
        var stopCommand = req.body.stopCommand;
        var enabled = req.body.enabled;
        
        Service.update({_id: serviceId}, {
        	title: serviceTitle,
        	host: hostName,
        	startCommand: startCommand,
        	stopCommand: stopCommand,
        	enabled: enabled
        }, function(err, numberAffected, rawResponse){
        	if(err){
        		console.log("Unable to update service settings: " + err);
        		res.send("ERROR: " + err);
        	}else{
        		res.send("Service Settings Have Been Successfully Edited.");
        	}
        });
        
    });


    app.get('/services/workspace', function(req, res) {

        try{
        	var serviceId = req.query.id;
            console.log("Looking up service information for serviceId: " + serviceId);
            
            Service
			.findOne({ _id: serviceId })
			.populate('policies')
			.exec(function (err, service) {
				if(typeof service.enabled == 'undefined'){
					service.enabled=true;
				}
				res.render('svcworkspace', {
	                user : req.user,
	                'serviceId' : serviceId,
	                'service' : service
	            });
			});
            
        }catch(err){
            console.log(err);
            res.send(err);
        }

    });

};
