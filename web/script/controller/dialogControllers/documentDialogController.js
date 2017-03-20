app.controller('documentDialogController', ['$scope', '$http', '$filter', '$timeout', '$q', 'documentosService', '$mdDialog', 'fileUpload', 'tagsService', 'documentKeywordRelationshipService', 'areasService', 'usuariosService','documentsBackupService',
    function ($scope, $http, $filter, $timeout, $q, documentosService, $mdDialog, fileUpload, tagsService, documentKeywordRelationshipService, areasService, usuariosService,documentsBackupService) {

        $scope.$watch(function () {
            return documentosService.getDocument();
        }, function (document) {

            if (usuariosService.getLoggedUser() != null) {
                
                $scope.loggedUser = usuariosService.getLoggedUser();
                    $scope.areasFiltradas= [];
                var area = {id: $scope.loggedUser.idArea, name: $scope.loggedUser.area.name};
                
                    $scope.areas = areasService.getList();
                     //window.console.log($scope.loggedUser);
                  //  window.console.log($scope.areas);
                    if (!$scope.loggedUser.area.superuser){
                         angular.forEach($scope.areas, function (area, key) {
                        
                      if (area.uploadAndEdit == true){
                            $scope.areasFiltradas.push(area);
                      }
                   });
                    }
                    else{
                        $scope.areasFiltradas = $scope.areas;
                    }
              
                   
                    
                    
                    
                    if (document != "") {
                        //CREATE
                        if (document.id == 0) {
                            if (document.isFolder == true){
                                 $mdDialog.show({
                                controller: documentDialogController,
                                controllerAs: 'ctrl',
                                templateUrl: 'documentDialogFolder.tmpl.html',
                                parent: angular.element(document.body),
                                targetEvent: documentosService.getEvent(),
                                clickOutsideToClose: true,
                                locals: {
                                    document: document,
                                    update: false,
                                    areas: $scope.areasFiltradas,
                                    fileUpload: fileUpload,
                                    documentsBackupService:documentsBackupService
                                }
                            });   
                            }
                            //UPDATE
                            else{
                                
                                   $mdDialog.show({
                                controller: documentDialogController,
                                controllerAs: 'ctrl',
                                templateUrl: 'documentDialog.tmpl.html',
                                parent: angular.element(document.body),
                                targetEvent: documentosService.getEvent(),
                                clickOutsideToClose: true,
                                locals: {
                                    document: document,
                                    update: false,
                                    areas: $scope.areasFiltradas,
                                    fileUpload: fileUpload,
                                    documentsBackupService:documentsBackupService
                                }
                            });
                            }
                         
                            
                            
                        } 
                        
                //UPDATE
                else {

                            document.fileDate = new Date(document.fileDate);
                            
                            if (document.isFolder == true){
                                 $mdDialog.show({
                                controller: documentDialogController,
                                controllerAs: 'ctrl',
                                templateUrl: 'updateDocumentDialogFolder.tmpl.html',
                                parent: angular.element(document.body),
                                targetEvent: documentosService.getEvent(),
                                clickOutsideToClose: true,
                                locals: {
                                    document: document,
                                    update: true,
                                    areas: $scope.areasFiltradas,
                                    fileUpload: fileUpload,
                                    documentsBackupService:documentsBackupService
                                }
                            });
                            }
                            else{
                               $mdDialog.show({
                                controller: documentDialogController,
                                controllerAs: 'ctrl',
                                templateUrl: 'documentDialog.tmpl.html',
                                parent: angular.element(document.body),
                                targetEvent: documentosService.getEvent(),
                                clickOutsideToClose: true,
                                locals: {
                                    document: document,
                                    update: true,
                                    areas: $scope.areasFiltradas,
                                    fileUpload: fileUpload,
                                    documentsBackupService:documentsBackupService
                                }
                            });  
                            }
                            
                           
                        }

                    }
                
            }

        }, true);

        function documentDialogController($scope, $http, $timeout, $q, documentosService, document, update, areas, fileUpload, tagsService, documentKeywordRelationshipService,documentsBackupService)
        {
             $scope.loggedUser = usuariosService.getLoggedUser();
              $scope.carpeta = document;
          $scope.documentosDeCarpeta = [];
          $scope.getDocumentsByFolder  = function(){
              documentosService.getDocumentsByFolder($scope.carpeta).then(function(d) {
                     
                        $scope.documentosDeCarpeta = documentosService.getList();
                        window.console.log($scope.documentosDeCarpeta);
                        for (var i=0 ; i<$scope.documentosDeCarpeta.length; i++){
                            
                           
                            
                           $scope.documentosDeCarpeta[i].isInsideFolder = true; 
                           $scope.documentosDeCarpeta[i].folder = $scope.carpeta;
                        }
                    });
          }
       //     window.console.log(JSON.stringify($scope.carpeta));
            if ($scope.carpeta.isFolder == true && $scope.carpeta.id != 0){
                $scope.getDocumentsByFolder ();
            }
          
          $scope.borrarORestaurar = function (document){
       document.idUsuario = $("#idUsuario").val();
       document.isInsideFolder = true;
             document.folder=$scope.carpeta;
            if (document.deleted == false){
                          if (confirm('¿Esta seguro de que desea restaurar este documento?')) {
                   document.deleted = false;
                    documentosService.restoreDocument(document).then(function (data) {
                           $scope.getDocumentsByFolder ();
                   

                });
} else {
   $scope.init();
}
    
            }
            else{
                    if (confirm('¿Esta seguro de que desea eliminar este documento?')) {
                   document.deleted = true;
                    documentosService.deleteDocument(document).then(function (data) {
                           $scope.getDocumentsByFolder ();
                  

                });
} else {
   $scope.init();
}
    
            }
  }
          
              $scope.updateDocument = function ($event,document){
             document.vengoDeRootYPuedoCambiarDeArea = false;
       documentosService.updateDocumentDialog($event,document);
       
   }; 
   
   
 $scope.moverDocumento = function (event,document){
            documentosService.updateDocumentMoveDialog(event,document);
 }
 
   
            $scope.createDocument = function ($event){
                  
              $scope.document = { id:0, isFolder:false, isInsideFolder:true, folder:$scope.carpeta, fileDate:new Date(), name:'', description:'',idArea: document.idArea ,createdBy: $("#idUsuario").val(), color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
                
            };
            
            $scope.createDocumentFolder = function ($event){
                 $scope.document = { id:0, isFolder:true, isInsideFolder:true, folder:$scope.carpeta, fileDate:new Date(), name:'', description:'',idArea:document.idArea ,createdBy: $("#idUsuario").val(), color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
                  
            };
            
            //############
            $scope.trabajando = false;
            
            $scope.shouldShow = function (area) {
  // put your authorization logic here
  return area.uploadAndEdit == true ;
}

            setTimeout(function () {

                $('.datepicker').pickadate({
                    selectMonths: true, // Creates a dropdown to control month
                    selectYears: 15 // Creates a dropdown of 15 years to control year
                });


            }, 700);

            $scope.areas = areas;
            $scope.document = document;
            
            
          //  window.console.log(update);
             //   window.console.log($scope.document);
                
//              if ($scope.document.isInsideFolder == true){
//                window.console.log("$scope.document.isInsideFolder = true");   
//            }
            
            $scope.update = update;
            $scope.tags = [];
            $scope.tagsOriginales = [];
            if ($scope.update == true) {
                $scope.tags = document.keywords;
                $scope.tagsOriginales = $scope.tags;

            }


            var countries = [];

            $scope.getTags = function () {
                tagsService.getTags().then(function (d) {
                    countries = tagsService.getList();
                });
            };

            $scope.getTags();


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
            $scope.loadCountries = function ($query) {
                return $http.get('/FIMWebServices/FIMRest/hello/getKeywords', {cache: false}).then(function (response) {
                    countries = response.data;
                    return countries.filter(function (country) {
                        return country.name.toLowerCase().indexOf($query.toLowerCase()) != -1;
                    });
                });
            };

            $scope.viewTags = function () {
                

            }


            $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
            $scope.tagsStuff = function () {
                angular.forEach($scope.tags, function (tag, key) {
                    if ("id" in tag) {
                        $scope.dDto = {
                            idKeyword: tag.id,
                            idDocument: $scope.document.id
                        };
                        documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                            // console.log(JSON.stringify(data));

                        });

                    } else {
                        tag.createdBy = $("#idUsuario").val();
                        // window.console.log("Tag a crear : " + JSON.stringify(tag));
                        tagsService.createTag(tag).then(function (data) {
                            $scope.dDto = {
                                idKeyword: data.id,
                                idDocument: $scope.document.id
                            };

                            documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                // console.log(JSON.stringify(data));

                            });
                            countries.push($scope.dDto);
                        });

                    }
                });
            }

 $scope.uploading = false;
  
  
       $scope.subirDocumento = function (document){
          
           $scope.uploading = true;
          //  window.console.log(document);
              documentsBackupService.backupDocument(document).then(function (data) {
                    $scope.getDocumentsByFolder ();                   
                   $scope.uploading = false;

                }); 
           
            
       }

            $scope.editDocument = function () {

$scope.trabajando = true;
                documentKeywordRelationshipService.deleteDocumentKeywordRelationshipsByDocument($scope.document).then(function (data) {
                    // window.console.log("Se han borrado las relaciones de este documento");
                });
                $scope.document.keywords = $scope.tags;
                $scope.tagsStuff();
                $scope.document.fileDate = new Date($("#fileDate").val());
                //window.console.log($scope.document);
                $scope.document.filename = $scope.document.name;
                documentosService.updateDocument($scope.document).then(function (data) {
                    $mdDialog.hide();
                    location.reload();
$scope.trabajando = false;
                });



            }


$scope.nuevoDocumentFolder = function () {
                window.console.log($scope.document);
                
     $scope.trabajando = true;
      $scope.document.fileDate = new Date($("#fileDate").val());
          $scope.document.filename = $scope.document.name; 
      
       documentosService.createDocument($scope.document).then(function (data) {
                                $mdDialog.hide();
                                $scope.document = data;
                                angular.forEach($scope.tags, function (tag, key) {
                                    if ("id" in tag) {
                                        $scope.dDto = {
                                            idKeyword: tag.id,
                                            idDocument: $scope.document.id
                                        };
                                        documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                        });

                                    } else {
                                        tag.createdBy = $("#idUsuario").val();
                                        
                                        tagsService.createTag(tag).then(function (data) {
                                            $scope.dDto = {
                                                idKeyword: data.id,
                                                idDocument: $scope.document.id
                                            };

                                            documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                                

                                            });
                                            countries.push($scope.dDto);
                                        });

                                    }
                                });
                                $scope.document.keywords = $scope.tags;
                               // location.reload();
                                documentosService.addToList($scope.document);
                            });
                                
                                $scope.trabajando = false;
                                    
      
}

