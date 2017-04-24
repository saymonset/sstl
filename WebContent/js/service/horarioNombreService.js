'use strict';
app.service('horarioNombreService',
	    function horarioNombreService($resource) {
	        return $resource('services/horarioNombreServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:nbHorarioModif', {
	        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbHorario:'@nbHorario',nbHorarioModif:'@nbHorarioModif'
	        }, {
	            query: {
	            	url:'services/horarioNombreServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
	                method: 'GET',
	                isArray: true
	            },
	            
	            create: {
	            	url:'services/horarioNombreServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
	                method: 'POST',
	                isArray: false
	            }
	            ,
	            queryNoExiste: {
	            	url:'services/horarioNombreServiceResource/queryNoExiste/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
	                method: 'GET',
	                isArray: false
	            }
	            ,
	            isDelete: {
	            	url:'services/horarioNombreServiceResource/isDdelete/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
	                method: 'get',
	                isArray: false
	            },
	            deletex: {
	            	url:'services/horarioNombreServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
	                method: 'DELETE',
	                isArray: false
	            } ,
	            update: {
	            	url:'services/horarioNombreServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:nbHorarioModif',
	                method: 'PUT',
	                isArray: false
	            }
	        });
	    });

 