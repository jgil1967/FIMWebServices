app.controller('controllerSideNavBar', ['$scope','contentsService','$timeout', '$q', '$log','objectsService','$location','usuariosService',
    function ($scope,contentsService,$timeout, $q, $log,objectsService,$location,usuariosService)
    
    {        $scope.dDto = {
                    query : ""
                };
                $scope.opciones = [];
                setTimeout(function(){ $scope.loggedUser = usuariosService.getLoggedUser(); 
                    $scope.$apply();
                 if ($scope.loggedUser.root==true){
                       $scope.opciones = contentsService.getContentsRoot();$scope.$apply();
                 }
                    if ($scope.loggedUser.isAdministrator==true && $scope.loggedUser.root==false){
                     
                     $scope.opciones = contentsService.getContentsAdministrator();$scope.$apply();
                 }
                 if ($scope.loggedUser.isAdministrator==false && $scope.loggedUser.root==false){
                     
                     $scope.opciones = contentsService.getContentsNormal();$scope.$apply();
                 }
                 
                }, 1000);
                
            $scope.$watch('dDto.query', function() {
                if (  $scope.dDto.query != ""){
                    objectsService.searchObjects($scope.dDto).then(function(searchResults) {
                  
                        
                    });
                }
                else{
                       $location.path('/');
                }
                  
            
            });
       /////////////////////////////////////////////////////////////////////////
        
         
    }]);