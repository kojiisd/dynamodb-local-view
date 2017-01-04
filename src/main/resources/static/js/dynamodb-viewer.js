var dynamoDbViewer = angular.module('DynamoDbViewer', []);

dynamoDbViewer.controller("listTables",
    function listTables($scope, $http) {
        $http.get('http://localhost:8080/api/list-tables')
                  .then(function(response) {
                      //First function handles success
                      $scope.tables = response.data;
                  }, function(response) {
                      //Second function handles error
                      alert("Error happens.");
                  });
              });


