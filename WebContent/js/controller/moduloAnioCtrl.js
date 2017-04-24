app
		.controller(
				"moduloAnioCtrl",
				function($scope, $http, $resource, $location, sharedProperties,
						tipoCtrlService, $timeout, $uibModal, $log,
						moduloService,moduloServiceAnio) {

					var tipoCtrl = tipoCtrlService.getProperty();
					$scope.isCtrl = tipoCtrl.isCtrl;
					$scope.modulo = tipoCtrl.modulo;

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
					
					/**Traemos la data*/
					$timeout(
							function() {
								moduloServiceAnio
										.query(
												{
													nbModulo : $scope.modulo
												},
												function success(data, status) {
													$scope.objetos = data;
												},
												function err(httpResponse) {
													$scope.data = data
															|| "FALSE";
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

					$scope.goToActualizar = function(nb_objeto,
							nbActividadModif, isDelete) {

						$scope.deleteYesDto = {};
						
						moduloServiceAnio
						.isDelete(
								{
									nbModulo : $scope.modulo,
									nbGrupoModulo : nb_objeto
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
 
						
						
						$scope.nbActividadModif = nbActividadModif;
						$scope.nb_objeto = nb_objeto;
						$scope.isActualizar = true;
						
						
						$scope.aviso = "";
					}
					$scope.goToCancelarActualizar = function() {
						$scope.isActualizar = false;
						$scope.nbActividadModif = "";

						$scope.aviso = "";
					}

					
					/***Crear */
					$scope.crear = function(nb_objeto) {
						
						/**Verificamos qe no existe*/
						moduloServiceAnio
					         .queryNoExiste(
							{
								nbModulo : $scope.modulo,
								nbGrupoModulo : nb_objeto,
								nbModificar : nb_objeto
							},
							function success(data, status) {
					            if (data.modificable){
					            	moduloServiceAnio
								     	.create(
											{
												nbModulo : $scope.modulo,
												nbGrupoModulo : nb_objeto
											},
											function success(actividadDto, status) {

												if (angular.isDefined(actividadDto)
														&& angular
																.isDefined(actividadDto.nbModificar)) {
													$scope.isCreate = false;
													$scope.objetos
															.push(actividadDto);
													$scope.nb_objeto = "";

													$scope.aviso = "Creado satisfactoriamente "
															+ actividadDto.nbModificar;
												} else {
													$scope.aviso = "No se pudo crear la actividad";
												}

											},
											function err(httpResponse) {
												$scope.data = data || "FALSE";
												$scope.status = status;
												$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js No se pudo crear la actividad';
											});
					            	
					            }else{
					            	$scope.nbActividadModif = "";
									$scope.isActualizar = false;
									$scope.aviso = "Existe un duplicado en el nombre "
											+ nb_objeto;
					            }
					            
							},
							function err(httpResponse) {
								 
							});

					

					}

					$scope.deleteObjeto = function(nb_modulo, nb_objeto) {
						if (confirm("�Seguro desea eliminar esta actividad?")) {

							moduloServiceAnio
									.deletex(
											{
												nbModulo : nb_modulo,
												nbGrupoModulo : nb_objeto
											},
											function success(objDto,
													status) {

												var log = [];
												angular
														.forEach(
																$scope.objetos,
																function(value,
																		key) {
																	if (value.nbGrupoModulo === nb_objeto
																			&& value.nbModulo === nb_modulo) {
																		$scope.objetos
																				.splice(
																						$scope.objetos
																								.indexOf(value),
																						1);
																	}

																}, log);

												$scope.nbActividadModif = "";

												$scope.isActualizar = false;
												$scope.aviso = "Eliminado satisfactoriamente "
														+ objDto.nbModificar;

											},
											function err(httpResponse) {
												$scope.status = status;
												$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js No se pudo borrar la actividad';
											});
						}

					}
					
				  	$scope.accesosDirectos = function() {
						var path = $location.path();
						$timeout(function() {
							$location.path('/accesosDirectos');
						}, 50);
					}

					
					/**Retrocedemos*/
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

					/**Continuamnos para otra pantalla*/
					$scope.loadData = function(nbModificar,grupo, modulo, username,
							cedula) {
						tipoCtrl = {
							isCtrl : "moduloAnioMesCtrl",
							modulo : modulo,
							grupo : grupo,
							gruponbModificar:nbModificar
						};
						tipoCtrlService.setProperty(tipoCtrl);

						var path = $location.path();

						$timeout(function() {
							$location.path('/moduloAnioMes');
						}, 50);
					}
					
					
					$scope.atencionByModulo = function(modulo) {
						tipoCtrl = {
							isCtrl : "atencionByModuloCtrl",
							modulo : modulo,
							grupo : 'none',
							subGrupo : 'none',
							gruponbModificar : $scope.gruponbModificar,
							subGruponbModificar : $scope.subGruponbModificar,
							actividad : 'none' ,
							nbHorario :'none',
							htmlGo:'/moduloAnio',
							ctrlGo:'moduloAnioCtrl'
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
							grupo : 'none',
							subGrupo : 'none',
							gruponbModificar : $scope.gruponbModificar,
							subGruponbModificar : $scope.subGruponbModificar,
							actividad : 'none' ,
							nbHorario :'none',
							htmlGo:'/moduloAnio',
							ctrlGo:'moduloAnioCtrl'
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
							grupo : 'none',
							subGrupo : 'none',
							gruponbModificar : $scope.gruponbModificar,
							subGruponbModificar : $scope.subGruponbModificar,
							actividad : 'none' ,
							nbHorario :'none',
							htmlGo:'/moduloAnio',
							ctrlGo:'moduloAnioCtrl'
						};
						tipoCtrlService.setProperty(tipoCtrl);

						$timeout(function() {
							$location.path('/asistenciaByModulo');
						}, 50);
					}
					
					
					

					$scope.actualizarObjeto = function(nb_modulo,
							nb_objeto, nbActividadModif) {
						
						
						moduloServiceAnio
						    .queryNoExiste(
								{
									nbModulo : nb_modulo,
									nbGrupoModulo : nb_objeto,
									nbModificar : nbActividadModif
								},
								function success(data, status) {
						            if (data.modificable){
						            	moduloServiceAnio
										.update(
												{
													nbModulo : nb_modulo,
													nbGrupoModulo : nb_objeto,
													nbModificar : nbActividadModif
												},
												function success(data, status) {

													$scope.isCreate = false;
		                                            
													if (null!=data && null!=data.nbGrupoModulo){
														for (var i = 0; i < $scope.objetos.length; i++) {
															if ($scope.objetos[i].nbGrupoModulo === nb_objeto
																	&& $scope.objetos[i].nbModulo === nb_modulo) {
																$scope.objetos[i] = data;
																break;
															}
														}
														$scope.nbActividadModif = "";
														$scope.isActualizar = false;
														$scope.aviso = "Actualizado satisfactoriamente "
																+ " a "
																+ nbActividadModif;
													} 
													
												},
												function err(httpResponse) {
													$scope.data = data || "FALSE";
													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado moduloAnioCtrl.js No se pudo UPDATE la actividad';
												});
						            }else{
						            	$scope.nbActividadModif = "";
										$scope.isActualizar = false;
										$scope.aviso = "Existe un duplicado en el nombre "
												+ nbActividadModif;
						            }
						            
								},
								function err(httpResponse) {
									 
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

							$scope.actualizarObjeto(nb_modulo, actividad,
									nbActividadModif);

						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.toggleAnimation = function() {
						$scope.animationsEnabled = !$scope.animationsEnabled;
					};

				});