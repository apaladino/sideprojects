var mongoose = require('mongoose'),
    Schema = mongoose.Schema,
    passportLocalMongoose = require('passport-local-mongoose');

var Account = new Schema({
	username: String,
    password: String,
    services : [{ type: Schema.Types.ObjectId, ref: 'Service' }]
});

Account.plugin(passportLocalMongoose);

module.exports = mongoose.model('Account', Account);
