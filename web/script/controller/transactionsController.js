app.controller('transactionsController',['$scope','topBannerService','transactionsService',
     function ($scope,topBannerService,transactionsService)
    { 
        
  $scope.options = {
    rowSelection: false, multiSelect: false, autoSelect: false,decapitate: false, largeEditDialog: false,  boundaryLinks: false,
    limitSelect: true, pageSelect: true};


            $scope.limitOptions = [5, 10, 15, {
    label: 'All',
    value: function () {
      return $scope.documents ? $scope.documents.count : 0;
    }
  }];
          $scope.query = {
    order: 'name',
    limit: 10,
    page: 1
  };
        $scope.transactions = [];
        
        $scope.getTransactions = function(){
             transactionsService.getTransactions().then(function(d) {
                   $scope.transactions = transactionsService.getList();
              
                });
        }
         $scope.getTransactions ();
        
        topBannerService.setTitle("Transacciones");
         
                
    }]);