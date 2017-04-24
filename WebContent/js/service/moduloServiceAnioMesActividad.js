'use strict';

app.service('moduloServiceAnioMesActividad',
    function moduloServiceAnioMesActividad($resource) {
        return $resource('services/actividadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbActividadModif', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbActividadModif:'@nbActividadModif'
        }, {
            query: {
            	url:'services/actividadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo',
                method: 'GET',
                isArray: true
            },
            
            create: {
            	url:'services/actividadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
                method: 'POST',
                isArray: false
            }
            ,
            queryNoExiste: {
            	url:'services/actividadServiceResource/queryNoExiste/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
                method: 'GET',
                isArray: false
            },
            deletex: {
            	url:'services/actividadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
                method: 'DELETE',
                isArray: false
            }  ,
            isDelete: {
            	url:'services/actividadServiceResource/isDdelete/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
                method: 'get',
                isArray: false
            },
            update: {
            	url:'services/actividadServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbActividadModif',
                method: 'PUT',
                isArray: false
            }
        });
    });
