app.controller("SelectionSortController", ['$scope', function($scope){
	$scope.title="Selection Sort Example";
	$scope.array = [17,5,2,9,21,7, -4, 33, 14];
	$scope.sort = function(){
		
		// start with first index array then find the index of
		// the smallest value and swap
		for(var i=0; i < $scope.array.length; i++){
			
			var temp;
			var minIndex = i;
			for(var j=i+1; j < $scope.array.length; j++){
				if($scope.array[j] < $scope.array[minIndex]){
					minIndex = j;
				}
			}
			
			// swap values
			console.log("Swapping " + $scope.array[i] + " for " + $scope.array[minIndex]);
			temp = $scope.array[i];
			$scope.array[i] = $scope.array[minIndex];
			$scope.array[minIndex] = temp;
		}
	};
	$scope.reset = function(){
		$scope.array = [17,5,2,9,21,7, -4, 33, 14];
	}
	
}]);
