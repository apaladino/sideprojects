app.controller("MergeSortController", ['$scope', function($scope){
		
	$scope.title="Merge Sort Example";
	$scope.array=[15,3,9,21,7,2,31,1];
	
	$scope.reset = function(){
		$scope.array=[15,3,9,21,7,2,31,1];
	}
	$scope.sort = function(){
		
		var arr = $scope.array;
		
		console.log("arr: " + arr);
		mergeSort(arr, 0, arr.length);
		
		arr.shift();
	}
	
	function mergeSort(a, p, r){
		
		if(p < r){
			var q = Math.floor((p+r)/2);		
			mergeSort(a, p, q); 		// sort left half of a[]
			mergeSort(a, q+1, r); 	// sort right half of a[]
			merge(a, p, q, r);		// merge both halfs together	
		}
	}
	
	
	function merge(a, p, q, r){
		
		var i=p; 					// i is index for left temp array
		var j=q+1;					// j is index for right temp array
		var k=p;					// k is index for temp array
		var b = new Array(r);				// temporary array used for merging
		
		// sort both left and right sides of array 
		while(i <= q && j <= r){
			if(a[i] <= a[j]){
				b[k++] = a[i++];
			}else{
				b[k++] = a[j++];
			}
		}
		
		// It is possible to have a remaining index which hasn't been sorted
		// , so we need to copy the rest into b[]
		while(i <= q){
			b[k] = a[i];
			k++;
			i++;
		}
		
		while(j <= r){
			b[k++] = a[j++];
		}
		// copy sort array b[] back into a[]
		k=p;
		while(k <= r){
			
			a[k] = b[k];
			k++;
		}
	}
	
}]);