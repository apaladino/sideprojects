var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var Connection = new Schema({
  name: String,
  url: String,
  username: String,
  password: String
});


module.export = mongoose.model('Connection', Connection);