  app.controller('documentsBackupDialogController',['$scope','$http','$timeout', '$q','documentsBackupService','$mdDialog', 
      function($scope,$http,$timeout, $q,documentsBackupService,$mdDialog) {
         
          $scope.$watch(function(){return documentsBackupService.getDocumentBackupRequest();}, function (documentBackupRequest) {
                 
        $mdDialog.show({
        controller: documentBackupRequestDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'documentBackupRequestDialog.tmpl.html',
        parent: angular.element(documentBackupRequest.body),
        targetEvent: documentsBackupService.getEvent(),
        clickOutsideToClose:true,
        locals: {
        documentBackupRequest:  documentBackupRequest ,
        
     
     }
      });
                
                  
          
              
          },true);
          
          function documentBackupRequestDialogController($scope,$http,$timeout, $q,documentsBackupService,documentBackupRequest)
        {
    
   
    
        }
              }]);
          
          
  

