app.controller("InsertionSortController", ['$scope', function($scope){
	
	
	$scope.title="Insertion Sort Example";
	$scope.array=[15,3,9,21,7,2,31];
	$scope.newItem="";
	$scope.sort = function(){
		
		insertionSort($scope.array);
		
		
	};
	
	$scope.reset = function(){
		$scope.array=[15,3,9,21,7,2,31];
	};
	
	function insertionSort(arr){
		
		// start at second index and iteratively sort array
		for(var j=1; j < arr.length; j++){
			var current = arr[j];
			var i = j-1;
			
			// insert a[j] into sorted array [0 ... j-1]
			while(i >= 0 && arr[i] > current){
				arr[i+1] = 	arr[i];
				i--;
			}
			arr[i+1] = current;
		}
	}
	
}]);