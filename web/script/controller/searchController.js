app.controller('searchController', ['$scope','$routeParams','topBannerService','objectsService','$location','$timeout',
    function ($scope, $routeParams,topBannerService,objectsService,$location,$timeout)
    {
        topBannerService.setTitle("Búsqueda");
         $scope.options = {
    rowSelection: false,
    multiSelect: false,
    autoSelect: false,
    decapitate: false,
    largeEditDialog: false,
    boundaryLinks: false,
    limitSelect: true,
    pageSelect: true
  };
   $scope.selected = [];
  $scope.limitOptions = [5, 10, 15, {
    label: 'All',
    value: function () {
      return $scope.results ? $scope.results.count : 0;
    }
  }];

  $scope.query = {
    order: 'name',
    limit: 10,
    page: 1
  };

  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };


  $scope.onPaginate = function(page, limit) {
    console.log('Scope Page: ' + $scope.query.page + ' Scope Limit: ' + $scope.query.limit);
    console.log('Page: ' + page + ' Limit: ' + limit);
    $scope.promise = $timeout(function () {
    }, 1000);
  };



  $scope.onReorder = function(order) {
    console.log('Scope Order: ' + $scope.query.order);
    console.log('Order: ' + order);
    $scope.promise = $timeout(function () {
    }, 1000);
  };
        
        $scope.results = [];
      
        if ($routeParams.searchTerm != ""){
            $scope.object = {
            query : $routeParams.searchTerm
        };
           // window.console.log("Entrando desde searchController a searchObjects...");
           objectsService.searchObjects($scope.object).then(function(searchResults) {
               $scope.results = objectsService.getSearchResults();
           }); 
        }
        
        $scope.touched=false;
         $scope.$watch(function(){return objectsService.getSearchTerm();}, function (search) 
       { 
           if (search  == ""  ) {  
                  $location.path('/');
           }
           else if (search  !=  "" && search  != undefined){
                   $scope.touched=true;
                  $location.path('/searchResults/' + search);
                 topBannerService.setTitle("Búsqueda");
                 $scope.results = objectsService.getSearchResults();
                 window.console.log("Tamaño resultados :" + $scope.results.length);
           }
           
           
       }, true);
        
         
    }]);