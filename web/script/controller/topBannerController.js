  app.controller('topBannerController',['$scope','topBannerService','usuariosService',
      function($scope,topBannerService,usuariosService) {

        //window.console.log("idUsuario : " + $("#idUsuario").val());
        $scope.mensaje = "";
        var user = {id: $("#idUsuario").val()};
         usuariosService.getUsuarioByID(user).then(function(d){
           $scope.loggedUser = usuariosService.getLoggedUser();
           if ( $scope.loggedUser.root == true){
               $scope.mensaje = "No olvide realizar respaldos periódicos de sus archivos y la base de datos";
           }
           
        });
        $scope.loggedUser = {};
        $scope.title = "";
        $scope.$watch(function(){return topBannerService.getTitle();}, function (t) {
            if (t!=""){
                 $scope.title = t;
                 
            }
            }, true);
    }]);