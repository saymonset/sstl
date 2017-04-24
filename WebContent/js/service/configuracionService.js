'use strict';

app.service('configuracionService',
    function configuracionService($resource) {
        return $resource('services/configuracionServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbActividad/:nbHorario/:txNombreParametro/:txValorParametro/:txObservaciones/:tipoParametro/:personalizar_horario', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nbActividad:'@nbActividad',nbHorario:'@nbHorario',txNombreParametro:'@txNombreParametro'
        		,txValorParametro:'@txValorParametro',txObservaciones:'@txObservaciones',tipoParametro:'@tipoParametro',personalizar_horario:'@personalizar_horario'
        }, {
            query: {
            	url:'services/configuracionServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbActividad/:nbHorario',
                method: 'GET',
                isArray: true
            } ,findParametro: {
            	url:'services/configuracionServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nbActividad/:nbHorario/:txNombreParametro',
                method: 'GET',
                isArray: true
            } ,
            update: {
                method: 'PUT',
                isArray: false
            }
        });
    });

 //