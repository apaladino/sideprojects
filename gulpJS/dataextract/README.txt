--------
General
--------
This is a simple search page webapp which pulls from two different datastores: 
   1) a flat file: data/history.csv
   			* there is also a data/patients.csv file, which is used to load some mock patient data into MongoDB for testing.
   2) a Mongodb database.
   
---------
Features:
---------
	- GulpJS: is used to compile and build the webapp
		- Look at gulpfile.js, there are three main tasks: start and watch. 
			- start: the start task will start the Express http server. Since it depends on the watch task, it will first call watch
			- watch: the watch task will set up a gulp watch cron to handle any file changes for the flat data file: data/history.csv. 
					 *If data/history.csv's content changed, then it will call the reloadPatientHistory task to reload the patient history data
			- reloadPatientHistory: This task will clear out the patientHistory collection in the Mongo database, and then reload the patient 
			 					    history records from data/history.csv into MongoJS	 
	- NodeJS: Underlying api to running Express server
	- ExpressJS: Easy to use NodeJS web application framework
		- models: 
			- models/PatientInfo.js   (Patient Information - presumably populated from webapp connected to MongoDB server)
			- modesl/PatientHistory   (Patient History - populated from flat file: data/history.csv)
		- main js file: app.js
		- main view: views/index.ejs
	- AngularJS: Web framework used for display & data binding
		- main controller: public/Javascripts/SearchController.js
	- Less: css generation
	- JQuery: Display, etc

----------------------------------	
How to set this up and run locally
----------------------------------
[setup]
1. Download & Install NodeJS and the npm tool. 
    https://docs.npmjs.com/getting-started/installing-node
2. Download & Install MongoJS 
	https://www.mongodb.com/download-center?filter=enterprise
	
[running]
1. start mongodb server
	1. [Windows] - open command prompt
	2. type mongod
2. Open command prompt
3. Navigate to dataexport directory
4. type: npm install
5. type: npm install -g gulp
6. type: gulp start
	* If gulp start fails due to missing modules, install locally with: npm install --save-dev <module name>
7. Open browser and go to http://localhost:8000