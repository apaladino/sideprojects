var Registrant = require('../../models/registrant');
var Event = require('../../models/event');
var FacebookProfile = require('../../models/facebookProfile');


exports.createNewFacebookProfile = function(firstName, lastName, email, fbLink, locale, timezone, pictureUrl,
        ageRange){

    return new FacebookProfile({
        emailAddress : email,
           firstName : firstName,
           lastName : lastName,
           pictureUrl : pictureUrl,
           ageRange : age_range,
           fbLink : fbLink,
           locale : locale,
           timezone : timezone
    });
}

exports.createNewRegistrant = function(firstName, lastName, email) {
    return new Registrant({
        firstname: firstName,
        lastName: lastName,
        email: email
    });
}

exports.createNewEvent = function(registrantKey, eventId, eventTitle, startTime, endTime) {
    return new Event({
        _registrantKey: registrantKey,
        eventId: eventId,
        eventTitle: eventTitle,
        startTime: startTime,
        endTime: endTime
    });
}

exports.createNewLinkedInProfile = function(registrantKey, profile) {
    return new LinkedInProfile({
        _registrantKey: registrantKey,
        firstName: profile.firstName,
        lastName: profile.lastName,
        emailAddress: profile.emailAddress,
        pictureUrl: profile.pictureUrl,
        industry: profile.industry,
        summary: profile.summary
    });
}

exports.createLinkedInPositions = function(profileKey, profile) {

    var positions = profile.positions.values;
    var linkedInPositions = [];

    var companyIds = [];

    for (var i = 0; i < positions.length; i++) {

        // check for duplicate company id

        console.log("Position: " + JSON.stringify(positions[i].company));

        var companyProfile = new LinkedInCompanyProfile({
            _profileKey: profileKey,
            industry: positions[i].company.industry,
            name: positions[i].company.name,
            size: positions[i].company.size,
            ticker: positions[i].company.ticker,
            type: positions[i].company.type,
            isCurrent: positions[i].isCurrent,
            startDate: positions[i].startDate,
            title: positions[i].title,
            summary: positions[i].summary
        });
        console.log("Saving profile");

        companyProfile.save(function (err) {
            if (err) {
                console.log("Unable to save company profile. " + err);
                return;
            }

        });

        console.log("company profile saved. " + JSON.stringify(companyProfile));
        linkedInPositions.push(companyProfile);

        break;

    }

    return linkedInPositions;
}

exports.isUserRegisteredForEvent = function(registrant, eventId){
    var isRegistered = false;
    if (registrant.events && registrant.events.length > 0) {
        for (var i = 0; i < registrant.events.length; i++) {
            if (registrant.events[i].eventId === eventId) {
                isRegistered = true;
                break;
            }
        }
    }
    return isRegistered;
};
