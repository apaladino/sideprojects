app.controller("ArrayController", ['$scope', function($scope){
	
	$scope.title="Arrays Example";
	$scope.array=[15,3,9];
	$scope.newItem="";
	$scope.selectedIndex="0";
	$scope.add = function(){
		var index = parseInt($scope.selectedIndex);
		var a = $scope.array;
		console.log("a=" + a);
		
		if(index > $scope.array.length){
			var b = [index];
		
			// resize array
			for(var i=0; i < a.length; i++){
				b[i] = a[i];
			}
			b[index] = $scope.newItem;
			$scope.array=b;
		}else{
			var max = a.length +1;
			var b = new Array(max);
			
			for(var i=0; i < index; i++){
				
				b[i] = $scope.array[i];
			}
			
			b[index] = $scope.newItem;
			for(var j= index + 1; j < max; j++){
				var aIndex = j-1;
				var bIndex = j;
				var val = a[aIndex];
				b[bIndex] = val;
			}
			$scope.array = new Array(b.length);
			$scope.array=b.slice(0);
		}
	}
	$scope.getIndexes = function(){
		var a = [];
		for(var i=0; i < $scope.array.length + 1; i++){
			a.push(i);
		}
		return a;
	}
}]);