app
		.controller(
				"atencionCtrl",
				function($scope, $http,  $resource, $location,
						sharedProperties, tipoCtrlService, miembroService,
						horarioService, estadisticaService, atencionService,
						$timeout, $rootScope, uibDateParser, $uibModal, $log,
						parametroService) {
 
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
					$scope.numeroCupos = 0;
					$scope.showIngHorario = false;
					$scope.miembroDto = {};
					$scope.selectionDelete = [];
					$scope.all = false;
					
					$scope.atencion = function(text,id,start,end,nbModulo,nbActividad,cedula,atencionRealizada) {
						 if (angular.isUndefined(atencionRealizada)){
							 atencionRealizada=null;
							 console.log(atencionRealizada);
						 }
							 
								var url =  "services/atencionCtrl/" + text
										+ "/" + id + "/" + start+ "/" + end+ "/" + nbModulo+"/"+$scope.grupo +"/"+$scope.subGrupo+ "/"
                            +$scope.actividad+ "/"+ $scope.nbHorario+ "/" + cedula+"/"+atencionRealizada;
								$http.post(url).success(
										function(atencionDto) {
											if (angular.isDefined(atencionDto) && angular.isDefined(atencionDto.cedula) && atencionDto.cedula!=null){
												$scope.isCreateActividad = false;
												$scope.aviso="Creado satisfactoriamente "+atencionDto.cedula;
											}else{
												$scope.aviso="No se pudo crear "+cedula;
											}
										});
								   
						}
					
					



					/** ****Bloque para eliminar********** */

					$scope.eliminar = function(selectionDelete) {
						$scope.openDelete('sm',
								"Seguro desea eliminar estos registros ?",
								selectionDelete);
					}

					/** ****Fin Bloque para eliminar********** */

					$timeout(
							function() {
								parametroService
										.query(
												{

													nbModulo : $scope.modulo,
													nbGrupoModulo:  $scope.grupo,
													nbSubGrupoModulo:  $scope.subGrupo,
													nb_actividad:  $scope.actividad,
													nbHorario:               $scope.nbHorario, 
													txNombreParametro : 'numerocupos'
												},
												function success(data, status) {
													$scope.numeroCupos = data.txValorParametro;
												},
												function err(httpResponse) {
													$scope.data = data
															|| "FALSE";
													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado atencionCtrl.js->parametroService.query';
												});
								 
							}, 50);

					$timeout(
							function() {

								miembroService
										.query(
												{
													nbModulo : $scope.modulo,
													nbGrupoModulo:  $scope.grupo,
													nbSubGrupoModulo:  $scope.subGrupo,
													nb_actividad:  $scope.actividad,
													nbHorario:               $scope.nbHorario
												},
												function success(data, status) {
													$scope.miembros = data;
												},
												function err(httpResponse) {
													$scope.miembros = data
															|| "FALSE";
													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.query';
												});

							 
							}, 50);

					$scope.crear = function(nbModulo, nbActividad, cedula) {

						miembroService
								.update(
										{
											nbModulo : nbModulo,
											nbGrupoModulo:  $scope.grupo,
											nbSubGrupoModulo:  $scope.subGrupo,
											nb_actividad: nbActividad,
											nbHorario:               $scope.nbHorario, 
											cedula : cedula
										},
										function success(miembroDto, status) {
											if (angular.isDefined(miembroDto)
													&& angular
															.isDefined(miembroDto.tipoEmp)) {
												$scope.isCreateActividad = false;
												$scope.miembros
														.push(miembroDto);
												$scope.nb_actividad = "";

												$scope.aviso = "Creado satisfactoriamente "
														+ miembroDto.cedula;
											} else {
												$scope.aviso = "No se pudo crear "
														+ cedula;
											}
										},
										function err(httpResponse) {

											$scope.status = status;
											$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.update';
										});
					}

					$scope.deleteM = function(nbModulo, nbActividad, cedula) {
						if (confirm("�Seguro desea eliminar  ?")) {
							miembroService
									.deletex(
											{
												nbModulo : nbModulo,
												nbGrupoModulo:  $scope.grupo,
												nbSubGrupoModulo:  $scope.subGrupo,
												nb_actividad: nbActividad,
												nbHorario:               $scope.nbHorario,  
												cedula : cedula
											},
											function success(miembroDto, status) {
												var log = [];
												angular
														.forEach(
																$scope.miembros,
																function(value,
																		key) {
																	if (value.nbActividad === nbActividad
																			&& value.nbModulo === nbModulo
																			&& value.cedula === cedula) {
																		$scope.miembros
																				.splice(
																						$scope.miembros
																								.indexOf(value),
																						1);
																	}

																}, log);
												$scope.nbActividadModif = "";
												$scope.aviso = "Eliminado satisfactoriamente "
														+ miembroDto.nbActividad;
											},
											function err(httpResponse) {
												$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->miembroService.deletex';
											});
						}

					}

					$scope.showHorario = function(nbModulo, nbActividad, cedula) {

						$scope.diaPrevious = false;
						$scope.diaNext = false;
						$scope.diaBusy = false;
						$scope.aviso = "";

						$timeout(function() {
							
							miembroService
							.findHorarioByCedula(
									{
										nbModulo : nbModulo,
										nbGrupoModulo:  $scope.grupo,
										nbSubGrupoModulo:  $scope.subGrupo,
										nb_actividad: nbActividad,
										nbHorario:               $scope.nbHorario,  
										cedula : cedula
									},
									function success(data, status) {
										$scope.events = data;
										$scope.showIngHorario = true;
									},
									function err(httpResponse) {
										$scope.aviso = 'Ha pasado algo inesperado atencionCtrl.js->miembroService.findHorarioByCedula';
									});
							
							
							
							
							
							
							
						}, 50);

						$timeout(
								function() {

									miembroService
											.findByCedula(
													{
														nbModulo : nbModulo,
														nbGrupoModulo:  $scope.grupo,
														nbSubGrupoModulo:  $scope.subGrupo,
														nb_actividad: nbActividad,
														nbHorario:               $scope.nbHorario,  
														cedula : cedula
													},
													function success(data,
															status) {
														$scope.miembroDto = data;
													},
													function err(httpResponse) {

														$scope.status = status;
														$scope.aviso = 'Ha pasado algo inesperado atencionCtrl.js->miembroService.findByCedula';
													});

								}, 50);

						/** *Buscamos atencion****Grafico******** */

//						estadisticaService
//								.getByUsuario(
//										{
//											nbModulo : nbModulo,
//											nb_actividad : nbActividad,
//											cedula : cedula
//										},
//										function success(chartDto, status) {
//											var ctx = document
//													.getElementById("myChart");
//											var myChart = new Chart(ctx,
//													chartDto);
//										},
//										function err(httpResponse) {
//											$scope.status = status;
//											$scope.aviso = 'Ha pasado algo inesperado atencionCtrl.js->estadisticaService.queryByUsuario';
//										});

				 

					}

					$scope.updateMiembroGoLista = function(miembroDto) {
						miembroDto.fechaStr = miembroDto.feRenovacion;
						if (miembroDto != null
								&& miembroDto.feRenovacion == null
								&& miembroDto.renovable) {
							$scope.open('sm', "Falta la Fecha Renovar");
						} else {
							
//:nbModulo/:nbGrupoModulo/:nbSubGrupoModulo/:nb_actividad/:nbHorario		
							
//							private String nbModulo;
//							private String nbActividad;
//							private String nbGrupo;
//							private String nbSubGrupo;
//							private String nbHorario;
							
							
							miembroService
							.updateMiembro(
									{
										nbModulo : miembroDto.nbModulo,
										nbGrupoModulo:miembroDto.nbGrupo,
										nbSubGrupo:miembroDto.nbSubGrupo,
										nbHorario:miembroDto.nbHorario,
										nbActividad:miembroDto.nbActividad,
										cedula:miembroDto.cedula,
										nombre:miembroDto.nombre,
										tipoEmp:miembroDto.tipoEmp,
										renovable:miembroDto.renovable,
										profesor:miembroDto.profesor,
										feRenovacion:miembroDto.feRenovacion
									}	,
									function success(miembroDto, status) {
										
										if (miembroDto) {

											// Modificamos lo que esta
											// en java script, solo el
											// elemento que se altero
											for (var i = 0; i < $scope.miembros.length; i++) {
												if ($scope.miembros[i].cedula == miembroDto.cedula
														&& $scope.miembros[i].nbModulo == miembroDto.nbModulo
														&& $scope.miembros[i].nbGrupoModulo == miembroDto.nbGrupoModulo
														&& $scope.miembros[i].nbSubGrupo == miembroDto.nbSubGrupo
														&& $scope.miembros[i].nbHorario == miembroDto.nbHorario
														&& $scope.miembros[i].nbActividad == miembroDto.nbActividad) {
													$scope.miembros[i] = miembroDto;
													break;
												}
											}

											$scope.aviso = "Modificado satisfactoriamente "
													+ miembroDto.cedula;
											$scope.pagContador = 1;
											$scope.cedula = "";
										} else {
											$scope.aviso = "No se pudo crear "
													+ cedula;
										}
										 				},
									function err(httpResponse) {
										$scope.status = status;
										$scope.aviso = 'Ha pasado algo inesperado atencionCtrl.js->miembroService.updateMiembro';
									});

							$scope.showIngHorario = false;
							$scope.searchCedula = "";
						}

					}

					$scope.cancelar = function() {
						$scope.pagContador = 1;
						$scope.cedula = "";
						$scope.showIngHorario = false;
						$scope.searchCedula = "";
						$scope.horarioSeleccionado = [];
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


					$scope.atras = function(modulo, actividad) {

						tipoCtrl = {
							isCtrl : "actividadCtrl",
							modulo : modulo,
							grupo : $scope.grupo,
							subGrupo : $scope.subGrupo,
							gruponbModificar : $scope.gruponbModificar,
							subGruponbModificar : $scope.subGruponbModificar,
							actividad : actividad,
							nbHorario : $scope.nbHorario
						};
						console.log(tipoCtrl);
						tipoCtrlService.setProperty(tipoCtrl);
						var path = $location.path();
						$timeout(function() {
							$location.path('/actividad');
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


					$scope.llenaHorario = function(pagContador) {
						if ((pagContador == 3)
								&& angular.isDefined($scope.checkboxModel)
								&& angular
										.isDefined($scope.checkboxModel.isConfigHorario)
								&& $scope.checkboxModel.isConfigHorario == true
								&& $scope.horarioSeleccionado.length > 0) {
							$timeout(function() {
								$scope.events = $scope.horarioSeleccionado;

							}, 50);
						} else {
							$timeout(
									function() {

										horarioService
												.findByModuloActividadMoveDisabled(
														{
															nbModulo : miembroDto.nbModulo,
															nbGrupoModulo:miembroDto.nbGrupo,
															nbSubGrupo:miembroDto.nbSubGrupo,
															nbHorario:miembroDto.nbHorario,
															nbActividad:miembroDto.nbActividad,
														},
														function success(data,
																status) {
															$scope.events = data;
														},
														function err(
																httpResponse) {
															$scope.status = status;
															$scope.aviso = 'Ha pasado algo inesperado atencionCtrl.js->horarioService.findByModuloActividadMoveDisabled';
														});
 
									}, 50);
						}
					}

					// //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
					// /FECHAS
					$scope.today = function() {
						$scope.miembroDto.feRenovacion = new Date();
					};
					$scope.today();

					$scope.clear = function() {
						$scope.miembroDto.feRenovacion = null;
					};

					$scope.options = {
						customClass : getDayClass,
						minDate : new Date(),
						showWeeks : true
					};

					// Disable weekend selection
					function disabled(data) {
						var date = data.date, mode = data.mode;
						return mode === 'day'
								&& (date.getDay() === 0 || date.getDay() === 6);
					}

					$scope.toggleMin = function() {
						$scope.options.minDate = $scope.options.minDate ? null
								: new Date();
					};

					$scope.toggleMin();

					$scope.setDate = function(year, month, day) {
						$miembroDto.feRenovacion = new Date(year, month, day);
					};

					var tomorrow = new Date();
					tomorrow.setDate(tomorrow.getDate() + 1);
					var afterTomorrow = new Date(tomorrow);
					afterTomorrow.setDate(tomorrow.getDate() + 1);
					$scope.events = [ {
						date : tomorrow,
						status : 'full'
					}, {
						date : afterTomorrow,
						status : 'partially'
					} ];

					function getDayClass(data) {
						var date = data.date, mode = data.mode;
						if (mode === 'day') {
							var dayToCheck = new Date(date)
									.setHours(0, 0, 0, 0);

							for (var i = 0; i < $scope.events.length; i++) {
								var currentDay = new Date($scope.events[i].date)
										.setHours(0, 0, 0, 0);

								if (dayToCheck === currentDay) {
									return $scope.events[i].status;
								}
							}
						}

						return '';
					}

					// tratamiento del horario

					$scope.displayMode = "";
					$scope.text = "";
					$scope.start = "";
					$scope.end = "";
					$scope.id = "";
					$scope.events = [];

					$scope.weekConfig = {

						viewType : "Week",
						scale : "Day",
						days : 14,
						heightSpec: "Full",
						headerDateFormat : "dddd",
						locale : "es-es",

						onEventResized : function(args) {
							$scope.procesarEvent(args);
						},
						onEventMoved : function(args) {
							$scope.procesarEvent(args);
						},
						onEventClicked : function(args) {
							$scope.procesarEvent(args);

						},

						scrollToHour : 12

					};

					$scope.edit = function() {
						$scope.displayMode = "edit";
					}

					$scope.procesarEvent = function(args) {

						$scope.text = args.e.text();
						$scope.id = args.e.id();
						$scope.start = args.e.start().toString();
						$scope.end = args.e.end().toString();
						$scope.diaPrevious = false;
						$scope.diaNext = false;
						$scope.diaBusy = false;
						var obj = {};
						obj.text = $scope.text;
						obj.id = $scope.id;
						obj.start = $scope.start;
						obj.end = $scope.end;
						obj.backColor = "#fff099";
						obj.moveDisabled = true;
						$scope.horarioSeleccionado = [];

						// Verificamos que si se puede agregar al calendario
						for (var i = 0; i < $scope.events.length; i++) {
							if ($scope.events[i].id == obj.id) {
								if ($scope.events[i].diaPrevious) {
									$scope.diaPrevious = true;
								} else if ($scope.events[i].diaNext) {
									$scope.diaNext = true;
								} else if ($scope.events[i].diaBusy) {
									$scope.diaBusy = true;
								} else {
									ingresarDiaEnHorario(
											$scope.horarioSeleccionado, obj.id,
											obj);
								}

								break;
							}
						}

					}

					function ingresarDiaEnHorario(source, id, obj) {
						$scope.horarioSeleccionado.push(obj);
					}

					$scope.deleteBloque = function(item) {
						$scope.horarioSeleccionado.splice(
								$scope.horarioSeleccionado.indexOf(item), 1);
					}
					$scope.name = "me";

					// --------------------------------------------------------------------------------------------------------------
					// ***************************************MODAL
					// VENTANA***********************************************************
					// --------------------------------------------------------------------------------------------------------------
					$scope.items = $scope.selectionDelete;
					;// ['item1', 'item2',
					// 'item3'];

					$scope.animationsEnabled = true;

					$scope.open = function(size, msg) {

						tipoCtrlService.msg = msg;
						var modalInstance = $uibModal.open({
							animation : $scope.animationsEnabled,
							templateUrl : 'myModalContent.html',
							controller : 'ModalInstanceCtrl',
							size : size,
							resolve : {
								items : function() {
									return $scope.items;
								}
							}
						});
						modalInstance.result.then(function(selectedItem) {
							$scope.selected = selectedItem;
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.openDelete = function(size, msg, selectionDelete) {
						tipoCtrlService.msg = msg;
						var modalInstance = $uibModal.open({
							animation : $scope.animationsEnabled,
							templateUrl : 'myModalContentDelete.html',
							controller : 'ModalInstanceCtrl',
							size : size,
							resolve : {
								items : function() {
									return selectionDelete;
								}
							}
						});
						modalInstance.result.then(function(selectedItem) {
							$scope.selected = selectedItem;

							var log = [];
							angular.forEach($scope.selectionDelete, function(
									value, key) {
								console.log(value.cedula);
								$scope.miembros.splice($scope.miembros
										.indexOf(value), 1);
							}, log);

						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.toggleAnimation = function() {
						$scope.animationsEnabled = !$scope.animationsEnabled;
					};

				});