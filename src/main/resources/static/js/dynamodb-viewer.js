var dynamoDbViewer = angular.module('DynamoDbViewer', ['ngTable']);

dynamoDbViewer.controller("listTables",
    function listTables($scope, $http, ngTableParams) {
        $scope.tableParams = new ngTableParams(
            { page: 1,
              count: 10 },
            { counts: [],
              total: 1,
              dataset: $scope.tables });

        $http.get('http://localhost:8080/api/list-tables')
            .then(function(response) {
                //First function handles success
                $scope.tables = response.data;
            }, function(response) {
                //Second function handles error
                alert("Can not connect to DynamoDB Local. Please check connection and try again.");
            });
        });


