var request = require('request');

exports.loadWorkspace = function(req, res, next) {

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
};