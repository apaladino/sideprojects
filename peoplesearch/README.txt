--
- Notes
- 
  * I was originally going to make a quick HttpServlet and mock out the data layer, but I was thinking that I'd never do this in the real world. This project is pretty 
	much what I would do if I was asked to create a new web page or REST API which does that was asked.  

  * This project uses Apache maven (lvl 3 & above), and leverages some of the new Spring 4 functionality, like a simple embedded HSQL data source, and spring boot.  

  * I also decided to just return a JSON response rather than wire in a view resolver and add a web page. I just didn't want to spend more time than necessary to do this.
  
  * I diss-agree with the expected design of implementing a sort method. Since we are using a back end data store, which most likely will be a relational database 
    server, then it would be much faster to pass the sortField directly to the DataManager and add a sort to the Criteria directly.
    
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