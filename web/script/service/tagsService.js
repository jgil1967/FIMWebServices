app.service('tagsService',function($http){
    var tags = [];
       var tag = "";
       var $event = "";
 var changeTag = function(event,d) {
          $event =  event;
         return tag = d;
    };  
    
    var tagsService = {
         getTags: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getKeywords').then(function (response) {
      tags = response.data;
      });
      return promise;
    },
    getList: function() {
       return tags;
    },
    addToList : function(tag){
          tag.unshift(tag);
    },
      createTag: function(tag) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/createTag",
    data: JSON.stringify(tag)
}).then(function(result){
    
            return result.data;
        });  },
    
    getTag: function() {
        var d = tag;
        tag = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return tagsService;
    });

