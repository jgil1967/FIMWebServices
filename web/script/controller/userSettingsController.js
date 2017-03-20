app.controller('userSettingsController',['$scope','topBannerService','usuariosService',
     function ($scope,topBannerService,usuariosService)
    { 
        
        $scope.loggedUser = {};
        topBannerService.setTitle("Cambiar mi contraseña");
         setTimeout(function(){ 
             $scope.loggedUser = usuariosService.getLoggedUser(); 
              $scope.$apply();
                }, 700);
                
                $scope.updatePassword = function (){
                      usuariosService.updateUser($scope.loggedUser).then(function (data) {
                                alert("Su contraseña se ha editado correctamente");
                 
                            });
                }
                
    }]);