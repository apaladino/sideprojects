var gulp = require('gulp');
var browserSync = require('browser-sync');
var reload = browserSync.reload;
var fs = require('fs');

var masterDir = '/ATMCodeBase';
var targetDirectories = ['Suite', 'Platform'];


gulp.task('default', function(){

   var str = '/ATMCodeBase/Suite/ui/dist/lib/js/tiny_mce/plugins/table/langs/fr_dlg.js';

   console.log(str.indexOf('/tiny_mce') > 0);
});

