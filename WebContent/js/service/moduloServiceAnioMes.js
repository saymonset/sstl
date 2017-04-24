'use strict';

app.service('moduloServiceAnioMes',
    function moduloServiceAnioMes($resource) {
        return $resource('services/subGrupoModuloServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbModificar', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nbModificar:'@nbModificar'
        }, {
            query: {
            	url:'services/subGrupoModuloServiceResource/:nbModulo/:nbGrupoModulo',
                method: 'GET',
                isArray: true
            },
            
            create: {
            	url:'services/subGrupoModuloServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo',
                method: 'POST',
                isArray: false
            }
            ,
            queryNoExiste: {
            	url:'services/subGrupoModuloServiceResource/queryNoExiste/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbModificar',
                method: 'GET',
                isArray: false
            },
            deletex: {
            	url:'services/subGrupoModuloServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo',
                method: 'DELETE',
                isArray: false
            } ,
            isDelete: {
            	url:'services/subGrupoModuloServiceResource/isDdelete/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo',
                method: 'get',
                isArray: false
            },
            update: {
            	url:'services/subGrupoModuloServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbModificar',
                method: 'PUT',
                isArray: false
            }
        });
    });
