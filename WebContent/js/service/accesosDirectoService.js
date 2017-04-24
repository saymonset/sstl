'use strict';

app.service('accesosDirectoService',
    function accesosDirectosService($resource) {
        return $resource('services/accesoDirectoService', {
        	
        }, {
            query: {
            	url:'services/accesoDirectoService/:cedula',
                method: 'GET',
                isArray: true
            } 
        });
    });


//