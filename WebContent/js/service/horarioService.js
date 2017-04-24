'use strict';

app.service('horarioService',
    function horarioService($resource) {
        return $resource('services/horarioServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:text/:id/:start/:end', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbHorario:'@nbHorario',text:'@text',id:'@id',start:'@start',end:'@end'
        }, {
            query: {
            	url:'services/horarioServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
                method: 'GET',
                isArray: true
            },
            findByModuloActividadMoveDisabled: {
      
            	url:'services/horarioServiceResource/findByModuloActividadMoveDisabled/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
                method: 'GET',
                isArray: true
            },
            create: {
                method: 'POST',
                isArray: false
            }
            ,
            deletex: {
            	url:'services/horarioServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:text/:id',
                method: 'DELETE',
                isArray: false
            } ,
            update: {
            	url:'services/horarioServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:text/:id/:start/:end',
                method: 'PUT',
                isArray: false
            }
        });
    });


 