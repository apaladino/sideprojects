/*
 * Linked In Company Profile
 */
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var LinkedInCompanyProfileSchema = new Schema({
    _profileKey : { type: String, ref: 'LinkedInProfile' },
    industry : String,
    name : String,
    size : String,
    ticker : String,
    type : String,
    isCurrent : Boolean,
    startDate : Date,
    title : String,
    summary : String

});


module.exports = mongoose.model('LinkedInCompanyProfile', LinkedInCompanyProfileSchema);