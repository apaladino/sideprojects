var app = angular.module("algorithmsApp", ['ngRoute']);

app.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl : 'routes/home.html',
                controller  : 'MainController'
            })

            // route for the arrays page
            .when('/arrays', {
                templateUrl : 'routes/array.html',
                controller  : 'ArrayController'
            })

            // route for the queue page
            .when('/queues', {
                templateUrl : 'routes/queue.html',
                controller  : 'QueueController'
            })
			
            // route for the stack page
            .when('/stacks', {
                templateUrl : 'routes/stack.html',
                controller  : 'StackController'
            })
			
            // route for the Insertion Sort page
            .when('/insertionSort', {
                templateUrl : 'routes/insertionSort.html',
                controller  : 'InsertionSortController'
            })
			
			// route for the Merge Sort Page
			.when('/mergeSort', {
				templateUrl: 'routes/mergeSort.html',
				controller: 'MergeSortController'
			})
			
			// route for Binary Search Page
			.when('/binarySearch', {
				templateUrl: 'routes/binarySearch.html',
				controller: 'BinarySearchController'
			})
			
			// routes for selection sort
			.when('/selectionSort', {
				templateUrl: 'routes/selectionSort.html',
				controller: 'SelectionSortController'
			})
			
			// route for max subarray problem
			.when('/maxSubArray', {
				templateUrl: 'routes/maxSubArray.html',
				controller: 'MaxSubArrayController'
			})
			
			// route for heap sort
			.when('/heapSort', {
				templateUrl: 'routes/heapSort.html',
				controller: 'HeapSortController'
			});
    });

app.run(function($templateCache) {
  $templateCache.removeAll();
});	