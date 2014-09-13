/*<![CDATA[*/
    		
var AddRegistrantModel = function() {
    			var self = this;
    			self.firstName = ko.observable("");
    			self.lastName = ko.observable("");
    			self.email = ko.observable("");
    			self.eventId = ko.observable("");
    			self.eventTitle = ko.observable("");
    			self.eventStartTime = ko.observable("");
    			self.eventEndTime = ko.observable("");
    			
    			self.getRegistrantByEmail = function(form) {
    				$.ajax({
    					url : '/registrant?email=' + self.email(),
    			        type: 'GET',
    			        success: function(data){
    			        		$('#getRegistrantResults').html(data);
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
	ko.applyBindings(new AddRegistrantModel());
	
});
    	/*]]>*/