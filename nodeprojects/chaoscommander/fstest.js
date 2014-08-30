var passport = require('passport');
var Account = require('./models/account');
var Connection = require('./models/connection');
var Table = require('./models/table');
var fs = require('fs');
var _exec = require('child_process').exec;
var title = 'DB Snapshot Tool';
var JsDiff = require('diff');



var dir = '/Users/apaladino/dev/nodeprojects/dbsnapshot/snapshots/G2MAPALG2M/';
var connectionName = 'G2MAPALG2M';


 files = fs.readdirSync(dir);
		files.sort(function(a, b) {
			return fs.statSync(dir + a).mtime.getTime()
					- fs.statSync(dir + b).mtime.getTime();
		});
		
console.log(files);