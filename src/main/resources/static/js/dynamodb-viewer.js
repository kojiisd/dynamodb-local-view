var dynamoDbViewer = angular.module('DynamoDbViewer', ['ngTable', 'angular-loading-bar']);

dynamoDbViewer.controller("listTables",
    function listTables($scope, $http, ngTableParams, cfpLoadingBar) {
    $scope.tableParams = new ngTableParams(
        { page: 1,
          count: 10 },
        { counts: [],
          total: 1,
          dataset: $scope.tables });

    cfpLoadingBar.start();
    $http.get('http://localhost:8080/api/list-tables')
        .then(function(response) {
            //First function handles success
            $scope.tables = response.data;
            cfpLoadingBar.complete();
        }, function(response) {
            cfpLoadingBar.complete();
            //Second function handles error
            alert("Can not connect to DynamoDB Local. Please check connection and try again.");
        });
    });

dynamoDbViewer.controller("inquiryTable",
    function inquiryTable($scope, $http, ngTableParams, cfpLoadingBar){
    $scope.init = function(tableName) {
        $scope.tableName = tableName;
        $scope.tableParams = new ngTableParams(
            { page: 1,
              count: 10 },
            { counts: [],
              total: 1,
              dataset: $scope.tables });

        cfpLoadingBar.start();
        $http.get('http://localhost:8080/api/inquiry-table/' + $scope.tableName)
            .then(function(response) {
                //First function handles success
                $scope.datas = response.data;
                cfpLoadingBar.complete();
            }, function(response) {
                //Second function handles error
                alert("Can not connect to DynamoDB Local. Please check connection and try again.");
                cfpLoadingBar.complete();
            });
        };
    });


