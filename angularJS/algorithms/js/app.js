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
            });
    });

app.run(function($templateCache) {
  $templateCache.removeAll();
});	