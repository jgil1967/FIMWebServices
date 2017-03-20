app.controller('appController',['$scope', '$timeout',
     function ($scope,$timeout)
    {    $scope.clock = Date.now(); // initialise the time variable
    $scope.tickInterval = 1000 //ms

    var tick = function() {
        $scope.clock = Date.now() // get the current time
        $timeout(tick, $scope.tickInterval); // reset the timer
    }

    // Start the timer
    $timeout(tick, $scope.tickInterval);
    
    //setInterval(function(){ location.reload(); }, 30000);
   
        
    }

]);