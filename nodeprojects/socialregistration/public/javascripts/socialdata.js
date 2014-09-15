/*<![CDATA[*/
    		
var RegisterWithLinkedInModel = function(){
	var self = this;
	self.eventId = ko.observable("");
	self.eventTitle = ko.observable("");
	self.eventStartTime = ko.observable("");
	self.eventEndTime = ko.observable("");
	
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
		        		$('#registerWithLinkedInErrMsg').text(errMsg).show().fadeOut(3600,function(){ $(this).remove(); });
		        	}else{
		        		$('#registerWithLinkedInResults').text(data).show().fadeOut(3600,function(){ 
		        			$(this).remove();
		        			$('#registerWithLinkedInDiv').fadeOut(2000, function(){
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
	ko.applyBindings(new RegisterWithLinkedInModel(), registerWithLinkedInDiv);
	
});
    	/*]]>*/