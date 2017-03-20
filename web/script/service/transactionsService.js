app.service('transactionsService',function($http){
    var transactions = [];
       var transaction = "";
       var $event = "";
 var transaction = function(event,d) {
          $event =  event;
         return transaction = d;
    };  
    
    var transactionsService = {
         getTransactions: function() {
      var promise = $http.get('/FIMWebServices/FIMRest/hello/getTransactionRecords').then(function (response) {
      transactions = response.data;
      });
      return promise;
    },
   
    getList: function() {
       return transactions;
    },
    addToList : function(transaction){
          transactions.unshift(transaction);
    },
    changeTransaction:function($event,d){
           
            changeTransaction($event,d);
    },
      updateTransactionDialog:function($event,d){
          
            changeTransaction($event,d);
    },
      createTransaction: function(transaction) {
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/createTag",
    data: JSON.stringify(transaction)
}).then(function(result){
    
            return result.data;
        });  },
    updateTransaction: function(transaction) {
        //console.log( JSON.stringify(transaction));
 return  $http({
    method: 'POST',
    url: "/FIMWebServices/FIMRest/hello/updateTransaction",
    data: JSON.stringify(transaction)
}).then(function(result){
    
            return result.data;
        });  },
    
    getTransaction: function() {
        var d = transaction;
        transaction = "";
       return d;
    },
    getEvent: function() {
       return $event;
    }
  
  };
      return transactionsService;
    });

