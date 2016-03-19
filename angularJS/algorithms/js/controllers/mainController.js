app.controller("MainController", ['$scope', function($scope){
	
	$scope.mode = "Queue";
	$scope.setMode = function(mode){
		console.log("set mode: " + mode);
		$scope.mode = mode;
	}
	$scope.isMode = function(val){
		console.log("isMode");
		return $scope.mode === val;
	}
	
}]);