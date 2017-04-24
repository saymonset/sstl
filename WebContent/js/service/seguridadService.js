'use strict';

app.service('seguridadService',
    function seguridadService($resource) {
        return $resource('services/seguridadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbActividad/:nbHorario', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nbActividad:'@nbActividad',nbHorario:'@nbHorario'		
        }, {
            query: {
                method: 'GET',
                isArray: false
            } 
        });
    });


 

 