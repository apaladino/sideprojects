#!/usr/bin/env bash

# Once the docker containers are up and running, you can run this script to open a terminal
# to the backend_api container if you need to troubleshoot anything

docker exec -ti `docker ps | grep backend_api | awk '{ print $1; }'` bash
