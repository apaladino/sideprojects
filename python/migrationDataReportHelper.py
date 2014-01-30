#! /usr/bin/env python

import sys
import urllib2
import json

#####					MigrationHelper												#####
#####  	Pulls migration related data from the migration service via rest api and 	#####
#####	converts to a CSV file.														#####

apiPath="/Service/migration/migrationStatus?migrationStatus={0}&migrationStartTime={1}&limit={2}&offset={3}"
env="live"
migrationStatus="COMPLETED" 
startTime="2013-01-01-01:00:00"
limit=10000000
offset=0
partition=4
envMap = {'dev': "https://<dev-env>", 'qa': "https://<qa-env>",
       'stage': "https://<stage-env>", 'live': "https://<live-env>"}
csvFileName='dataresults.csv'
printToCSVFile = True

# usage migrationHelper dev 4 COMPLETED 2013-02-08-01:00:00
sys.argv = ["migrationHelper", "live",4,"ROLLED_BACK", "2013-01-01-01:00:00", 5000, "true"] 

if len(sys.argv) >= 2 and sys.argv[1] != "":
    env = str(sys.argv[1])

if len(sys.argv) >= 3 and sys.argv[2] != "":
    partition = str(sys.argv[2])
    
if len(sys.argv) >= 4 and sys.argv[3] != "":
    migrationStatus = str(sys.argv[3])

if len(sys.argv) >= 5 and sys.argv[4] != "":
    startTime=str(sys.argv[4]) 

if len(sys.argv) >= 6 and sys.argv[5] != "":
    limit = int(str(sys.argv[5]))
 
if len(sys.argv) >= 7 and sys.argv[6] != "" and (sys.argv[6] == "false" or sys.argv[6] == "no"  ):
    printToCSVFile=False  
  

# build the http get request url
requestUrl = envMap[env].format(partition) 
requestPath=apiPath.format(migrationStatus, startTime, limit, offset)

print "migrationHelper request URL: "+requestUrl + requestPath
response = urllib2.urlopen(requestUrl + requestPath)

if response.code != 200: 
    print "Invalid response code returned: " + response.code
    print response.read()
    sys.exit(0)
    

content = response.read()

if str(printToCSVFile) == "False":
    print content
    sys.exit(0)
    
print "Writing response to CSV file: " + csvFileName
jsonObj = json.loads(content)
count = len(jsonObj)
if count > 0:
    firstObj = jsonObj[0];
    keys = firstObj.keys()
    f = open(csvFileName, 'wb') 
    for key in keys:
        f.write(str(key).replace(",", "") + ",")
    f.write("\n")
               
    for r in jsonObj:
        for value in r.values():
            val = str(value).replace(",", "")
            val = val.replace("\r", "")
            replaceToken = """

Migration:"""
            val = val.replace(replaceToken, " Migration:")
            replaceToken = """
Unable"""
            val = val.replace(replaceToken, " Unable")
            replaceToken = """
"""
            val = val.replace(replaceToken," ")
            
            if str(val).startswith('type=org.springframework.web.client.ResourceAccessException message'):
                continue 
            f.write(val + "," )
        f.write("\n")
print "Finished."
