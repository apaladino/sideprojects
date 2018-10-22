#!/usr/bin/env bash

echo "[start_python_server.sh] -- STARTING DJANGO SERVER"


while ! mysql --host=maria_db --user=root --password=docker -e "select count(*) from relapp.backendapp_user" > /dev/null; do echo waiting for maria_db; sleep 3; done;

python manage.py migrate
python manage.py runserver 0.0.0.0:8000