function JSUtil(){
	var debuggingEnabled = false;
	
	this.endsWith = function(str, suffix) {
		suffix = String(suffix);
		str = String(str);
		
		var subStr = str.substring(str.length-(suffix.length));
		
		return subStr == suffix;
    };

    this.startsWith = function(str, prefix){
        return str.indexOf(prefix) == 0;
    };

    this.debug = function(str){
        if(debuggingEnabled == true){
            console.log(str);
        }
    };
};

module.exports=JSUtil;