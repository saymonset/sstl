app
		.controller(
				"moduloCtrl",
				function($scope, $http, $resource, $location, sharedProperties,
						tipoCtrlService, $timeout, $uibModal, $log,
						moduloService) {

					var tipoCtrl = tipoCtrlService.getProperty();
					$scope.isCtrl = tipoCtrl.isCtrl;
					$scope.modulo = tipoCtrl.modulo;

					$scope.userInfo = sharedProperties.getProperty();
					$scope.cedula = $scope.userInfo.cedula;
					$scope.username = $scope.userInfo.username;
					$scope.isCreateActividad = false;
					$scope.isVerFormActualizarActividad = false;
					$scope.isActualizarActividad = false;
					$scope.actividades = [];
					$scope.matchPattern = new RegExp("^[A-Za-z]");
					$scope.numMatchPattern = new RegExp("^[0-9]");
					$scope.nb_actividad = "";
					$scope.aviso = "";
					
					

					$timeout(
							function() {
								moduloService
										.query(
												{
													nbModulo : $scope.modulo
												},
												function success(data, status) {
													$scope.actividades = data;
												},
												function err(httpResponse) {
													$scope.data = data
															|| "FALSE";
													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado moduloCtrl.js';
												});

							}, 50);

					
					
					
					

					 
					$scope.goToCrearActividad = function() {
						$scope.isCreateActividad = true;
						$scope.nb_actividad = "";

						$scope.aviso = "";
					}
					$scope.goToCancelarActividad = function() {
						$scope.isCreateActividad = false;
						$scope.nb_actividad = "";

						$scope.aviso = "";
					}

					$scope.goVerFormActualizarActividad = function() {
						$scope.isVerFormActualizarActividad = true;
						$scope.aviso = "";
					}

					$scope.goVerFormActividadesCreadas = function() {
						$scope.isVerFormActualizarActividad = false;
						$scope.aviso = "";
					}

					$scope.goToActualizarActividad = function(nb_actividad,
							nbActividadModif, isDelete) {
						$scope.nbActividadModif = nbActividadModif;
						$scope.nb_actividad = nb_actividad;
						$scope.isActualizarActividad = true;
						$scope.isDelete = isDelete;
						$scope.aviso = "";
					}
					$scope.goToCancelarActualizarActividad = function() {
						$scope.isActualizarActividad = false;
						$scope.nbActividadModif = "";

						$scope.aviso = "";
					}

					$scope.crearActividad = function(nb_actividad) {

						moduloService
								.create(
										{
											nbModulo : $scope.modulo,
											nb_actividad : nb_actividad
										},
										function success(actividadDto, status) {

											if (angular.isDefined(actividadDto)
													&& angular
															.isDefined(actividadDto.nbActividad)) {
												$scope.isCreateActividad = false;
												$scope.actividades
														.push(actividadDto);
												$scope.nb_actividad = "";

												$scope.aviso = "Creado satisfactoriamente "
														+ actividadDto.nbActividad;
											} else {
												$scope.aviso = "No se pudo crear la actividad";
											}

										},
										function err(httpResponse) {
											$scope.data = data || "FALSE";
											$scope.status = status;
											$scope.aviso = 'Ha pasado algo inesperado moduloCtrl.js No se pudo crear la actividad';
										});

					}

					$scope.deleteActividad = function(nb_modulo, nb_actividad) {
						if (confirm("�Seguro desea eliminar esta actividad?")) {

							moduloService
									.deletex(
											{
												nbModulo : nb_modulo,
												nb_actividad : nb_actividad
											},
											function success(actividadDto,
													status) {

												var log = [];
												angular
														.forEach(
																$scope.actividades,
																function(value,
																		key) {
																	if (value.nbActividad === nb_actividad
																			&& value.nbModulo === nb_modulo) {
																		$scope.actividades
																				.splice(
																						$scope.actividades
																								.indexOf(value),
																						1);
																	}

																}, log);

												$scope.nbActividadModif = "";

												$scope.isActualizarActividad = false;
												$scope.aviso = "Eliminado satisfactoriamente "
														+ actividadDto.nbActividad;

											},
											function err(httpResponse) {
												$scope.data = data || "FALSE";
												$scope.status = status;
												$scope.aviso = 'Ha pasado algo inesperado moduloCtrl.js No se pudo borrar la actividad';
											});
						}

					}

				  	$scope.accesosDirectos = function() {
						var path = $location.path();
						$timeout(function() {
							$location.path('/accesosDirectos');
						}, 50);
					}

					$scope.atras = function() {
						tipoCtrl = {
							isCtrl : "dashBoardCtrl"
						};
						tipoCtrlService.setProperty(tipoCtrl);
						var path = $location.path();
						$timeout(function() {
							$location.path('/dashBoard');
						}, 50);
						var absUrl = $location.absUrl();
						console.log(absUrl);
					}

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

					$scope.loadData = function(actividad, modulo, username,
							cedula) {

						tipoCtrl = {
							isCtrl : "actividadCtrl",
							modulo : modulo,
							actividad : actividad
						};
						tipoCtrlService.setProperty(tipoCtrl);

						var path = $location.path();

						$timeout(function() {
							$location.path('/actividad');
						}, 50);
					}

					$scope.actualizarActividad = function(nb_modulo,
							nb_actividad, nbActividadModif) {

						moduloService
								.update(
										{
											nbModulo : nb_modulo,
											nb_actividad : nb_actividad,
											nbActividadModif : nbActividadModif
										},
										function success(actividadDto, status) {

											$scope.isCreateActividad = false;

											for (var i = 0; i < $scope.actividades.length; i++) {
												if ($scope.actividades[i].nbActividad === nb_actividad
														&& $scope.actividades[i].nbModulo === nb_modulo) {
													$scope.actividades[i] = actividadDto;
													break;
												}
											}

											$scope.nbActividadModif = "";

											$scope.isActualizarActividad = false;
											$scope.aviso = "Actualizado satisfactoriamente "
													+ nb_actividad
													+ " a "
													+ nbActividadModif;

										},
										function err(httpResponse) {
											$scope.data = data || "FALSE";
											$scope.status = status;
											$scope.aviso = 'Ha pasado algo inesperado moduloCtrl.js No se pudo UPDATE la actividad';
										});

					}

					// --------------------------------------------------------------------------------------------------------------
					// ***************************************MODAL
					// VENTANA***********************************************************
					// --------------------------------------------------------------------------------------------------------------

					$scope.actualizar = function(nb_modulo, actividad,
							nbActividadModif) {
						$scope.openDelete('sm',
								"Perdera los historicos!.. Desea continuar?",
								nb_modulo, actividad, nbActividadModif);
					}

					$scope.animationsEnabled = true;

					$scope.openDelete = function(size, msg, nb_modulo,
							actividad, nbActividadModif) {
						$scope.items = [];
						$scope.items.push(actividad);
						tipoCtrlService.msg = msg;
						var modalInstance = $uibModal.open({
							animation : $scope.animationsEnabled,
							templateUrl : 'myModalContentDelete.html',
							controller : 'ModalInstanceCtrl',
							size : size,
							resolve : {
								items : function() {
									return $scope.items;
								}
							}
						});
						modalInstance.result.then(function(selectedItem) {

							$scope.actualizarActividad(nb_modulo, actividad,
									nbActividadModif);

						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.toggleAnimation = function() {
						$scope.animationsEnabled = !$scope.animationsEnabled;
					};

				});