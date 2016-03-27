app.controller("MaxSubArrayController", ['$scope', function($scope){

	// class holding sum info for a given sub array
	var SubArrayInfo = function(maxLeft, maxRight, sum){
		this.maxLeft = maxLeft;
		this.maxRight = maxRight;
		this.sum = sum;
		this.toString = function(){
			return "MaxLeft: " + this.maxLeft + "\tMaxRight: " + this.maxRight + "\tSum: " + this.sum;
		}
	};
	
	// find max sum for sub array encompassing both left and right branches
	function findMaxCrossingSubArray(a, low, mid, high){
		
		var leftSum = -1000000;
		var maxLeft = 0;
		
		// find max for left half of array
		for(var i = mid; i > low; i--){
			if(a[i] > leftSum){
				leftSum = a[i];
				maxLeft = i;
			}
		}
		
		// find max for right half of array
		var rightSum = -1000000;
		var maxRight = 0; 
		
		for(var i= mid+1; i < high; i++){
			if(a[i] > rightSum){
				rightSum = a[i];
				maxRight = i;
			}
		}
		
		// consolidate into a new SubArrayInfo object and return
		return new SubArrayInfo(maxLeft, maxRight, (leftSum + rightSum));
	};
	
	function findMaxSubArray(a, low, high){
		
		if(low == high){
			return new SubArrayInfo(a, low, a[low]);
		}else{
			var mid = Math.floor((low + high)/2);
			var sumLeftBranch = findMaxSubArray(a, low, mid);
			var sumRightBranch = findMaxSubArray(a, mid+1, high);
			var sumCrossBranches = findMaxCrossingSubArray(a, low, mid, high);
			
			// check which branch has greatest sum
			if(sumLeftBranch.sum > sumRightBranch.sum && sumLeftBranch.sum > sumCrossBranches.sum){
				return sumLeftBranch;
			}else if( sumRightBranch.sum > sumLeftBranch.sum && sumRightBranch.sum > sumCrossBranches.sum){
				return sumRightBranch;
			}else{
				return sumCrossBranches;
			}
		}
	};
	
	$scope.title='Max Sub Array Problem Example';
	$scope.array = [13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7];
	$scope.results = "";
	$scope.find = function(){
		var a = $scope.array;
		var maxSub = findMaxSubArray(a, 0, a.length - 1);
		$scope.results = maxSub.toString();
	}
		
}]);