var gulp = require('gulp');
var exec = require('exec');
var gutil = require('gulp-util');
mkdirs = require('mkdirs');
var nodemon = require('gulp-nodemon');
var PatientHistoryService = require('./service/PatientHistoryService');


var paths = { "dbDir": "C:/data/db", "dbLogs": "C:/tmp"};

/*
 * Reloads the patient history data from flat file into MongoDB
 */
gulp.task('reloadPatientHistory', function(){
	console.log("Patient history data file has changed. reloading patient history.");
	PatientHistoryService.reloadPatientHistory();
});

/*
 * This task will set up a gulp watch cron and call the reloadPatientHistory task if the data/history.csv file changes.
 */
gulp.task('watch', function () {
   gulp.watch('data/history.csv', ['reloadPatientHistory']);
});

/*
 * Stub task for running unit and integration tests. 
 */
gulp.task('test', function(){
	console.log("Running unit and integration tests");
});

/*
 * Main task. it will call the watch task, and then start the Express JS server
 */
gulp.task('start', ['watch', 'test'], function () {
  nodemon({
    script: 'app.js'
  , ext: 'js html'
  , env: { 'NODE_ENV': 'development' }
  })
})