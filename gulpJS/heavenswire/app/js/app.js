var app = angular.module("heavensWireApp", ['ngRoute']);

app.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl : 'routes/home.html',
                controller  : 'MainController'
            })

    });

app.run(function($templateCache) {
  $templateCache.removeAll();
});	