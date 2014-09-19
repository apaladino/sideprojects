/*<![CDATA[*/
    		
var RegisterWithLinkedInModel = function(){
	var self = this;
	self.eventId = ko.observable("");
	self.eventTitle = ko.observable("");
	self.eventStartTime = ko.observable("");
	self.eventEndTime = ko.observable("");

    self.registerWithTwitter = function(){
        $.get( "/registrant/twitter", function( data ) {
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
        });
    };

	self.registerWithLinkedIn = function(){
		
  		
		IN.UI.Authorize().params({"scope":["r_basicprofile", "r_emailaddress"]}).place();

  		IN.Event.on(IN, 'auth', function () {
  		IN.API.Profile("me")
  		.fields("id,firstName,lastName,emailAddress,summary,industry,positions,picture-url")
  		.result(function (me) {
  		
  		var profile = me.values[0];
  		var firstName = profile.firstName;
  		var lastName = profile.lastName;
  		var email = profile.emailAddress;
  		var pictureUrl = profile.pictureUrl;
  		
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
		
	};
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
    			
    			self.getRegistrantByEmail = function(form) {

    				$.ajax({
    					url : '/registrant?email=' + self.email(),
    			        type: 'GET',
    			        success: function(data){
                            var registrant = JSON.parse(data);
                            $('#getRegistrantResultsJSON').html("<h5>JSON Response</h5> <br/>" + data);
                            $('#firstNameTxt').val(registrant.firstname);
                            $('#lastNameTxt').val(registrant.lastName);
                            $('#profilePic').attr("src", registrant.linkedInProfile.pictureUrl);
                            $('#getRegistrantResults').show();
    			        },
    			        error:function (xhr, ajaxOptions, thrownError){
    			    		$('#getRegistrantByEmailErrMsg').text(xhr.responseText)
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
    		
$(document).ready(function(){    		
	ko.applyBindings(new AddRegistrantModel(), addRegistrantsSpan);
	ko.applyBindings(new RegisterWithLinkedInModel(), socialRegistrationDiv);
	
});

// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        testAPI();
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
        document.getElementById('status').innerHTML = 'Please log ' +
                'into this app.';
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        document.getElementById('status').innerHTML = 'Please log ' +
                'into Facebook.';
    }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '747022911975099',
        cookie     : true,  // enable cookies to allow the server to access
        // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.1' // use version 2.1
    });

    // Now that we've initialized the JavaScript SDK, we call
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};

// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
        console.log('Successful login for: ' + response.name);
        document.getElementById('status').innerHTML =
                'Thanks for logging in, ' + response.name + '!';
    });
}
    	/*]]>*/