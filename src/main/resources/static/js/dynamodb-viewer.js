var dynamoDbViewer = angular.module('DynamoDbViewer', ['ngTable', 'angular-loading-bar']);

dynamoDbViewer.controller("listTables",
    function listTables($scope, $http, ngTableParams, cfpLoadingBar) {
    $scope.init = function(host, port) {
        $scope.tableParams = new ngTableParams(
            { page: 1,
              count: 10 },
            { counts: [],
              total: 1,
              dataset: $scope.tables });

        var tmpHost = (host == "" || host == null) ? "localhost" : host;
        var tmpPort = (port == "" || port == null) ? "8080" : port;

        var url = "http://" + tmpHost + ":" + tmpPort + "/api/list-tables"
        cfpLoadingBar.start();
        $http.get(url)
            .then(function(response) {
                //First function handles success
                $scope.tables = response.data;
                cfpLoadingBar.complete();
            }, function(response) {
                cfpLoadingBar.complete();
                //Second function handles error
                alert("Can not connect to DynamoDB Local. Please check connection and try again.");
            });
        };
    });


dynamoDbViewer.controller("inquiryTable",
    function inquiryTable($scope, $http, ngTableParams, cfpLoadingBar){
    $scope.init = function(tableName, host, port) {
        $scope.tableName = tableName;
        $scope.tableParams = new ngTableParams(
            { page: 1,
              count: 10 },
            { counts: [],
              total: 1,
              dataset: $scope.tables });

        var tmpHost = (host == "" || host == null) ? "localhost" : host;
        var tmpPort = (port == "" || port == null) ? "8080" : port;

        var url = "http://" + tmpHost + ":" + tmpPort + "/api/inquiry-table/"
        cfpLoadingBar.start();
        $http.get(url + $scope.tableName)
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


