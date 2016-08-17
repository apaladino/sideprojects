--
-- Requirements
-- 
1. Maven v3 and above
2. Java SDK version 7 and above

-- 
-- How to launch (options)
--
1. From command line:  
	a. type: mvn spring-boot:run
	b. Open browser and go to http://localhost:8080/people?sortField=firstName&ascending=true
	
2. You can also run the unit test: TestPersonController

3. You could also package up the webapp and copy it to your local J2EE Servlet Container's webapp directory
   ex:  mvn clean package
