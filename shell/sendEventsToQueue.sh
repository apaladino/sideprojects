#!/bin/bash

#####																								#####
#####       		sendEventsToQueue  shell script 												#####
#####		Reads a list of users from text file and generates a JSON post to a queueing service	#####
#####																								#####


userKey=""
accountKey=""
parition=""
email=""
company=""
region=""
status="COMPLETED"

targetEnv=""
targetQsvc=""
queueName="user.Event.QueueName"

# queue svc hosts
devQsvc="dev.somedomain.com"
qaQsvc="qa.somedomain.com"
stageQsvc="stage.somedomain.com"
liveQsvc="live.somedomain.com"


if [ "$1" != "" ]; then
	FILENAME=$1
else
    echo "USAGE: sendUserEvents <users.properties file> <environment [ed,qa,stage,live]>"
    exit
fi

if [ "$2" != "" ]; then
   targetEnv="$2"
else
    echo "USAGE: sendUserEvents <users.properties file> <environment [ed,qa,stage,live]>"
    exit
fi

if [ $targetEnv == "ed" ]; then
	targetQsvc="$devQsvc"
	echo $targetQsvc
fi

if [ $targetEnv == "rc" ]; then
	targetQsvc="$qaQsvc"
fi

if [ $targetEnv == "stage" ]; then
	targetQsvc="$stageQsvc"
fi

if [ $targetEnv == "live" ]; then
	targetQsvc="$liveQsvc"
fi

declare -a LINE_TOKENS

count=0
cat $FILENAME | while read LINE
do
       let count++
       echo "$count $LINE"
       
       echo ARR=`echo $LINE | cut -d \, -f 1`

       userKey=`echo $LINE | cut -d \, -f 1`
       accountKey=`echo $LINE | cut -d \, -f 2`
       email=`echo $LINE | cut -d \, -f 3`
       company=`echo $LINE | cut -d \, -f 4`
       region=`echo $LINE | cut -d \, -f 5`
       system=`echo $LINE | cut -d \, -f 6`
       
       echo "Sending user event for user: " + $email
 	   echo curl -H "Content-Type: application/json" -X POST "http://$targetQsvc/queue/rest/$queueName?ttl=600000" -d "{\"systemA\":{\"userKey\":$userKey,\"accountKey\":$accountKey,\"system\":\"$system\",\"email\":\"$email\"},\"systemB\":{\"Company\":$company,\"Region\":region},\"status\":\"$status\"}"
             
       curl -v -H "Content-Type: application/json" -X POST "http://$targetQsvc/queue/rest/$queueName?ttl=600000" -d "{\"systemA\":{\"userKey\":$userKey,\"accountKey\":$accountKey,\"system\":\"$system\",\"email\":\"$email\"},\"systemB\":{\"Company\":$company,\"Region\":$region},\"status\":\"$status\"}"

sleep 1       
       
done

echo "Finished sending user events."