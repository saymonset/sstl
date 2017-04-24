'use strict';
app.controller("accesosDirectosCtrl", function($scope, $http, $resource,
		$location, sharedProperties, tipoCtrlService, accesosDirectoService,miembroService,
		$timeout,Idle, Keepalive, $uibModal ) {

	var tipoCtrl = {
		isCtrl : "accesosDirectosCtrl"
	};
	
	
	tipoCtrlService.setProperty(tipoCtrl);
	$scope.isCtrl = tipoCtrl.isCtrl;

	$scope.userInfo = sharedProperties.getProperty();

	$scope.cedula = $scope.userInfo.cedula;
	$scope.username = $scope.userInfo.username;
	
	$scope.admin = $scope.userInfo.admin;
	
	$scope.loading = false;

	$scope.objetos = {};

	$scope.getInclude = function() {
		return "header.html";
	}
	
	 

	
	 
	/** Traemos la data */
	$timeout(function() {
		$scope.loading = true;
		accesosDirectoService.query({
			cedula:$scope.cedula
		}, function success(data, status) {
			$scope.objetos = data;
			$scope.loading = false;
		}, function err(httpResponse) {
			$scope.loading = false;
			$scope.status = status;
			$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js';
		});

	}, 50);

	$scope.salir = function() {
		var path = $location.path();
		$timeout(function() {
			$scope.userInfo={};
			/****En sharedProperties , lleva la info del usuario.. si es admin, la propioedad admin = true***********/
			sharedProperties.setProperty(null);

			$location.path('/');
		}, 50);
	}

	 
	
	
	//"loadInscripcion(item.nbModulo,item.nbGrupo,item.nbSubGrupo,item.nbHorario, item.nbActividad);"
	$scope.loadInscripcion = function(nbModulo, nbGrupo, nbSubGrupo,nbActividad, nbHorario,personalizarHorario
			) {
		
		var tipoCtrl = {
				isCtrl : "inscripcionCtrl",
				modulo : nbModulo,
				grupo : nbGrupo,
				subGrupo : nbSubGrupo,
				gruponbModificar : nbGrupo,
				subGruponbModificar : nbSubGrupo,
				actividad : nbActividad,
				nbHorario : nbHorario,
				personalizarHorario : personalizarHorario ,
				accesoDirecto:'true'
			};

		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();

		$timeout(function() {

			$location.path('/inscripcion');
		}, 50);
	}
	
	
	

	
	
	
	
	
	$scope.panel = function() {
		var path = $location.path();
		$timeout(function() {
			$location.path('/dashBoard');
		}, 50);
	}

	$scope.loadData = function(modulo, username, cedula) {
		tipoCtrl = {
			isCtrl : "moduloAnioCtrl",
			modulo : modulo
		};
		tipoCtrlService.setProperty(tipoCtrl);
		var path = $location.path();
		$timeout(function() {
			$location.path('/moduloAnio');
		}, 50);
		var absUrl = $location.absUrl();
		console.log(absUrl);
	}

	
	
	
	
});