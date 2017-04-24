'use strict';

app.service('parametroService',
    function parametroService($resource) {
        return $resource('services/parametroServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:txNombreParametro', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbHorario:'@nbHorario',txNombreParametro:'@txNombreParametro'
        }, {
            query: {
            	url:'services/parametroServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:txNombreParametro',
                method: 'GET',
                isArray: false
            }  
        });
    });
