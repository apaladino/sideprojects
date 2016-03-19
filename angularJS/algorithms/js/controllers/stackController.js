app.controller("StackController", ['$scope', function($scope){
	$scope.title="Stack Example";
	$scope.stack = [17,2,5];
	$scope.newItem = "";
	$scope.push = function(){
		var val = $scope.newItem;
		
		if(val != ""){
			$scope.stack.unshift(val);
			$scope.newItem = "";
		}
	};
	$scope.pop = function(){
		if($scope.stack.length > 0){
			$scope.stack.shift();
		}
	}

}]);
