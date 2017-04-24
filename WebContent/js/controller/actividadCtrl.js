app
.controller("actividadCtrl", function($scope, $http, $resource, $location, sharedProperties,
		tipoCtrlService, $timeout, seguridadService,configuracionService) {
	
	
 
	var tipoCtrl = tipoCtrlService.getProperty();
	$scope.isCtrl = tipoCtrl.isCtrl;
	
	$scope.modulo = tipoCtrl.modulo;
	$scope.grupo = tipoCtrl.grupo;
	$scope.subGrupo = tipoCtrl.subGrupo;
	$scope.gruponbModificar = tipoCtrl.gruponbModificar;
	$scope.subGruponbModificar = tipoCtrl.subGruponbModificar;
	$scope.actividad = tipoCtrl.actividad;
	$scope.nbHorario = tipoCtrl.nbHorario;
	$scope.userInfo = sharedProperties.getProperty();
	$scope.cedula = $scope.userInfo.cedula;
	$scope.username = $scope.userInfo.username;
	
	$scope.seguridadDto={};
	
	 $timeout(function() {
		 
		 seguridadService
			.query(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nbActividad:$scope.actividad,
						nbHorario:$scope.nbHorario
					},
					function success(data, status) {
						$scope.seguridadDto=data;
					},
					function err(httpResponse) {
						$scope.data = data
								|| "FALSE";
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado actividadCtrl.js->seguridadService.query';
					});
		}, 50);
//	
//	 /***
//	  * Para cada parametro del modulo y actviidad.., chequeamos que esten por defectos las variabes de los paramtros 
//	  * ******************/
	 $timeout(function() {
		
		 configuracionService
			.query(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nbActividad:$scope.actividad,
						nbHorario:$scope.nbHorario
					},
					function success(data, status) {
						$scope.objetos=data;
					},
					function err(httpResponse) {
						$scope.data = data
								|| "FALSE";
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado actividadCtrl.js->configuracionService.query';
					});
		}, 50);
	

	$scope.getInclude = function() {
		return "header.html";
	}
	
	$scope.salir = function() {
		var path = $location.path();
		$timeout(function() {
			$scope.userInfo={};
			/****En sharedProperties , lleva la info del usuario.. si es admin, la propioedad admin = true***********/
			sharedProperties.setProperty(null);

			$location.path('/');
		}, 50);
	}


	$scope.atras = function(modulo) {
		tipoCtrl = {
			isCtrl : "horarioNombreCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo:$scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar :$scope.subGruponbModificar,
			actividad : $scope.actividad
		};
		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();
		$timeout(function() {
			$location.path('/horarioNombre');
		}, 50);
		var absUrl = $location.absUrl();
		console.log(absUrl);
	}
	
  	$scope.accesosDirectos = function() {
		var path = $location.path();
		$timeout(function() {
			$location.path('/accesosDirectos');
		}, 50);
	}

	
	$scope.loadConfiguracion = function(modulo, actividad) {
		var tipoCtrl = {
			isCtrl : "configuracionCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : actividad ,
			nbHorario :$scope.nbHorario
		};
		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/configuracion');
		}, 50);
	}
	
	

 

	$scope.loadHorario = function(modulo,nbGrupoModulo,nbSubGrupoModulo,gruponbModificar,subGruponbModificar, actividad,nbHorario) {
		var tipoCtrl = {
			isCtrl : "horarioCtrl",
			modulo : modulo,
			grupo : nbGrupoModulo,
			subGrupo : nbSubGrupoModulo,
			gruponbModificar : gruponbModificar,
			subGruponbModificar : subGruponbModificar,
			actividad : actividad ,
			nbHorario :nbHorario
		};
		
		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/horario');
		}, 50);
	}
	
	
	$scope.loadMiembro = function(modulo, actividad) {
		var tipoCtrl = {
			isCtrl : "miembroCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : actividad ,
			nbHorario :$scope.nbHorario
		};

		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/miembro');
		}, 50);
	}
	
	
	
	
	$scope.loadInscripcion = function(modulo, actividad) {
		var tipoCtrl = {
			isCtrl : "inscripcionCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : actividad ,
			nbHorario :$scope.nbHorario
		};

		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/inscripcion');
		}, 50);
	}
	
	
	$scope.atencionByModulo = function(modulo) {
		tipoCtrl = {
			isCtrl : "atencionByModuloCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : $scope.actividad ,
			nbHorario :$scope.nbHorario ,
			htmlGo:'/actividad',
			ctrlGo:'actividadCtrl'
		};
		tipoCtrlService.setProperty(tipoCtrl);

		$timeout(function() {
			$location.path('/atencionByModulo');
		}, 50);
	}
	
	
	
	
	$scope.inscripcionByModulo = function(modulo) {
		tipoCtrl = {
			isCtrl : "inscripcionByModuloCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : $scope.actividad ,
			nbHorario :$scope.nbHorario ,
			htmlGo:'/actividad',
			ctrlGo:'actividadCtrl'
		};
		tipoCtrlService.setProperty(tipoCtrl);

		$timeout(function() {
			$location.path('/inscripcionByModulo');
		}, 50);
	}
	
 
	$scope.asistenciaByModulo = function(modulo) {
		tipoCtrl = {
			isCtrl : "asistenciaByModuloCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : $scope.actividad ,
			nbHorario :$scope.nbHorario,
			htmlGo:'/actividad',
			ctrlGo:'actividadCtrl'
		};
		tipoCtrlService.setProperty(tipoCtrl);

		$timeout(function() {
			$location.path('/asistenciaByModulo');
		}, 50);
	}
	
 
	
	
	

	
	
	
	$scope.loadAtencion= function(modulo, actividad) {
		var tipoCtrl = {
			isCtrl : "atencionCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : actividad ,
			nbHorario :$scope.nbHorario
		};

		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/atencion');
		}, 50);
	}
	
	$scope.loadEstadistica = function(modulo, actividad) {
		var tipoCtrl = {
			isCtrl : "estadisticaCtrl",
			modulo : modulo,
			grupo : $scope.grupo,
			subGrupo : $scope.subGrupo,
			gruponbModificar : $scope.gruponbModificar,
			subGruponbModificar : $scope.subGruponbModificar,
			actividad : actividad ,
			nbHorario :$scope.nbHorario
		};

		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/estadistica');
		}, 50);
	}
	
	 
	
	
});