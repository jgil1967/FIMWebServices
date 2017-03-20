app.controller('documentosController',['$http','$scope','topBannerService','documentosService','areasService','usuariosService','documentsBackupService',
     function ($http,$scope,topBannerService,documentosService,areasService,usuariosService,documentsBackupService)
    { 
 setTimeout(function(){ $('.collapsible').collapsible(); 
 
   $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year
  });
  
    
 }, 700);
 
 
 
 $scope.moverDocumento = function (event,document){
            documentosService.updateDocumentMoveDialog(event,document);
 }
 
 
      $scope.areas= [];
        $scope.areasSeleccionadas= [];
       $scope.selectedAreas= [];
              $scope.usuarios= [];
       $scope.selectedUsuarios= []; 
       
        var selectedValues = [];   
       $scope.changed = function (){
                selectedValues = [];   
    $("#areasSeleccionadas option").each(function(){
                   // window.console.log($(this));
                    if ($(this).prop("selected") == true) {
           selectedValues.push(parseInt($(this).attr('id')));
    } else {
                  //  window.console.log($(this).prop("selected") );
    }
    
    });
    //console.log("ONLY SELECTED: " + selectedValues);
     $scope.filteredDocuments();
$scope.safeApply ();
       }
       
         
                $scope.filteredDocuments = function () {
    return $scope.documents.filter(function (document) {
       

        return selectedValues.indexOf(document.idArea) !== -1 ;
    });
  };
              //window.console.log("ENTRANDO A CONTROLADOR");
                $scope.obteniendo = false; 
              $scope.emptySelectedAreas = function (){
                  $scope.selectedAreas=[];
              }
              $scope.setAllSelectedAreas  = function(){
                $scope.selectedAreas=$scope.backup;
              };
              $scope.backup  = [];
              
              
              $( document ).ready(function() {
    $('select').material_select();
     $scope.init();
$scope.llenarTodos ();
});


$scope.safeApply = function(fn) {
  var phase = this.$root.$$phase;
  if(phase == '$apply' || phase == '$digest') {
    if(fn && (typeof(fn) === 'function')) {
      fn();
    }
  } else {
    this.$apply(fn);
  }
};


$scope.quitarTodo = function (){
      setTimeout(function(){ 
        var el = document.getElementById("areasSeleccionadas");
                   
                    for (var i = 0; i < el.length; i++) {
                   el[i].setAttribute('selected',false);
    }
// window.console.log(el);
 $scope.changed ();
}, 500);
}




$scope.llenarTodos = function (){
           // window.console.log("Vamos a llenar todas las seleccionadas");
    setTimeout(function(){ 
        var el = document.getElementById("areasSeleccionadas");
                   
                    for (var i = 0; i < el.length; i++) {
                        
                   el[i].setAttribute('selected',true);
    }
 //window.console.log(el);
 $scope.changed ();
}, 500);
}

              $scope.init = function (){
                     if ($scope.obteniendo == false){
                 $scope.obteniendo = true;
                   $scope.loggedUser = usuariosService.getLoggedUser(); 
                   //window.console.log( $scope.loggedUser );
                   if ($scope.loggedUser.area.superuser == false){
                     var area = {id:$scope.loggedUser.idArea,name:$scope.loggedUser.area.name};
                     areasService.getAreasByArea2(area).then(function(d) {
                   $scope.areas= areasService.getList();
                        //window.console.log($scope.areas);
                        
                 
                   $scope.getDocumentsOnlyEnabled($scope.areas);  
               });    
                   }
                   else{
                         $scope.getDocuments(); 
                           areasService.getAreas().then(function(d) {
                   $scope.areas= areasService.getList();
                        
                     });   
                   }
                }
              }
             
            
              //por esto entra dos veces wey
                $scope.getDocumentsOnlyEnabled = function (areas){
                   // window.console.log(areas);
            
                 $scope.obteniendo = true;
                 documentosService.getDocumentsOnlyEnabled(areas).then(function(d) {
                        $scope.documents = documentosService.getList();
                        $scope.obteniendo = false;
                      //  window.console.log($scope.documents );
                    });
                  
                 }; 
         $scope.getDocuments = function (){
           
                 documentosService.getDocuments().then(function(d) {
                     //window.console.log(   $scope.documents);
                        $scope.documents = documentosService.getList();
                        $scope.obteniendo = false;
                    });
             
                  
                 };  
  $scope.uploading = false;
  
  
       $scope.subirDocumento = function (document){
          
          document.user.id =  $("#idUsuario").val();
           $scope.uploading = true;
            window.console.log(document);
              documentsBackupService.backupDocument(document).then(function (data) {
                   $scope.getDocuments();
                   $scope.uploading = false;

                }); 
           
            
       }
       
       
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
         $scope.clear = function(){
    $scope.myModel = undefined;
  };
  
  $scope.borrarORestaurar = function (document){
       document.idUsuario = $("#idUsuario").val();
            //window.console.log(document);   
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
   $scope.init();
}
    
            }
  }
        
         $scope.downloadDocument = function (document){
              $scope.document = document;
             documentosService.downloadDocument($scope.document).then(function (data) {
                          
                        });
            
                
         };
        
         $scope.updateDocument = function ($event,document){
             document.vengoDeRootYPuedoCambiarDeArea = true;
       documentosService.updateDocumentDialog($event,document);
       
   }; 
        
         
          topBannerService.setTitle("Documentos");
            $scope.createDocument = function ($event){
             $scope.document = { id:0, isFolder:false,isInsideFolder:false,vengoDeRootYPuedoCambiarDeArea:true, fileDate:new Date(), name:'', description:'',idArea:0,createdBy: $("#idUsuario").val(), color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
            };
            
              $scope.createDocumentFolder = function ($event){
             $scope.document = { id:0, isFolder:true,isInsideFolder:false,fileDate:new Date(), name:'',vengoDeRootYPuedoCambiarDeArea:true, description:'',idArea:0,createdBy: $("#idUsuario").val(), color:"#01579b",kind:"document" };
              documentosService.updateDocumentDialog($event,$scope.document );
            };
            
               $scope.documents = [];
              
              $scope.dateRangeFilter = function (property, startDate, endDate) {
                  //change  start date to 00:00:00
    return function (item) {
//                window.console.log(item);
//                window.console.log(startDate);
//                window.console.log(endDate);
        if (item[property] === null) return false;
 
        var itemDate = moment(item[property]);
        var s = moment(startDate, "MM-DD-YYYY");
        var e = moment(endDate, "MM-DD-YYYY");
 
        if (itemDate >= s && itemDate <= e) return true;
        return false;
    }
    
}
     $scope.fileDateRangeFilter = function (property, startDate, endDate) {
                  //change  start date to 00:00:00
    return function (item) {
//                window.console.log(item);
//                window.console.log(startDate);
//                window.console.log(endDate);
        if (item[property] === null) return false;
 
        var itemDate = moment(item[property]);
        var s = moment(startDate, "MM-DD-YYYY");
        var e = moment(endDate, "MM-DD-YYYY");
 
        if (itemDate >= s && itemDate <= e) return true;
        return false;
    }
   
}

 $scope.tags = [];
    var countries = [];
    
  $scope.loadCountries = function($query) {
    return $http.get('/FIMWebServices/FIMRest/hello/getKeywords', { cache: false}).then(function(response) {
                
        countries = response.data;
                //window.console.log(countries);
      return countries.filter(function(country) {
        return country.name.toLowerCase().indexOf($query.toLowerCase()) != -1;
      });
    });
  };

$scope.dates = {
    startDate:'',
    endDate:'',
    startFileDate:'',
    endFileDate:''
} 

//         $scope.$watch("dates", function(newValues, oldValues, scope) {
//             window.console.log(newValues);
//            window.console.log(JSON.stringify($scope.dates ));
//});

 $scope.startDate;
 $scope.endDate;
 $scope.startFileDate;
 $scope.endFileDate;
$scope.search = function ($event){
         //   window.console.log("Search");
                if ($scope.searchDocumentos !="" || new Date($scope.startDate) != "Invalid Date" || new Date($scope.endDate) != "Invalid Date" ||new Date( $scope.startFileDate) != "Invalid Date" ||new Date($scope.endFileDate) != "Invalid Date" )
            {
                
                
                  var dates2 = {
                oldestCreatedOn: new Date($("#startDate").val()),
                newestCreatedOn: new Date($("#endDate").val()),
                oldestFileDate: new Date($("#startFileDate").val()),
                newestFileDate: new Date($("#endFileDate").val())
                    } ;
                 
                    var filters = {
                        dates:dates2,
                        keywords:$scope.tags,
                        filterQuery: $scope.searchDocumentos
                    }
                documentosService.getDocumentsFilters(filters).then(function() {
                  //  window.console.log("documentosService.getList() : " + documentosService.getList());
                $scope.documents = documentosService.getList();
                
                //Ya se por que es dude
            });
            }
            
}
 $scope.$watchGroup(['startDate', 'endDate', 'startFileDate', 'endFileDate'], function(newValues, oldValues, scope) {
           
  
},true);




                
                   
           
            
            
            
            
    }

]);