  app.controller('usuarioDialogController',['$scope','$http','$timeout', '$q','usuariosService','$mdDialog','areasService', 
      function($scope,$http,$timeout, $q,usuariosService,$mdDialog,areasService) {
         
          $scope.$watch(function(){return usuariosService.getUsuario();}, function (usuario) {
                 
                  areasService.getAreas().then(function(d) {
                      $scope.areas = areasService.getList();
                      if (usuario != ""){
                     if (usuario.id == 0){
       
        $mdDialog.show({
        controller: usuarioDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'usuarioDialog.tmpl.html',
        parent: angular.element(usuario.body),
        targetEvent: usuariosService.getEvent(),
        clickOutsideToClose:true,
        locals: {
        usuario:  usuario ,
        carreras: $scope.carreras,
        update: false,
        areas: $scope.areas 
     }
      });
                      } else {
                   // window.console.log("Es update");
                    $mdDialog.show({
                        controller: usuarioDialogController,
                        controllerAs: 'ctrl',
                        templateUrl: 'usuarioDialog.tmpl.html',
                        parent: angular.element(usuario.body),
                        targetEvent: usuariosService.getEvent(),
                        clickOutsideToClose: true,
                        locals: {
                            usuario: usuario,
                            carreras: $scope.carreras,
                            update: true,
                            areas: $scope.areas 
                        }
                    });
                }
                  
                   
                
                  
          
              }
                  });
              
          },true);
          
          function usuarioDialogController($scope,$http,$timeout, $q,usuariosService,usuario,update,areas)
        {
    $scope.areas = areas;
            
    $scope.usuario  = usuario;
    $scope.loggedUser = usuariosService.getLoggedUser();
            window.console.log($scope.loggedUser);
       $scope.update = update;
            if ($scope.update == true) {
             $scope.usarioNameOriginalAlEditar = $scope.usuario.name;
            }
     $scope.$watch('usuario.name', function() {
                if (  $scope.usuario.name != ""){
                     
                     if(update && $scope.usarioNameOriginalAlEditar == $scope.usuario.name){
                        return; 
                     }
                   
                    usuariosService.verificaDisponibilidadUsuario($scope.usuario).then(function (data) {
                        $scope.usuario =data;
                        
                    });
                    
                }
                else{
                     //  window.console.log("vacio");
                }
            });
    
    
     $scope.editUsuario = function(){
              usuariosService.updateUser($scope.usuario).then(function (data) {
                                $mdDialog.hide();
                 
                            });
     }
           


  
            
             $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
  
              $scope.nuevoUsuario = function(){
              usuariosService.createUsuario($scope.usuario).then(function (data) {
                  usuariosService.addToList(data);
                            $mdDialog.hide();
                        });
        
              };
    
        }
              }]);
          
          
  

