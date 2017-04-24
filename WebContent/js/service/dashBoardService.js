'use strict';

app.service('dashBoardService',
    function dashBoardService($resource) {
        return $resource('services/moduloServiceResource', {
        	
        }, {
            query: {
            	url:'services/moduloServiceResource',
                method: 'GET',
                isArray: true
            } 
        });
    });


//