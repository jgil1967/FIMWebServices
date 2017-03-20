app.controller('usuariosController',['$scope','topBannerService','usuariosService',
     function ($scope,topBannerService,usuariosService)
    { 
        
           setTimeout(function(){ 
               
               $scope.loggedUser = usuariosService.getLoggedUser();
               
               
               if ($scope.loggedUser.root == true ){
                   $scope.getUsuarios($scope.loggedUser); 
               }
               else if ($scope.loggedUser.root == false && $scope.loggedUser.isAdministrator == true){
                  window.console.log("Solo administrador");
                   $scope.getUsuariosForAdministrator ();
               }
              
                }, 500);
                
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
        
        
          $scope.updateUserDirectly = function (usuario){
       usuariosService.updateUser(usuario);
   }; 
        
         $scope.updateUser = function ($event,usuario){
       usuariosService.updateUsuarioDialog($event,usuario);
   }; 
        
        
          topBannerService.setTitle("Usuarios");
            $scope.createUsuario = function ($event){
             $scope.usuario = { id:0, name:'', description:'',contrasena:'',enabled:true,availability:true ,idArea:0,root:false, createdBy: $("#idUsuario").val()};
             
              usuariosService.updateUsuarioDialog($event,$scope.usuario );
              
            };
               $scope.users = [];
              
                 $scope.getUsuarios = function (loggedUser){
                  usuariosService.getUsuarios(loggedUser).then(function(d) {
                   $scope.users = usuariosService.getList();
             
                });
                 };
                 
                   $scope.getUsuariosForAdministrator = function (){
                  usuariosService.getUsuariosForAdministrator().then(function(d) {
                   $scope.users = usuariosService.getList();
             
                });
                 };
                   
         
            
            
            
            
    }

]);