$scope.editDocumentFolder = function () {
   
$scope.trabajando = true;
                documentKeywordRelationshipService.deleteDocumentKeywordRelationshipsByDocument($scope.document).then(function (data) {
                    // window.console.log("Se han borrado las relaciones de este documento");
                });
                $scope.document.keywords = $scope.tags;
                $scope.tagsStuff();
                $scope.document.fileDate = new Date($("#fileDate").val());
               // window.console.log($scope.document);
                documentosService.updateDocument($scope.document).then(function (data) {
                    $mdDialog.hide();
                   
$scope.trabajando = false;
                });

     
}
//pensar alternativa para el botón.
            $scope.nuevoDocument = function () {
                  $mdDialog.hide();
                if (!$scope.trabajando){
                   $scope.trabajando = true;
                    var file = $scope.myFile;
                $scope.document.filename = $scope.myFile.name;
                $scope.document.fileDate = new Date($("#fileDate").val());
                var uploadUrl = "/FIMWebServices/FIMRest/hello/upload";
                var fields = [{"name": "name", "data": document.name}, {"name": "description", "data": document.description}];
                var fd = new FormData();
                fd.append('file', file);
                for (var i = 0; i < fields.length; i++) {
                    fd.append(fields[i].name, fields[i].data)
                }
                $http.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                        .success(function (response) {
                           $scope.trabajando= true;
                            $scope.document.filename = response;
                            documentosService.createDocument($scope.document).then(function (data) {
                                $mdDialog.hide();
                                $scope.document = data;
                                angular.forEach($scope.tags, function (tag, key) {
                                    if ("id" in tag) {
                                        $scope.dDto = {
                                            idKeyword: tag.id,
                                            idDocument: $scope.document.id
                                        };
                                        documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                        });

                                    } else {
                                        tag.createdBy = $("#idUsuario").val();
                                        
                                        tagsService.createTag(tag).then(function (data) {
                                            $scope.dDto = {
                                                idKeyword: data.id,
                                                idDocument: $scope.document.id
                                            };

                                            documentKeywordRelationshipService.createdocumentKeywordRelationship($scope.dDto).then(function (data) {
                                                

                                            });
                                            countries.push($scope.dDto);
                                        });

                                    }
                                });
                                $scope.document.keywords = $scope.tags;
                                
                                documentosService.addToList($scope.document);
                                location.reload();
                            });

                                $scope.trabajando = false;
                                
                                    
 $scope.trabajando = false;

                        })
                        .error(function () {
                             $scope.trabajando = false;
                           // window.console.log("false");
                        });

                }
                

            };

        }
    }]);




