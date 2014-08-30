var passport = require('passport');
var Account = require('./models/account');
var Connection = require('./models/connection');
var Table = require('./models/table');
var fs = require('fs');
var _exec = require('child_process').exec;
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
	  
	  console.log("looking up user: "+ JSON.stringify(req.user));
	  res.redirect('/workspace');
  });

  app.get('/logout', function(req, res) {
      req.logout();
      res.redirect('/');
  });

  app.get('/ping', function(req, res){
      res.send("pong!", 200);
  });

  app.post('/connections', function(req, res){
	  console.log('Create connection request revieved: ' + JSON.stringify(req.body) + ", userkey: " + JSON.stringify(req.user));

	  var connectionName = req.body.connectionName;
	  var url = req.body.url;
	  var userName = req.body.userName;
	  var password = req.body.password;
	  
	  var connection = new Connection({ _accountKey : req.user._id,
		  name: connectionName, 
		  url : url, 
		  username: userName, 
		  password: password
	  });
	  connection.save(function(err){
		  if(err){
			  console.log("unable to save connection.");
		  }
	  });
	  
	  req.user.connections.push(connection);
	  req.user.save();
	  
	  res.send(connection);
	  
  });
  
  app.get('/connection/workspace', function(req,res){
	  
	  var connectionName= req.query.id;
	  var prefix = connectionName;
	  
	  // look up the snapshots for this connection
	  var dir = './snapshots/'; 
	  var files = fs.readdirSync(dir);
	  files.sort(function(a, b) {
	                 return fs.statSync(dir + a).mtime.getTime() - 
	                        fs.statSync(dir + b).mtime.getTime();
	             });
	  
	  var snapshotfiles = [];
	  for(var i=0; i < files.length; i++){
		  if(files[i].indexOf(prefix) == 0){	
			 snapshotfiles.push(files[i]); 
		  }
	  }
	  
	  res.render('connworkspace', { user : req.user, 
		  'connectionid' : connectionName,
		  'filePrefix' : prefix, 
		  'files' : snapshotfiles});
  
  });
  
  app.post('/delete/snapshots/', function(req,res){
		console.log("delete request:" + JSON.stringify(req.body));
		var snapshots = req.body.snapshots;
		var errors = [];
		
		var dir = './snapshots/'; 
		try{
			for(var i=0; i < snapshots.length; i++){
				var filePath = dir + '/' + snapshots[i];
		        console.log("file  " + filePath);
		        
				if (fs.statSync(filePath).isFile()){
					console.log("Deleting file: " + filePath);
		            fs.unlinkSync(filePath);
				}else{
					errors.push(snapshots[i]);
				}
			}
			
			if(errors.length == 0){
				res.send(200);
			}else{
				res.send(500);
			}
			
		}catch(err){
			res.send(500);
		}
	});
  
  app.post('/create/snapshots', function(req, res){
	
	  console.log("create snapshot request: " + JSON.stringify(req.body));
	  var connectionId = req.body.connectionid;
	  
	  var connection = "";
	  // find connection 
	  for(var i=0; i < req.user.connections.length; i++){
		 if(req.user.connections[i].name == connectionId ){
			 connection = req.user.connections[i];
		 } 
	  }
	  
	  console.log("found connection: " + JSON.stringify(connection));
	  var url = connection.url;
	  var username = connection.username;
	  var password = connection.password;
	  
	  // take snapshot
	  //    'java -jar schema-snapshot-1.0-SNAPSHOT-jar-with-dependencies.jar "jdbc:oracle:thin:@//devracdb-scan.qai.expertcity.com:1521/dev" "APALADINOG2WSOA3" "APALADINOG2WSOA3" "APALADINOG2WSOA3-snapshot"'
	  var command = 'java -jar schema-snapshot-1.0-SNAPSHOT-jar-with-dependencies.jar "' + url + '" "' + username +'" "'+password+'" "'+connectionId+'"';
	  console.log("command: " + command);	  
	  _exec( command, 
	      function(e, stdout, stderr){
	          if(e){
	        	  console.log("Unexpected error: " + e);
	        	  res.send(500);
	          }
	          console.log(stdout);
	          res.send(200);
	      } );
  });
  
  app.get('/download/snapshot', function(req,res){
	 
	  var connectionName= req.query.id;
	  var prefix = connectionName;
	  var fileName = "";
	  // look up the snapshots for this connection
	  var dir = './snapshots/'; 
	  var files = fs.readdirSync(dir);
	  for(var i=0; i < files.length; i++){
		  if(files[i].indexOf(prefix) == 0){	
			 fileName = files[i];
			 break;
		  }
	  }
	
	  var file = fs.createWriteStream(__dirname + '/snapshots/' + fileName);
	  res.writeHead(200, {'Content-Type': 'application/octet-stream', 'Content-Disposition': 'attatchment; filename=' + connectionName});
	  res.pipe(file);
	  file.on('finish', function() {
	      file.close();
	    });
  });
  
  app.get('/snapshots/diff', function(req, res){
	 
	  console.log("Diff request recieved ");
	  
	  var id1 = req.query.id1;
	  var id2 = req.query.id2;
	  
	  // look up the files given the ids
	  var fileName1 = "";
	  var fileName2 = "";
	  var dir = __dirname + '/snapshots/'; 
	  var files = fs.readdirSync(dir);
	  for(var i=0; i < files.length; i++){
		  if(files[i].indexOf(id1) == 0){	
			 fileName1 = files[i];
		  }
		  if(files[i].indexOf(id2) == 0){
			  fileName2 = files[i];
		  }
		  if(fileName1 != "" && fileName2 != ""){
			  break;
		  }
	  }
	  
	  var tables1 = [];
	  var tables2 = [];
	  
	  try{
		  var eol = require('os').EOL;
		  var filename1 = __dirname + '/snapshots/' + fileName1;
		  var file1 = fs.readFileSync(filename1, 'utf8');
		  var lines1 = file1.split('\r');
		  var file2 = fs.readFileSync(__dirname + '/snapshots/' + fileName1, 'utf8');
		  var lines2 = file2.split('\r');
		  
		  console.log("lines1 " + lines1.length);
		  
		  // load tables for snapshot1
		  var tableFound = false;
		  var currentTable = '';
		  
		  for(var i=0; i < lines1.length; i++){
			 if(lines1[i].indexOf('######	') == 0){
				 tableFound = true;
				 var newtable = new Table();
				 newtable.tableName = lines1[i].split('	')[1];
				 currentTable = newtable;
			 } 
			 if(lines1[i].indexOf(' ') == 0){
				 tableFound = false;
				 tables1.push(currentTable);
				 currentTable='';
			 }
		  }
	  }catch(err){
		  console.log("Error loading snapshot file: " + err);
	  }
	  
  
	  
	  console.log(id1 + "\r" + id2);
	  res.render('diff', { user : req.user, 
		  'id1' : id1,
		  'id2' : id2
		  });
	  
  });
  
  
};

