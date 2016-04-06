app.controller("HeapSortController", ['$scope', function($scope){
	
	$scope.title="Heap Sort";
	$scope.array =  [1, 4, 10, 8, 7, 9, 3, 2, 44, 16];
	$scope.sort = function(){
		heapSort($scope.array);
	};
	$scope.reset = function(){
		$scope.array =  [1, 4, 10, 8, 7, 9, 3, 2, 44, 16];
	}
	
	function heapSort(a){
		buildHeap(a);
		
		// loop through heap and swap root with leaf nodes
		for(var i=(a.length-1); i > 2; i--){
			var tmp = a[1];
			a[1] = a[i];
			a[i] = tmp;
			a.heapSize--;
			maxHeapify(a,1);
		}
	}
		
	function buildHeap(a){
		
		a.heapSize = a.length-1;
		var mid = Math.floor((a.heapSize)/2);
		
		// start at the middle of the array and 
		// build maxHeap at each level
		for(var i=mid; i > 0; i--){
			maxHeapify(a, i);
		}
	}
		
	function maxHeapify(a, i){
		
		var l = left(i);
		var r = right(i);
		var largest = 0;
		
		// check to see if left child has larger value
		if(l < a.heapSize && a[l] > a[i])
			largest = l;
		else
			largest = i;
		
		// check to see if right child has larger value
		if(r < a.heapSize && a[r] > a[largest])
			largest = r;
		
		if(largest != i){
			// swap child value with parent value
			var tmp = a[i];
			a[i] = a[largest];
			a[largest] = tmp;
			
			// rebuild max heap
			maxHeapify(a, largest);
		}
	}
	
	function left(i){
		return i<<1;
	}
	
	function right(i){
		return (i<<1) + 1;
	}
	
	function parent(i){
		return Math.floor(i/2);
	}
	
	
}]);
