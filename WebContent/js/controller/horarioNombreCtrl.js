app
		.controller(
				"horarioNombreCtrl",
				function($scope, $http, $resource, $location, sharedProperties,
						tipoCtrlService, $timeout, $uibModal, $log,
						horarioNombreService) {

					var tipoCtrl = tipoCtrlService.getProperty();
					$scope.isCtrl = tipoCtrl.isCtrl;
					$scope.modulo = tipoCtrl.modulo;
					$scope.grupo = tipoCtrl.grupo;
					$scope.subGrupo = tipoCtrl.subGrupo;
					$scope.gruponbModificar = tipoCtrl.gruponbModificar;
					$scope.subGruponbModificar = tipoCtrl.subGruponbModificar;
					$scope.actividad = tipoCtrl.actividad;
					
					

					$scope.userInfo = sharedProperties.getProperty();
					$scope.cedula = $scope.userInfo.cedula;
					$scope.username = $scope.userInfo.username;
					$scope.isCreate = false;
					$scope.isVerFormActualizar = false;
					$scope.isActualizar = false;
					$scope.objetos = [];
					$scope.matchPattern = new RegExp("^[0-9A-Za-z]");
					$scope.numMatchPattern = new RegExp("^[0-9]");
					$scope.nb_objeto = "";
					$scope.aviso = "";

					//:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo
					/** Traemos la data */
					$timeout(
							function() {
								horarioNombreService
										.query(
												{
													nbModulo : $scope.modulo,
													nbGrupoModulo : $scope.grupo,
													nbSubGrupoModulo:$scope.subGrupo,
													nb_actividad:$scope.actividad
												},
												function success(data, status) {
													$scope.objetos = data;
												},
												function err(httpResponse) {

													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js';
												});

							}, 50);


					$scope.goToCrear = function() {
						$scope.isCreate = true;
						$scope.nb_objeto = "";

						$scope.aviso = "";
					}
					$scope.goToCancelar = function() {
						$scope.isCreate = false;
						$scope.nb_objeto = "";

						$scope.aviso = "";
					}

					$scope.goVerFormActualizar = function() {
						$scope.isVerFormActualizar = true;
						$scope.aviso = "";
					}

					$scope.goVerFormActividadesCreadas = function() {
						$scope.isVerFormActualizar = false;
						$scope.aviso = "";
					}

					$scope.goToActualizar = function(nbModulo,nbGrupoModulo,nbSubGrupoModulo,nbActividad,nbModificar,isDelete) {
						
						  $scope.deleteYesDto = {};

						  horarioNombreService
							.isDelete(
									{
										nbModulo : nbModulo,
										nbGrupoModulo : nbGrupoModulo,
										nbSubGrupoModulo:nbSubGrupoModulo,
										nb_actividad:nbActividad,
										nbHorario:nbModificar
									},
									function success(data, status) {
										$scope.deleteYesDto = data;
										$scope.isDelete = $scope.deleteYesDto.deleteObj;
									},
									function err(httpResponse) {
										$scope.data = data
												|| "FALSE";
										$scope.status = status;
										$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js';
									});
	 
						
						$scope.modulo = nbModulo;
						$scope.grupo = nbGrupoModulo;
						$scope.subGrupoModulo = nbSubGrupoModulo;
						$scope.nbActividad = nbActividad;
						$scope.nbHorario=nbModificar;
						$scope.nbModificar = nbModificar;
						$scope.isActualizar = true;
						$scope.aviso = "";
					}
					$scope.goToCancelarActualizar = function() {
						$scope.isActualizar = false;
						$scope.nbModificar = "";

						$scope.aviso = "";
					}

					/** *Crear */
					$scope.crear = function(nb_objeto, modulo, grupo,subGrupoModulo,actividad) {
						/** Verificamos qe no existe */
						horarioNombreService
								.queryNoExiste(
										{
											nbModulo : modulo,
											nbGrupoModulo : grupo,
											nbSubGrupoModulo : subGrupoModulo,
											nb_actividad : actividad,
											nbHorario :nb_objeto
										},
										function success(data, status) {
											/**data.modificable = true, es que no existe y se puede crear*/
											if (data.modificable) {
												horarioNombreService
														.create(
																{
																	nbModulo : modulo,
																	nbGrupoModulo : grupo,
																	nbSubGrupoModulo : subGrupoModulo,
																	nb_actividad : actividad,
																	nbHorario :nb_objeto
																},

																function success(
																		out_dto,
																		status) {

																	if (angular
																			.isDefined(out_dto)
																			&& angular
																					.isDefined(out_dto.nbHorario)) {
																		$scope.isCreate = false;
																		$scope.objetos
																				.push(out_dto);
																		$scope.nb_objeto = "";

																		$scope.aviso = "Creado satisfactoriamente "
																				+ out_dto.nbHorario;
																	} else {
																		$scope.aviso = "No se pudo crear la actividad";
																	}

																},
																function err(
																		httpResponse) {

																	$scope.status = status;
																	$scope.aviso = 'Ha pasado algo inesperado moduloAnioMesaActividadCtrl.js No se pudo crear ';
																});

											} else {
												$scope.nb_objeto = "";
												$scope.isActualizar = false;
												$scope.aviso = "Existe un duplicado en el nombre "
														+ nb_objeto;
											}

										}, function err(httpResponse) {

										});

						/** Verificamos qe no existe */

					}
//
					$scope.deleteObjeto = function(modulo,grupo,subGrupoModulo,nbActividad,nbHorario) {
						if (confirm("¿Seguro desea eliminar esta actividad?")) {
							horarioNombreService
									.deletex(
											{
												nbModulo : modulo,
												nbGrupoModulo : grupo,
												nbSubGrupoModulo: subGrupoModulo,
												nb_actividad:nbActividad,
												nbHorario:nbHorario
											},
											function success(objDto, status) {

												var log = [];
												angular
														.forEach(
																$scope.objetos,
																function(value,
																		key) {
																	if (value.nbGrupo === grupo
																			&& value.nbModulo === modulo
																			&& value.nbSubGrupo === subGrupoModulo
																			&& value.nbActividad === nbActividad
																			&& value.nbHorario === nbHorario) {
																		$scope.objetos
																				.splice(
																						$scope.objetos
																								.indexOf(value),
																						1);
																	}

																}, log);
												
												$scope.nbModificar = "";

												$scope.isActualizar = false;
												$scope.aviso = "Eliminado satisfactoriamente "
														+ objDto.nbHorario;
											},
											function err(httpResponse) {
												$scope.status = status;
												$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js No se pudo borrar nbModificar';
											});
						}
					}
					
				  	$scope.accesosDirectos = function() {
						var path = $location.path();
						$timeout(function() {
							$location.path('/accesosDirectos');
						}, 50);
					}

					
					/** Retrocedemos */
					$scope.atras = function(modulo) {
						tipoCtrl = {
							isCtrl : "moduloAnioMesActividadCtrl",
							modulo : modulo,
							grupo : $scope.grupo,
							subGrupo:$scope.subGrupo,
							gruponbModificar : $scope.gruponbModificar,
							subGruponbModificar :$scope.subGruponbModificar
						};
						tipoCtrlService.setProperty(tipoCtrl);
						var path = $location.path();
						$timeout(function() {
							$location.path('/moduloAnioMesActividad');
						}, 50);
						var absUrl = $location.absUrl();
						console.log(absUrl);
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
							nbHorario :'none',
							htmlGo:'/horarioNombre',
							ctrlGo:'horarioNombreCtrl'
						};
						tipoCtrlService.setProperty(tipoCtrl);

						$timeout(function() {
							$location.path('/inscripcionByModulo');
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
							nbHorario :'none',
							htmlGo:'/horarioNombre',
							ctrlGo:'horarioNombreCtrl'
						};
						tipoCtrlService.setProperty(tipoCtrl);

						$timeout(function() {
							$location.path('/atencionByModulo');
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
							nbHorario :'none',
							htmlGo:'/horarioNombre',
							ctrlGo:'horarioNombreCtrl'
						};
						tipoCtrlService.setProperty(tipoCtrl);

						$timeout(function() {
							$location.path('/asistenciaByModulo');
						}, 50);
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

					/** Continuamnos para otra pantalla */

					$scope.loadData = function(nbModulo, nbGrupoModulo,nbSubGrupoModulo,gruponbModificar,subGruponbModificar,nbActividad,nbHorario, username,cedula) {
						tipoCtrl = {
							isCtrl : "actividadCtrl",
							modulo : nbModulo,
							grupo : nbGrupoModulo,
							subGrupo : nbSubGrupoModulo,
							gruponbModificar : gruponbModificar,
							subGruponbModificar : subGruponbModificar,
							actividad : nbActividad,
							nbHorario : nbHorario,
							username :username,
							cedula : cedula
							
						};
						tipoCtrlService.setProperty(tipoCtrl);

						var path = $location.path();

						$timeout(function() {
							$location.path('/actividad');
						}, 50);
					}
					
					 
					
					

					$scope.actualizarObjeto = function(modulo, grupo,
							subGrupoModulo,nbActividad,nbHorario,nbModificar) {
						horarioNombreService
								.queryNoExiste(
										{
											nbModulo : modulo,
											nbGrupoModulo : grupo,
											nbSubGrupoModulo : subGrupoModulo,
											nb_actividad : nbActividad,
											nbHorario : nbModificar
										},
										function success(data, status) {
											if (data.modificable) {
												horarioNombreService
														.update(
																{
																	nbModulo : modulo,
																	nbGrupoModulo : grupo,
																	nbSubGrupoModulo : subGrupoModulo,
																	nb_actividad : nbActividad,
																	nbHorario : nbHorario,
																	nbHorarioModif : nbModificar
																},
																function success(
																		data,
																		status) {

																	$scope.isCreate = false;

																	if (null != data
																			&& null != data.nbGrupo) {
																		for (var i = 0; i < $scope.objetos.length; i++) {
																			if ($scope.objetos[i].nbModulo === modulo
																					&& $scope.objetos[i].nbGrupo === grupo
																					&& $scope.objetos[i].nbSubGrupo === subGrupoModulo
																					&& $scope.objetos[i].nbActividad === nbActividad
																					&& $scope.objetos[i].nbHorario === nbHorario) {
																				
																				$scope.objetos[i] = data;
																				break;

																			}
																		}
																		$scope.nbModificar = "";
																		$scope.isActualizar = false;
																		$scope.isDelete = data.isDelete;
																		$scope.aviso = "Actualizado satisfactoriamente "
																				+ " a "
																				+ nbModificar;
																	}

																},
																function err(
																		httpResponse) {
																 
																	$scope.status = status;
																	$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js No se pudo UPDATE la actividad';
																});
											} else {
												$scope.nbModificar = "";
												$scope.isActualizar = false;
												$scope.aviso = "Existe un duplicado en el nombre "
														+ nbModificar;
											}

										}, function err(httpResponse) {

										});

					}

					// --------------------------------------------------------------------------------------------------------------
					// ***************************************MODAL
					// VENTANA***********************************************************
					// --------------------------------------------------------------------------------------------------------------
					$scope.actualizar = function(modulo, grupo,
							subGrupoModulo,nbActividad,nbHorario,nbModificar) {
						$scope.openDelete('sm',
								"Perdera los historicos!.. Desea continuar?",
								modulo, grupo, subGrupoModulo,nbActividad,nbHorario,nbModificar);
					}

					$scope.animationsEnabled = true;

					$scope.openDelete = function(size, msg, modulo, grupo,
							subGrupoModulo,nbActividad,nbHorario,nbModificar) {
						$scope.items = [];
						$scope.items.push(nbModificar);
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

							$scope.actualizarObjeto(modulo, grupo,
									subGrupoModulo,nbActividad,nbHorario,nbModificar);

						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.toggleAnimation = function() {
						$scope.animationsEnabled = !$scope.animationsEnabled;
					};

				});