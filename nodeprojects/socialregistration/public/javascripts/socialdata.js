/*<![CDATA[*/

var loadEventSettingsForm = function(){
    $.ajax({
        url : '/views/eventSettings',
        type : 'GET',
        success : function(data){
            $('#workspaceTitle').text("Event Settings");
            $('#eventSettingsDiv').html(data).show();
        },
        error : function(data){
            genErrorMessage("Unexpected Error Loading event settings view.");
        }
    });
}

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

     $('#errorMsgDiv').text(msg);
     $('#errorMsgContainer').show().fadeOut(3600, function(){
                     $(this).remove();
        });
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

}


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


/*]]>*/