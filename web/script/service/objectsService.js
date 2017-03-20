app.service('objectsService', function($http){
  var searchResults = [];
  var searchTerm = "";
  
  
  
    var objectsService = {
   searchObjects: function(dDto) {
           // window.console.log("Query : " + dDto.query);
           searchTerm = dDto.query ;
   return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/searchDocuments",
    data: JSON.stringify(dDto)
        }).then(function(result)
        {
             // window.console.log("Resultados: " + JSON.stringify(result.data));
            //    window.console.log("Tamaño desde searchObjects: " + result.data.length);
            searchResults = result.data;
        });
        
    }, getSearchResults: function() {
       return searchResults;
    }
    ,
    
    getSearchTerm: function() {
       return searchTerm;
    }
    }; 
    
    return objectsService;
});


