//subidospormiController
  app.controller('databaseBackupController',['$scope','databaseBackupService','topBannerService',
      function($scope,databaseBackupService,topBannerService) {
topBannerService.setTitle("Respaldo de base de datos");
            $scope.databaseBackupData = {databaseName:"FIMWebServices",user:"postgres",password:"postgresql1",postgresqlPath:"/Library/PostgreSQL/9.6/bin/",email:"jgil1967@hotmail.com",nombreBackup:"FIMWebServices"};  
            $scope.listoParaDescargar = false;
            $scope.createDatabaseBackupData = function (){
               databaseBackupService.createDatabaseBackup($scope.databaseBackupData).then(function (data) {
                 $scope.listoParaDescargar = true; 
               });
            
            }
               
    }]);