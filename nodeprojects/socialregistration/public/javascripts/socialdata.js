/*<![CDATA[*/

var loadAddRegistrantView = function(){
    $.ajax({
        url : '/views/addRegistrant',
        type : 'GET',
        success : function(data){
            $('#workspaceTitle').text("Add Registrant");
            $('#addRegistrantDiv').html(data).show();
        },
        error : function(data){
            genErrorMessage("Unexpected Error Retrieving View");
        }
    });
};
var loadCreateEventView = function(){
    $.ajax({
        url : '/views/createEvent',
        type: 'GET',
        success: function(data){
            $('#workspaceTitle').text("Create Event");
            $('#createEventDiv').html(data);
            $('#createEventDiv').show();
            },
        error: function(data){
            alert("error");
            genErrorMessage('Unexpected Error Retrieving View');
        }
    });
};

var loadSocialRegistrationView = function(){

    $.ajax({
        url : '/views/socialRegistration',
        type : 'GET',
        success : function(data){
            $('#workspaceTitle').text("Register For An Event");
            $('#socialRegistrationDiv').html(data).show();
        },
        error : function(data){
            genErrorMessage("Unexpected Error Retrieving View");
        }
    })
};

var loadGetRegistrantByEmailView = function(){
    $.ajax({
        url : '/views/getRegistrantByEmail',
        type : 'GET',
        success : function(data){
            $('#workspaceTitle').text("Get Registrant By Email Address");
            $('#getRegistrantByEmailDiv').html(data).show();
        },
        error : function(data){
            genErrorMessage("Unexpected Error Retrieving View");
        }
    });
};

var loadGetRegistrantByIDView = function(){
    $.ajax({
        url : '/views/getRegistrantByID',
        type : 'GET',
        success : function(data){
            $('#workspaceTitle').text("Get Registrant By ID");
            $('#getRegistrantByIDDiv').html(data).show();
        },
        error : function(){
            genErrorMessage("Unexpected Error Retrieving View");
        }
    });
};

var genErrorMessage = function(msg){
     var errMsg = '<div class="message-banner error-banner">' +
                     '<div class="banner-content">' +
                        '<p><strong>'+msg+'</strong>' +
                     '</div>' +
                   '</div>';
     $('#errorMsgDiv').html(errMsg).show().fadeOut(3600, function(){
                     $(this).html("").hide();
                 });
};

var RegisterWithLinkedInModel = function(){
	var self = this;
	self.eventId = ko.observable("");
	self.eventTitle = ko.observable("");
	self.eventStartTime = ko.observable("");
	self.eventEndTime = ko.observable("");

  	self.registerWithLinkedIn = function(){


		IN.UI.Authorize().params({"scope":["r_basicprofile", "r_emailaddress"]}).place();

  		IN.Event.on(IN, 'auth', function () {
  		console.log("auth");

  		IN.API.Profile("me")
  		.fields("id,firstName,lastName,emailAddress,summary,industry,positions,picture-url")
  		.result(function (me) {
        console.log("--" + JSON.stringify(me));

  		var profile = me.values[0];
  		var firstName = profile.firstName;
  		var lastName = profile.lastName;
  		var email = profile.emailAddress;
  		var pictureUrl = profile.pictureUrl;
        console.log("redirecting to /registrant/linkedin");

	  	$.ajax({
			url : '/registrant/linkedin',
			data: { eventId : self.eventId(),
				eventTitle : self.eventTitle(),
				eventStartTime : self.eventStartTime(),
				eventEndTime : self.eventEndTime(),
				firstName : firstName,
				lastName : lastName,
				email : email,
				pictureUrl : pictureUrl,
				profile: profile},
		    type: 'POST',
		    success: function(data){
		        	if(data && data.lastIndexOf("Error:", 0) === 0){
		        		var errMsg = data.substring(6);
		        		$('#socialRegistrationErrMsg').text(errMsg).show().fadeOut(3600,function(){ $(this).remove(); });
		        	}else{
		        		$('#socialRegistrationResults').text(data).show().fadeOut(3600,function(){
		        			$(this).remove();
		        			$('#socialRegistrationDiv').fadeOut(2000, function(){
		        				$('#infoDiv').show();
		        			});
		        			});
		        	}
		        }
			});

  		});
  		});

	}

	self.registerWithFacebook = function(){

        var eventInfo = new Object();
        eventInfo.eventId = self.eventId();
        eventInfo.eventTitle = self.eventTitle();
        eventInfo.eventStartTime = self.eventStartTime();
        eventInfo.eventEndTime = self.eventEndTime();

        FB.init({
            appId      : '747022911975099',
            cookie     : true,  // enable cookies to allow the server to access
            // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.1' // use version 2.1
        });

       FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                    // Logged into your app and Facebook.
                    fbRegister(eventInfo);
            } else {

                FB.login(function(response){
                     fbRegister(eventInfo);
                });
            }
        });
    }
};



