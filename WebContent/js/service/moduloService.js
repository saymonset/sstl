'use strict';

app.service('moduloService',
    function moduloService($resource) {
        return $resource('services/actividadCtrl/:nbModulo/:nb_actividad/:nbActividadModif', {
        	nbModulo: '@nbModulo',nb_actividad: '@nb_actividad',nbActividadModif:'@nbActividadModif'
        }, {
            query: {
                method: 'GET',
                isArray: true
            },
            create: {
                method: 'POST',
                isArray: false
            }
            ,
            deletex: {
                method: 'DELETE',
                isArray: false
            } ,
            update: {
                method: 'PUT',
                isArray: false
            }
        });
    });


 