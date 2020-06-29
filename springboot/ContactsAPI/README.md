Simple Spring Boot application with a single Rest controller. 

# Package layout
  src/main/java/
     /com/apaladino/
        /controller - holds rest controllers
        /data - holds dao related objects 
        /error - holds custom exceptions
        /service - holds service layer objects
        
* Main driver class: DriverApp.java 

# To build application
mvn clean install

# To run tests
mvn clean test

# to start application
java -jar target/person-api-0.0.1-SNAPSHOT.jar

## example curl requests
curl -X GET "http://localhost:8080/person" -H "Accept: application/json"
curl -X DELETE "http://localhost:8080/person/3" 
curl -X PUT "http://localhost:8080/person" -H "Content-Type: application/json" -d '{ "id": 1, "firstName": "bob", "lastName": "smith"}'

#Notes
Java version: 11.0.7
Maven version: 3.6.0
application log file: application.log
        