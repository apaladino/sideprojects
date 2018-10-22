This is an example LAMP stack built with docker compose.

THe docker-compose.yml file lists out 3 services, maria_db, 
phpmysqladmin, and api. It will boot up each container, and then
start a django server on the api container.  

Prereqs: 
  * Download and install Docker:  https://docs.docker.com/docker-for-mac/
  * For mac users, make sure the default mysql server is not running, otherwise
    you will see a port conflict error when you start the maria_db container.


To build and run:  
   1. Install docker 
   2. cd into root directory  
   3. run:   docker-compose rm -v
      run:   docker-compose build
      run:   docker-compose up
   4. Open a browser and navigate to: http://localhost:8000
   5. For the django admin page, navigate to: http://localhost:8000/admin
   



