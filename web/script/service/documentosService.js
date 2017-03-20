app.service('documentosService',function($http){
    var documents = [];
    var dates = {};
       var document = "";
       var documentGovernment = "";
       var documentMove = "";
       
       
       var $event = "";
 var changeDocument = function(event,d) {
          $event =  event;
         return document = d;
    };  
     var changeDocumentMove = function(event,d) {
          $event =  event;
         return documentMove = d;
    };  
    
    var documentosService = {
         getDocuments: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getDocuments').then(function (response) {
      documents = response.data;
      });
      return promise;
    },
      getDocumentGovernment: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getDocumentGovernment').then(function (response) {
      documentGovernment = response.data;
      });
      return promise;
    },
     getDocumentsOnlyEnabled: function(areas) {
   
       return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/getDocumentsOnlyEnabled",
    data: JSON.stringify(areas)
}).then(function(result){
    
          documents = result.data;
            
        });  
        
    },
    getDocumentsByFolder: function(folder) {
   
       return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/getDocumentsByFolder",
    data: JSON.stringify(folder)
}).then(function(result){
    
          documents = result.data;
            
        });  
        
    },
      getDocumentsByUser: function(user) {
   
       return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/getDocumentsByUser",
    data: JSON.stringify(user)
}).then(function(result){
    
          documents = result.data;
            
        });  
        
    },
      getDocumentsFilters: function(filters) {
            window.console.log("Filters: " + JSON.stringify(filters));
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/getDocumentsFilters",
    data: JSON.stringify(filters)
}).then(function(result){
    
          documents = result.data;
            
        });  },
     getDatesDTO: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getDatesDTO').then(function (response) {
      dates = response.data;
      });
      return promise;
    },
    getList: function() {
       return documents;
    },
     getDocumentGovernmentObject: function() {
       return documentGovernment;
    },
    getDates: function() {
       return dates;
    },
    addToList : function(document){
          documents.unshift(document);
    },
    updateDocumentMoveDialog:function($event,d){
           
            changeDocumentMove($event,d);
    },
    updateDocumentDialog:function($event,d){
           
            changeDocument($event,d);
    },
    downloadDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/downloadDocument",
    data: JSON.stringify(document)
}).then(function(result){
    window.console.dir(result);
                window.console.log(result);
          // return result.data;
        });  },
    
    moveDocuments: function(documents) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/moveDocuments",
    data: JSON.stringify(documents)
}).then(function(result){
    
            return result.data;
        });  },
    
     updateDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/updateDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
    
    deleteDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/deleteDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
      restoreDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/restoreDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
      createDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/createDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
    
    getDocument: function() {
        var d = document;
        document = "";
       return d;
    },
     getDocumentMove: function() {
        var d = documentMove;
        documentMove = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return documentosService;
    });

