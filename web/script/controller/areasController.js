app.controller('areasController',['$http','$scope','topBannerService','areasService','usuariosService',
     function ($http,$scope,topBannerService,areasService,usuariosService)
    { 
         setTimeout(function(){ $scope.loggedUser = usuariosService.getLoggedUser(); 
              window.console.log($scope.loggedUser);
                }, 700);
        
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
        
        
topBannerService.setTitle("Areas");

$scope.updateAreaDirectly = function(a){
    areasService.updateArea(a);
}
$scope.updateArea = function ($event,area){
            
            areasService.updateAreaDialog($event,area);
}

$scope.areas = []
  $scope.getAreas = function (){
                  areasService.getAreas().then(function(d) {
                   $scope.areas = areasService.getList();
              
                });
                 };
                   
        $scope.getAreas();  
  $scope.createArea = function ($event){
             $scope.area = { id:0, name:'',superuser:false,enabled:true , createdBy: $("#idUsuario").val()};
             
              areasService.updateAreaDialog($event,$scope.area );
              
            };
            
   }

]);