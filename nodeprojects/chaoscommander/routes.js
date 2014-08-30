var passport = require('passport');
var Account = require('./models/account');
var Connection = require('./models/connection');
var fs = require('fs');
var _exec = require('child_process').exec;
var title = 'Chaos Commander';
var Service = require('./models/service');

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

		res.render('workspace', {
			user : req.user,
			'title' : title
		});
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

    app.post('/services', function(req, res) {
        console.log('Create service request revieved: '
                + JSON.stringify(req.body) + ", userkey: "
                + JSON.stringify(req.user));

        console.log("Service: " + Service);

        var serviceTitle = req.body.serviceTitle;
        var hostName = req.body.hostName;
        var serviceName = req.body.serviceName;
        var startCommand = req.body.startCommand;
        var stopCommand = req.body.stopCommand;

        var service = new Service({
            _accountKey : req.user._id,
            title : serviceTitle,
            host : hostName,
            startCommand : startCommand,
            stopCommand : stopCommand
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

    app.get('/services/workspace', function(req, res) {

        try{
            var connectionName = req.query.id;
            var prefix = connectionName;
            var snapshotsDir =  __dirname + '/snapshots/'
            var dir = snapshotsDir + '/' + req.user.username + '/' + connectionName + '/';

            // look up the snapshots for this connection
            var files = fs.readdirSync( dir);
            files.sort(function(a, b) {
                return fs.statSync(dir + a).mtime.getTime()
                        - fs.statSync(dir + b).mtime.getTime();
            });

            res.render('connworkspace', {
                user : req.user,
                'connectionid' : connectionName,
                'filePrefix' : prefix,
                'files' : files
            });
        }catch(err){
            console.log(err);
            res.send(err);
        }

    });

};
