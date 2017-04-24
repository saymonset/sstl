'use strict';

app.service('asistenciaService',
    function asistenciaService($resource) {
        return $resource('services/asistenciaCtrl/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula/:nombre/:tipoEmp/:reenovable/:profesor/:feRenovacion', {
        	mnbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad:'@nb_actividad',nbHorario:'@nbHorario',cedula:'@cedula',nombre:'@nombre',tipoEmp:'@tipoEmp'
        		,renovable:'@renovable',profesor:'@profesor',feRenovacion:'@feRenovacion'
        }, {
             
            buscarByModuloCedula: {
            	url:'services/asistenciaCtrl/buscarByModuloCedula/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            }  ,
            buscarMiembrosByModulo: {
            	url:'services/asistenciaCtrl/buscarMiembrosByModulo/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad',
                method: 'GET',
                isArray: true
            } 
            
            
            
        });
    });


//