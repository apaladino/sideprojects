var passport = require('passport');
var Account = require('./models/account');
var Connection = require('./models/account');
var title = 'DB Snapshot Tool';

module.exports = function (app) {

  app.get('/', function (req, res) {
      res.render('index', { user : req.user, 'title' : title });
  });

  app.get('/register', function(req, res) {
      res.render('register', {'title' : title });
  });

  app.post('/register', function(req, res) {
    Account.register(new Account({ username : req.body.username }), req.body.password, function(err, account) {
        if (err) {
        	console.log("Error occurred while trying to register with account: " + err);
            return res.render('register', { account : account, 'title' : title, 'errMsg' : err.message});
        }

        passport.authenticate('local')(req, res, function () {
        	res.redirect('/workspace');
        });
    });
  });

  app.get('/workspace', function(req, res,next){
	  
	  if(!req.user){
		  console.log("User not found in session, redirecting to '/'");
		  res.statusCode = 302; 
	      res.setHeader("Location", "/");
	      res.end();
	      return;
	  }
	  
	  res.render('workspace', { user : req.user, 'title' : title });
	  
  });

  app.get('/login', function(req, res) {
      res.render('login', { user : req.user, 'title' : title });
  });

  app.post('/login', passport.authenticate('local'), function(req, res) {
      res.redirect('/workspace');
  });

  app.get('/logout', function(req, res) {
      req.logout();
      res.redirect('/');
  });

  app.get('/ping', function(req, res){
      res.send("pong!", 200);
  });
  
  app.get('/connections/add', function(req, res){
  		
  		
  });

};
