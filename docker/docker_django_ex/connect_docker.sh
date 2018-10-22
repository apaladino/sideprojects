#!/usr/bin/env bash



docker exec -ti `docker ps | grep backend_api | awk '{ print $1; }'` bash
