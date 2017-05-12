
function SearchUtil(macros){
	this.macros = macros;
	
	this.searchMacros = function(searchVal){
		
		console.log("macros: " + macros.length);
		try{
			var matches = [];
			
			for(var i=0; i < macros.length; i++){
				var lcname = macros[i].name.toLowerCase();
				var lcsearch = searchVal.toLowerCase();
				
				if(lcname.indexOf(lcsearch) >= 0){
					matches.push(macros[i]);
				}
			}
			return matches;
		}catch(err){
			console.log('Error: ' + err);
			exit;
		}
		
	};
}

module.exports = SearchUtil;