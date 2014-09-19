# Idea for new service
###
### Service Name: Social Identity Service
###
### Description:  Service which provided a REST api that allows you to register any user to the identity service. 
###				  Once registered, the identity service will retrieve any social data for the user email address
###				  from social networks, like:
###					- LinkedIn
###					- Facebook
###					- Google+
###				  
###				  It will provide API calls to retrieve social network data for reporting purposes.
###				  It will also provide API calls to communicate and share social information with each registrant
###				  using the social network APIs.  




### Links:
			https://developer.linkedin.com/documents/
			http://developer.linkedinlabs.com/tutorials/jsapi_authentication/
			
https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=750c8k09ptha6k&scope=r_contactinfo%20r_fullprofile%20r_emailaddress&state=AP2014Maine&redirect_uri=http://localhost:8000/rest/linkedin/register



https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=AQRLyuM7UVMGNszK2s1jL7JSh_h7vRoJutVgxEjhBbFAU822Tmfh-GKz0L29UnJdrVGyj8PgDDFTIOc-RY5KUm1ooBMxRQwu8F8QS9aofWSLKulsi_Q&redirect_uri=http://localhost:8000/rest/linkedin/register&client_id=750c8k09ptha6k&client_secret=ECInL187WE272dAa



## LinkedIn accounts
#           andyp.g2w.0902.1@aol.com/secret11




### TWITTER

-- api console
https://dev.twitter.com/rest/tools/console

-- get user profile image
https://api.twitter.com/1.1/statuses/user_timeline.json

-- account settings
https://api.twitter.com/1.1/account/settings.json

