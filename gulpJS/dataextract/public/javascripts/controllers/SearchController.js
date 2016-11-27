app.controller("SearchController", ['$scope', function($scope){
	$scope.title="Search Medical Records";
	$scope.searchType = "Personal";
	$scope.searchQuery = "";
	$scope.patientInfoOptions = ["pid", "firstName",	"middleName", "lastName", "phone", "dob", "residence"];
	$scope.patientHistoryOptions = ["phid","pid","doctors"];
    $scope.useRegex = false;
	$scope.getSearchOptions = function(){
		
		if($scope.searchType === 'Personal')
			return $scope.patientInfoOptions;
		else
			return $scope.patientHistoryOptions;
	}
	
	$scope.searchField = "";
	
	$scope.results = [];
	
	$scope.search = function(){
		var val = $scope.searchQuery;
		
		if(!(val) || val.length == 0){
			showErrorMsg("Please enter in a search value.");
			return;
		}
		
		if($scope.searchField.length == 0){
			showErrorMsg("Please selet a search field.");
			return;
		}
		
		var url = "http://localhost:8000/patientInfo/search?searchType=" + $scope.searchType +"&searchField="+$scope.searchField
			+"&searchQuery=" + $scope.searchQuery + "&useRegex=" + $scope.useRegex;
		
		$.get(url, function(data, status){
	        $("#results").html(data);
	    });
		
	};
	$scope.clear = function(){
		$scope.searchQuery = "";
	}

}]);

function showErrorMsg(msg){
	$("#errorMsgDiv").html(msg).show().fadeOut(5000, function(){ $(this).hide();});
}

function showSuccessMsg(msg){
	$("#successMsg").html(msg).show().fadeOut(5000, function(){ $(this).hide();});
}