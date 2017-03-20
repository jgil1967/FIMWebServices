  app.controller('keywordDialogController',['$scope','$http','$timeout', '$q','keywordsService','$mdDialog', 
      function($scope,$http,$timeout, $q,keywordsService,$mdDialog) {
         
          $scope.$watch(function(){return keywordsService.getKeyword();}, function (keyword) {
                 
                 
              if (keyword != ""){
                  
                    
                   
                     if (keyword.id == 0){
       
        $mdDialog.show({
        controller: keywordDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'keywordDialog.tmpl.html',
        parent: angular.element(keyword.body),
        targetEvent: keywordsService.getEvent(),
        clickOutsideToClose:true,
        locals: {
        keyword:  keyword ,
        
        update: false
     }
      });
                      } else {
                   // window.console.log("Es update");
                    $mdDialog.show({
                        controller: keywordDialogController,
                        controllerAs: 'ctrl',
                        templateUrl: 'keywordDialog.tmpl.html',
                        parent: angular.element(keyword.body),
                        targetEvent: keywordsService.getEvent(),
                        clickOutsideToClose: true,
                        locals: {
                            keyword: keyword,
                            
                            update: true
                        }
                    });
                }
                  
                   
                
                  
          
              }
          },true);
          
          function keywordDialogController($scope,$http,$timeout, $q,keywordsService,keyword,update)
        {
    
   
    $scope.keyword  = keyword;
   
    
    $scope.editKeyword = function (){
          $scope.keyword.createdBy =  $("#idUsuario").val();
        keywordsService.updateKeyword($scope.keyword ).then(function(d) {
            $mdDialog.hide();
        });
    }
    
   
    
              $scope.update = update;
              
          
              
            if ($scope.update == true) {
               
            }
            
            
            
             $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
  
              $scope.nuevoKeyword = function(){
              keywordsService.createKeyword($scope.keyword).then(function (data) {
                  keywordsService.addToList(data);
                            $mdDialog.hide();
                        });
        
              };
    
        }
              }]);
          
          
  

