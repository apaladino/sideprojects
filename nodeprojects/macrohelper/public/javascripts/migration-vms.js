/*<![CDATA[*/
    		
var GetAccountStatusModel = function() {
    			var self = this;
    			self.environments = ko.observableArray(['i2', 'i3', 'qa3', 'stage', 'live']);
    			self.statuses = ko.observableArray(['READY', 'ROLLED_BACK', 'STARTED', 'PENDING', 'COMPLETED']);
    			self.selectedEnvironment = ko.observable("");
    			self.accountKey = ko.observable("100000000000032202");
    			self.userKey = ko.observable("100000000000034566");
    			self.migrationStatus = ko.observable("");
    			self.soaUserKey = ko.observable("");
    			self.migrationWorkflow = ko.observable("");
    			self.getAccountStatusResults = function(form) {
    				$.ajax({
    					url : '/accountstatus/' + self.accountKey() + '?env=' + self.selectedEnvironment(),
    			        type: 'GET',
    			        success: function(data){
    			        	 $('#accountStatusResults').html(data);
    			        }
    				});
    			};
    			self.getUsersForAccountResults = function(){
    				$.ajax({
    					url : '/accountstatus/' + self.accountKey() + '/users?env=' + self.selectedEnvironment(),
    			        type: 'GET',
    			        success: function(data){
    			        	 $('#userStatusesForAccountResults').html(data);
    			        }
    				});
    			};
    			self.getInvitedUsersForAccountResults = function(){
    				$.ajax({
    					url : '/accountstatus/' + self.accountKey() + '/invitedusers?env=' + self.selectedEnvironment(),
    			        type: 'GET',
    			        success: function(data){
    			        	 $('#invitedUserStatusesForAccountResults').html(data);
    			        }
    				});	
    			};
    			self.getUserStatusResults = function(){
    				$.ajax({
    					url : '/userstatus/' + self.userKey() + '?env=' + self.selectedEnvironment(),
    			        type: 'GET',
    			        success: function(data){
    			        	 $('#getUserStatusResults').html(data);
    			        }
    				});
    			};
    			self.addUserStatusResults = function(){
    				
    				$.ajax({
    					url : '/userstatus/' + self.userKey() + '?env=' + self.selectedEnvironment(),
    					data: {migrationStatus: self.migrationStatus(),
    						soaUserKey: self.soaUserKey(),
    						migrationWorkflow: self.migrationWorkflow()},
    			        type: 'POST',
    			        success: function(data){
    			        	 $('#addUserStatusResults').html(data);
    			        }
    				});
    			}
    			
    		};
    		
$(document).ready(function(){    		
	ko.applyBindings(new GetAccountStatusModel());
	
});
    	/*]]>*/