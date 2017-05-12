var fs = require('fs');
var JSUtilObj = require('./JSUtil');

var jsUtil = new JSUtilObj();

function FileLoader(){
    var masterDir = '/ATMCodeBase';
    var targetDirectories = ['Suite', 'Platform'];
    var excludeDirectories = ['/Suite/build', '/Platform/build', '/js/jquery/', 'tiny_mce', '/js/jstree/'
    , '/tinymce_plugins/', '/microapps/', '/build/test/', '/lib/test', '/bootstrap/'];
    var excludedFiles = ['-min.js', '.min.js', '/microapps', '/jquery']; //, 'tiny_mce', '/build/'];
    var debuggingEnabled = false;
   
    

    this.loadFileNames = function(dir){
        console.log("Getting file names.");

        return getFiles(dir, []);
    };



    function getFiles(dir, files_){
        
        files_ = files_ || [];
        var files = fs.readdirSync(dir);
        for (var i in files){
            var name = dir + '/' + files[i];


            if (fs.statSync(name).isDirectory()){
                // skip hidden directories
                if( name.indexOf('/.') > 0){
                    continue;
                }

                // skip directory if not in the target directories
                if(name.indexOf(masterDir + '/' + targetDirectories[0]) < 0 &&
                    (name.indexOf(masterDir + '/' + targetDirectories[1]) < 0)){
                    continue;
                }

                // skip directory if is a excluded directory
                var isExcludedDir = false;
                for(var i =0; i < excludeDirectories.length; i++){
                    if(name.indexOf([excludeDirectories[i]]) > 0){
                        isExcludedDir = true;
                        break;
                    }
                }
                if(isExcludedDir == true){
                    jsUtil.debug("skipping directory: " + name);
                    continue;
                }else{
                    getFiles(name, files_);    
                }
                
            } else {

                // include all js files
                if(jsUtil.endsWith(name, ".js") || (name.indexOf("/VM_") > 0 && jsUtil.endsWith(name, ".vm"))) {

                     // exclude files in excludedFiles list
                    var isExcludedFile = false;
                    for(var i=0; i < excludedFiles.length; i++){
                        if( name.indexOf(excludedFiles[i]) > 0){
                            jsUtil.debug("skipping file: " + name);
                            isExcludedFile = true;
                            break;
                        }
                    }
                    if(isExcludedFile){
                        jsUtil.debug("skipping file: " + name);
                        continue;
                    }else{
                        jsUtil.debug("" + name.indexOf("\/build\/") + ", " + isExcludedFile + ", adding file: " + name);
                        files_.push(name);    
                    } 

                        
                }
                
            }
        }


        return files_;
    };


};

module.exports = FileLoader;