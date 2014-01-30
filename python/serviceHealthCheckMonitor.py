#! /usr/bin/env python

###### Service Health Check monitor 																	#######
######		Checks both nodes of a service by calling the service's healthcheck url via REST			#######
######		and will send an alert email if a sevice node is down.										#######


import sys
import urlparse
import httplib
import urllib
import time
from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText
from email.MIMEImage import MIMEImage



serviceNode1HealthCheckURL="http://someservice.somedomain.com:8080/jmx/beans?objectName=com.andyp.someservice:name=healthCheckMonitor,type=healthcheck"
serviceNode2HealthCheckURL="http://someservice.somedomain.com:8080/jmx/beans?objectName=com.andyp.someservice:name=healthCheckMonitor,type=healthcheck"

sender="<sender email>"
emailRecipients="<email recipients list>"
SMTPServer="<smtp server url"
path=/jmx/beans?objectName=com.andyp.someservice:name=healthCheckMonitor,type=healthcheck"
subject="Andy's handy service healthcheck monitor."

## verify service node one passes health check
response = urllib.urlopen(serviceNode1HealthCheckURL).read()
errors = ""

lines = response.split("\n")[1] 
print lines
line = lines.split("=")[1]
print line   

if line == "Health check status: GOOD":
    print "Service Node 1 is down"
    errors = errors + "<p>Failed: " + serviceNode1HealthCheckURL + ": " + line + "</p>";
     
## verify payment service node two health check
response = urllib.urlopen(serviceNode2HealthCheckURL).read();
print "Checking node two..."

lines = response.split("\n")[1] 
line = lines.split("=")[1]
print line   

if line != "Health check status: GOOD":
    print "Service Node 2 is down"
    errors = errors + "<p>Failed: " + serviceNode2HealthCheckURL + ": " + line + "</p>";

## If there are any errors then send out email.
if errors != "":
    print "Sending email notification"
    strFrom = sender
    strTo = emailRecipients

    # Create the root message and fill in the from, to, and subject headers
    msgRoot = MIMEMultipart('related')
    msgRoot['Subject'] = subject
    msgRoot['From'] = strFrom
    msgRoot['To'] = strTo
    msgRoot.preamble = subject

    # Encapsulate the plain and HTML versions of the message body in an
    # 'alternative' part, so message agents can decide which they want to display.
    msgAlternative = MIMEMultipart('alternative')
    msgRoot.attach(msgAlternative)

    msgText = MIMEText(subject)
    msgAlternative.attach(msgText)

    # We reference the image in the IMG SRC attribute by the ID we give it below
    msgText = MIMEText('<b>' + subject +'</b><br> ' + errors + '<br><img src="cid:image1"><br>', 'html')
    msgAlternative.attach(msgText)

    # This example assumes the image is in the current directory
    fp = open('alertEmailImage.png', 'rb')
    msgImage = MIMEImage(fp.read())
    fp.close()

    # Define the image's ID as referenced above
    msgImage.add_header('Content-ID', '<image1>')
    msgRoot.attach(msgImage)

    # Send the email 
    import smtplib
    smtp = smtplib.SMTP()
    smtp.connect(SMTPServer)
    smtp.sendmail(strFrom, strTo, msgRoot.as_string())
    smtp.quit()
    
    
print "Finished"    
         

