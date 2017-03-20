app.service('topBannerService', function($http){
      
       var title = "";
   var changeTitle = function(t) {
               
         return title = t;
    };
var topBannerService = {
    
    getTitle: function() {
       return title;
    },
    setTitle: function(t) {
           // window.console.log(t);
        changeTitle(t);
    }
  };
  return topBannerService;
   
   
});
