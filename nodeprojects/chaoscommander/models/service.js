var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var Service = new Schema({
    title: String,
    host: String,
    serviceName: String,
    startCommand: String,
    stopCommand: String
});


module.export = mongoose.model('Service', Service);