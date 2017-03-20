app.service('documentsBackupService',function($http){
  var documentsBackups = [];
         var documentBackupRequest = "";
       var $event = "";
 var changeDocumentBackupRequest = function(event,d) {
          $event =  event;
         return documentBackupRequest = d;
    };  
    
    var documentsBackupService = {
          getDocumentsBackups: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getDocumentBackups').then(function (response) {
      documentsBackups = response.data;
      });
      return promise;
    },
     getList: function() {
       return documentsBackups;
    },
     addToList : function(object){
          documentsBackups.unshift(object);
    },
      createDocumentsBackup: function() {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/createDocumentsBackup",
 
}).then(function(result){
    
            return result.data;
        });  },
    changeDocumentBackupRequest:function($event,d){
           
            changeDocumentBackupRequest($event,d);
    },
     getEvent: function() {
       return $event;
    },
     backupDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/backupDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
    getDocumentBackupRequest: function() {
        var d = documentBackupRequest;
        documentBackupRequest = "";
       return d;
    },
  };
      return documentsBackupService;
    });