var AddRegistrantModel = function() {
    			var self = this;
    			self.id = ko.observable("");
    			self.firstName = ko.observable("");
    			self.lastName = ko.observable("");
    			self.email = ko.observable("");
    			self.eventId = ko.observable("");
    			self.eventTitle = ko.observable("");
    			self.eventStartTime = ko.observable("");
    			self.eventEndTime = ko.observable("");

    			self.getRegistrantByID = function(form){
    				$('#getRegistrantByIDResults').text("");

    				$.ajax({
    					url : '/registrant/' + self.id(),
    			        type: 'GET',
    			        success: function(data){
    			        	$('#getRegistrantByIDResults').html(data);
    			        },
    			        error:function (xhr, ajaxOptions, thrownError){
    			    		$('#getRegistrantByIDErrMsg').text(xhr.responseText)
    			    			.show().fadeOut(3600,function(){ $(this).remove(); });
    			        }
    				});
    			};

    			self.addRegistrantSave = function(){

    				$.ajax({
    					url : '/registrant/',
    					data: {firstName: self.firstName(),
    						lastName: self.lastName(),
    						email: self.email(),
    						eventId: self.eventId(),
    						eventTitle: self.eventTitle(),
    						eventStartTime: self.eventStartTime(),
    						eventEndTime: self.eventEndTime()},
    			        type: 'POST',
    			        success: function(data){
    			        	if(data && data.lastIndexOf("Error:", 0) === 0){
    			        		var errMsg = data.substring(6);
    			        		$('#addRegistrantErrMsg').text(errMsg).show().fadeOut(3600,function(){ $(this).remove(); });
    			        	}else{
    			        		$('#addRegistrantSuccessMsg').text(data).show().fadeOut(3600,function(){
    			        			$(this).remove();
    			        			$('#addRegistrantDiv').fadeOut(2000, function(){
    			        				$('#infoDiv').show();
    			        			});
    			        			});
    			        	}

    			        }
    				});
    			}

    		};
    		
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}


// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


function fbRegister(eventInfo) {
    var fields='first_name,last_name, about, age_range, bio, birthday, ' +
            'education, email, link, locale, location, political, timezone, work';
    var results = [];

    FB.api('/me', { fields: fields}, function(response) {
        console.log('Successful login for: ' + response.name);
        getFbProfilePic(response, eventInfo);
    });

}

function getFbProfilePic(fbProfile, eventInfo){
    FB.api('/me/picture', function(response) {

            $.ajax({
            			url : '/registrant/facebook',
            			data: { eventInfo : eventInfo,
            				fbProfile : fbProfile,
            				fbPicture : response},
            		    type: 'POST',
            		    success: function(data){
            		        	if(data && data.lastIndexOf("Error:", 0) === 0){
            		        		var errMsg = data.substring(6);
            		        		$('#socialRegistrationErrMsg').text(errMsg).show().fadeOut(3600,function(){ $(this).remove(); });
            		        	}else{
            		        		$('#socialRegistrationResults').text(data).show()/*.fadeOut(3600,function(){
            		        			$(this).remove();
            		        			$('#socialRegistrationDiv').fadeOut(2000, function(){
            		        				$('#infoDiv').show();
            		        			});
            		        			})*/;
            		        	}
            		        },
            		    error: function(data){
            		        var errMsg = "Unexpected Error Occurred.";
            		        $('#socialRegistrationErrMsg').text(errMsg).show().fadeOut(4600,function(){ $(this).remove(); });
            		    }
            			});


        });

$(document).ready(function(){
	ko.applyBindings(new RegisterWithLinkedInModel(), socialRegistrationDiv);

});

}
    	/*]]>*/