//subidospormiController
  app.controller('documentsBackupController',['$scope','$mdDialog','documentsBackupService','topBannerService',
      function($scope,$mdDialog,documentsBackupService,topBannerService) {
topBannerService.setTitle("Respaldo de documentos en Google Drive");
  
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
          
          $scope.documentBackups = [];

  $scope.getDocumentBackups = function (){
                  documentsBackupService.getDocumentsBackups().then(function(d) {
                   $scope.documentBackups = documentsBackupService.getList();
                //window.console.log($scope.documentBackups);
                });
                 };

$scope.getDocumentBackups();
$scope.trabajando = false;
$scope.request = {};
   $scope.createDocumentsBackupData = function ($event){
       $scope.trabajando = true;
          $mdDialog.show({
        controller: documentBackupRequestDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'documentBackupRequestDialog.tmpl.html',
        parent: angular.element($scope.request.body),
        targetEvent: documentsBackupService.getEvent(),
        clickOutsideToClose:true,
        locals: {
    
        
     
     }
      });
      
              documentsBackupService.createDocumentsBackup().then(function (data) {
                  //window.console.log(data);
                     documentsBackupService.addToList(data);
                     
                   $mdDialog.hide();
                 $scope.trabajando = false;
              });
            
            };

                   function documentBackupRequestDialogController($scope,$http,$timeout, $q,documentsBackupService)
        {
    
   
    
        }
    }]);