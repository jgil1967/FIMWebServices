app.controller('keywordsController',['$http','$scope','topBannerService','keywordsService','usuariosService',
     function ($http,$scope,topBannerService,keywordsService,usuariosService)
    { 
        
         setTimeout(function(){ $scope.loggedUser = usuariosService.getLoggedUser(); 
            //  window.console.log($scope.loggedUser);
              $scope.getKeywords();  
                }, 400);
        
topBannerService.setTitle("Palabras clave");
        $scope.options = {
    rowSelection: false, multiSelect: false, autoSelect: false,decapitate: false, largeEditDialog: false,  boundaryLinks: false,
    limitSelect: true, pageSelect: true};


            $scope.limitOptions = [5, 10, 15, {
    label: 'All',
    value: function () {
      return $scope.usuarios ? $scope.usuarios.count : 0;
    }
  }];
          $scope.query = {
    order: 'name',
    limit: 50,
    page: 1
  };
      
$scope.updateKeyword = function ($event,keyword){
            keyword.createdBy =  $("#idUsuario").val();
            window.console.log(keyword);
            window.console.log( $("#idUsuario").val());
            keywordsService.updateKeywordDialog($event,keyword);
}

$scope.keywords = [];
  $scope.getKeywords = function (){
                  keywordsService.getKeywords().then(function(d) {
                   $scope.keywords = keywordsService.getList();
              
                });
                 };
                   
        
  $scope.createKeyword = function ($event){
             $scope.keyword = { id:0, name:'' , createdBy: $("#idUsuario").val()};
             
              keywordsService.updateKeywordDialog($event,$scope.keyword );
              
            };
            
   }

]);