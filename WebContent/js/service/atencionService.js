'use strict';

app.service('atencionService',
    function atencionService($resource) {
        return $resource('services/atencionCtrl/:text/:id/:start/:end/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula/:atencionRealizada', {
        	text: '@text',id: '@id',start:'@start',end:'@end',mnbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbHorario:'@nbHorario'
        		,cedula:'@cedula',atencionRealizada:'@atencionRealizada'
        }, {
            create: {
                method: 'POST',
                isArray: false
            }
        });
    });

