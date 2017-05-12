function Macro(name, fullName, filename, comments, body, type){
	this.name = name;
	this.fullName = fullName;
	this.filename = filename;
	this.comments = reverse(comments);
	this.body = reverse(body);
	this.type = type;
	
	function reverse(arr){
		var revArr =  [];
		
		for(var i= arr.length -1; i >= 0; i--){
			revArr.push(arr[i]);
		}
		
		if(String(revArr[0]).indexOf("#end") == 0){
			revArr[0] = "<br/>";
		}
		return revArr;
	};
};


module.exports = Macro; 