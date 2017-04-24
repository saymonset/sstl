'use strict';

app.service('miembroService',
    function miembroService($resource) {
        return $resource('services/miembroServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula/:nombre/:tipoEmp/:reenovable/:profesor/:feRenovacion', {
        	nbModulo: '@nbModulo',nbGrupoModulo: '@nbGrupoModulo',nbSubGrupoModulo: '@nbSubGrupoModulo',nb_actividad: '@nb_actividad',nbHorario: '@nbHorario',cedula:'@cedula',nombre:'@nombre',tipoEmp:'@tipoEmp'
        		,renovable:'@renovable',profesor:'@profesor',feRenovacion:'@feRenovacion'
        }, {
            query: {
            	url:'services/miembroServiceResource/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario',
                method: 'GET',
                isArray: true
            },
            findModuloFechaDesdeHasta: {
            	url:'services/miembroServiceResource/moduloFechaDesdeHasta/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:feDesde/:feHasta',
                method: 'GET',
                isArray: true
            },
            findModuloActividadFechaDesdeHasta: {
            	url:'services/miembroServiceResource/moduloActividadFechaDesdeHasta/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:feDesde/:feHasta',
                method: 'GET',
                isArray: true
            }, 
            findHorarioSinColorByCedula: {
            	url:'services/miembroServiceResource/findHorarioSinColorByCedula/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula',
                method: 'GET',
                isArray: true
            },
            findByCedula: {
             
            	url:'services/miembroServiceResource/findByCedula/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula',
                method: 'GET',
                isArray: false
            } ,
            
            findUserFromPersonalTdoEmpleados: {
            	url:'services/miembroServiceResource/findUserFromPersonalTdoEmpleados/:cedula',
                method: 'GET',
                isArray: false
            } 
            ,
            findHorarioByCedula: {
            	url:'services/miembroServiceResource/findHorarioByCedula/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula',
                method: 'GET',
                isArray: true
            } 
            
            ,
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
            },
            updateMiembro: {
            	url:'services/miembroServiceResource/updateMiembro/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:cedula/:nombre/:tipoEmp/:renovable/:profesor/:feRenovacion',
                method: 'PUT',
                isArray: false
            },
            updateFechaRenovacion: {
            	url:'services/miembroServiceResource/fechaRenovacion/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/',
                method: 'PUT',
                isArray: false
            },
            estadisticasByModulo: {
            	url:'services/miembroServiceResource/estadisticasByModulo/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            }  ,
            estadisticasGetMiembroChart: {
            	url:'services/miembroServiceResource/estadisticasGetMiembroChart/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            } ,
            estadisticasByModuloActividad: {
            	url:'services/miembroServiceResource/estadisticasByModuloActividad/:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            }  ,
            buscarActividadesByModulo: {
            	url:'services/miembroServiceResource/buscarActividadesByModulo/:modulo',
                method: 'GET',
                isArray: true
            } 
            
            
            
        });
    });


//