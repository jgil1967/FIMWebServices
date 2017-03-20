//subidospormiController
  app.controller('subidospormiController',['$scope','topBannerService','documentosService',
      function($scope,topBannerService,documentosService) {

         topBannerService.setTitle("Mis documentos");
                      $scope.options = {
    rowSelection: false, multiSelect: false, autoSelect: false,decapitate: false, largeEditDialog: false,  boundaryLinks: false,
    limitSelect: true, pageSelect: true};

 $scope.createDocument = function ($event){
             $scope.document = { id:0, fileDate:new Date(), name:'', description:'',idArea:0,createdBy: $("#idUsuario").val(), color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
            };


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
        $scope.user = { id:$("#idUsuario").val() };
                $scope.documents =[];
                
            $scope.borrarORestaurar = function (document){
                document.idUsuario = $("#idUsuario").val();
            window.console.log(document);   
            if (document.deleted == false){
                          if (confirm('¿Esta seguro de que desea restaurar este documento?')) {
                   document.deleted = false;
                    documentosService.restoreDocument(document).then(function (data) {
                   $scope.getDocuments();

                });
} else {
   $scope.init();
}
    
            }
            else{
                    if (confirm('¿Esta seguro de que desea eliminar este documento?')) {
                   document.deleted = true;
                    documentosService.deleteDocument(document).then(function (data) {
                   $scope.getDocuments();

                });
} else {
   $scope.getDocuments();
}
    
            }
  }
                $scope.updateDocument = function ($event,document){
       documentosService.updateDocumentDialog($event,document);
   }; 
             $scope.getDocuments = function (){
                    documentosService.getDocumentsByUser(   $scope.user).then(function(d) {
                     
                        $scope.documents = documentosService.getList();
                        window.console.log($scope.documents);
                        
                    });
             };
                $scope.getDocuments();
      
              
                  
               
    }]);