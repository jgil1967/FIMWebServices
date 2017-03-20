  app.controller('areaDialogController',['$scope','$http','$timeout', '$q','areasService','$mdDialog', 
      function($scope,$http,$timeout, $q,areasService,$mdDialog) {
         
          $scope.$watch(function(){return areasService.getArea();}, function (area) {
                 
                 
              if (area != ""){
                  
                    
                   
                     if (area.id == 0){
       
        $mdDialog.show({
        controller: areaDialogController,
        controllerAs: 'ctrl',
        templateUrl: 'areaDialog.tmpl.html',
        parent: angular.element(area.body),
        targetEvent: areasService.getEvent(),
        clickOutsideToClose:true,
        locals: {
        area:  area ,
        
        update: false
     }
      });
                      } else {
                   // window.console.log("Es update");
                    $mdDialog.show({
                        controller: areaDialogController,
                        controllerAs: 'ctrl',
                        templateUrl: 'areaDialog.tmpl.html',
                        parent: angular.element(area.body),
                        targetEvent: areasService.getEvent(),
                        clickOutsideToClose: true,
                        locals: {
                            area: area,
                            
                            update: true
                        }
                    });
                }
                  
                   
                
                  
          
              }
          },true);
          
          function areaDialogController($scope,$http,$timeout, $q,areasService,area,update)
        {
    
    $scope.areasByArea = [];
    $scope.possibleAreasByArea = [];
    $scope.area  = area;
            window.console.log($scope.area  );
    $scope.createAreaRelationship = function (a){
        var ar = {idArea1:$scope.area.id,idArea2: a.id }
        areasService.createAreaRelationship(ar).then(function(d) {
             $scope.getAreasAndStuff ();
        });
    };
    
    $scope.editArea = function (){
        areasService.updateArea($scope.area ).then(function(d) {
            $mdDialog.hide();
        });
    }
    
    
    //
     $scope.uploadAndEdit = function (a){
           var ar = {idArea1:$scope.area.id,idArea2: a.id,uploadAndEdit:a.uploadAndEdit };
                areasService.uploadAndEdit(ar).then(function(d) {
           //$scope.getAreasAndStuff ();
        });
    
     }
    $scope.deleteAreaRelationship = function (a){
        var ar = {idArea1:$scope.area.id,idArea2: a.id };
        
        areasService.deleteAreaRelationship(ar).then(function(d) {
           $scope.getAreasAndStuff ();
        });
    
    };
    
              $scope.update = update;
              
              $scope.getAreasAndStuff = function (){
                   areasService.getAreasByArea($scope.area  ).then(function(d) {
              $scope.areasByArea = d;
                    window.console.log(d);
        
    });
    areasService.getPossibleAreasByArea($scope.area  ).then(function(d) {
       $scope.possibleAreasByArea = d;
        window.console.log(d);
        
    });
    
    
              }
              
              
            if ($scope.update == true) {
               $scope.getAreasAndStuff();
            }
            
            
            
             $scope.cancel = function ($event) {
                $mdDialog.cancel();
            };
  
              $scope.nuevoArea = function(){
              areasService.createArea($scope.area).then(function (data) {
                  areasService.addToList(data);
                            $mdDialog.hide();
                        });
        
              };
    
        }
              }]);
          
          
  

