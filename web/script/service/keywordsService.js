app.service('keywordsService',function($http){
    var keywords = [];
       var keyword = "";
       var $event = "";
 var changeKeyword = function(event,d) {
          $event =  event;
         return keyword = d;
    };  
    
    var keywordsService = {
         getKeywords: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getKeywords').then(function (response) {
      keywords = response.data;
      });
      return promise;
    },
   
    getList: function() {
       return keywords;
    },
    addToList : function(keyword){
          keywords.unshift(keyword);
    },
    changeKeyword:function($event,d){
           
            changeKeyword($event,d);
    },
      updateKeywordDialog:function($event,d){
          
            changeKeyword($event,d);
    },
      createKeyword: function(keyword) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/createTag",
    data: JSON.stringify(keyword)
}).then(function(result){
    
            return result.data;
        });  },
    updateKeyword: function(keyword) {
        //console.log( JSON.stringify(keyword));
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/updateKeyword",
    data: JSON.stringify(keyword)
}).then(function(result){
    
            return result.data;
        });  },
    
    getKeyword: function() {
        var d = keyword;
        keyword = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return keywordsService;
    });

