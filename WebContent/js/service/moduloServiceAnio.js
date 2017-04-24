'use strict';

app.service('moduloServiceAnio',
    function moduloServiceAnio($resource) {
        return $resource('services/grupoModuloServiceResource/:nbModulo/:nbGrupoModulo/:nbModificar', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbModificar:'@nbModificar'
        }, {
            query: {
                method: 'GET',
                isArray: true
            },
            queryNoExiste: {
            	url:'services/grupoModuloServiceResource/queryNoExiste/:nbModulo/:nbGrupoModulo/:nbModificar',
                method: 'GET',
                isArray: false
            },
            create: {
                method: 'POST',
                isArray: false
            }
            ,
            deletex: {
                method: 'DELETE',
                isArray: false
            },
            isDelete: {
            	url:'services/grupoModuloServiceResource/:nbModulo/:nbGrupoModulo',
                method: 'get',
                isArray: false
            } ,
            update: {
                method: 'PUT',
                isArray: false
            }
        });
    });


