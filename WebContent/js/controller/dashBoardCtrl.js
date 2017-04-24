'use strict';
app.controller(
		"dashBoardCtrl",
		function($scope, $http, $resource, $location, sharedProperties,
				tipoCtrlService,dashBoardService, $timeout) {

			var tipoCtrl = {
				isCtrl : "dashBoardCtrl"
			};
			tipoCtrlService.setProperty(tipoCtrl);
			$scope.isCtrl = tipoCtrl.isCtrl;
			
	         $scope.userInfo = sharedProperties.getProperty();
	         
			$scope.cedula = $scope.userInfo.cedula;
			$scope.username = $scope.userInfo.username;
			
			$scope.objetos = {};

			$scope.getInclude = function() {
				return "header.html";
			}
			
			
		  	$scope.accesosDirectos = function() {
				var path = $location.path();
				$timeout(function() {
					$location.path('/accesosDirectos');
				}, 50);
			}

			
			/** Traemos la data */
			$timeout(
					function() {
						dashBoardService
								.query(
										{
											 
										},
										function success(data, status) {
											$scope.objetos = data;
										},
										function err(httpResponse) {

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