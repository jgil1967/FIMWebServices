app.service('documentKeywordRelationshipService',function($http){

       var tag = "";
       var $event = "";
 var changeTag = function(event,d) {
          $event =  event;
         return tag = d;
    };  
    
    var documentKeywordRelationshipService = {
        
      createdocumentKeywordRelationship: function(tag) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/createDocumentKeywordRelationship",
    data: JSON.stringify(tag)
}).then(function(result){
    
            return result.data;
        });  },
      deleteDocumentKeywordRelationshipsByDocument: function(document) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/deleteDocumentKeywordRelationshipsByDocument",
    data: JSON.stringify(document)
}).then(function(result){
    
            return result.data;
        });  },
   
    getEvent: function() {
       return $event;
    }
  
  };
      return documentKeywordRelationshipService;
    });

