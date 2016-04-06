
app.controller("QueueController", ['$scope', function($scope){
	$scope.title="Queue Example";
	$scope.queue = [17,2,9,21,7];
	$scope.queueType = "standard";
	$scope.newItem = "";	
	$scope.orderType = "FIFO";
	
	$scope.isStandard = function(){
		return $scope.queueType === "standard";
	};
	
	$scope.enqueue = function(){
		console.log("enqueue");
		var val = $scope.newItem;
		$scope.queue.push(val);
		$scope.newItem = "";
	};
	$scope.dequeue= function(){
		var queueType = $scope.queueType;
		
		if(queueType === 'standard'){
			
			if($scope.orderType === "FIFO")
				$scope.queue.shift();
			else
				$scope.queue.pop();
		}else{
			var lowestVal = 100000;
			var lowestValIndex = 0;
			
			if($scope.queue.length > 0){
				lowestVal = $scope.queue[0];
				
				for(var i=0; i < $scope.queue.length; i++){
					var val = $scope.queue[i];
					if(val < lowestVal){
						lowestVal = val;
						lowestValIndex = i;
					}
				}
				
				if(lowestValIndex == 0){
					$scope.queue.shift();
				}else if( lowestValIndex == $scope.length - 1){
					$scope.queue.pop();
				}else{
					var arr1 = $scope.queue.splice(0,lowestValIndex);
					var arr2 = $scope.queue.splice(lowestValIndex);
					$scope.queue = arr1.concat(arr2);
				}
			}
		}
	};
}]);