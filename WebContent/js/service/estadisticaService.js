'use strict';

app.service('estadisticaService',
    function estadisticaService($resource) {
        return $resource('services/estadisticaCtrl/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula', {
        	mnbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbHorario:'@nbHorario',cedula:'@cedula'		
        }, {
            query: {
                method: 'GET',
                isArray: false
            },
            getByUsuario: {
                method: 'GET',
                isArray: false
            } 
            ,
            estadisticasByModulo: {
            	url:'services/estadisticaCtrl/estadisticasByModulo/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            }  ,
            estadisticasByModuloActividad: {
            	url:'services/estadisticaCtrl/estadisticasByModuloActividad/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            }  ,
            buscarActividadesByModulo: {
            	url:'services/estadisticaCtrl/buscarActividadesByModulo/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
                method: 'GET',
                isArray: true
            } 
        });
    });


 

 