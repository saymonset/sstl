app
		.controller(
				"estadisticaCtrl",
				function($scope, $http,  $resource, $location,
						sharedProperties, tipoCtrlService, horarioService,
						miembroService,estadisticaService, $timeout, $rootScope) {

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
					$scope.show = false;

					$timeout(
							function() {

								horarioService
										.query(
												{
													
													nbModulo : $scope.modulo,
													nbGrupoModulo:  $scope.grupo,
													nbSubGrupoModulo:  $scope.subGrupo,
													nb_actividad:  $scope.actividad
													
												},
												function success(data, status) {
													$scope.events = data;
												},
												function err(httpResponse) {
													$scope.data = data
															|| "FALSE";
													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado stadisticaCtrl.js->horarioService.query';
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
													nbHorario: $scope.nbHorario
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

					$scope.atencion = function(nbModulo, nbActividad, cedula,
							id, start, end, text) {

						atencionService
								.create(
										{
											text : text,
											id : id,
											start : start,
											end : end,
											nbModulo : $scope.modulo,
											nbGrupoModulo:  $scope.grupo,
											nbSubGrupoModulo:  $scope.subGrupo,
											nb_actividad:  $scope.actividad,
											nbHorario:  $scope.nbHorario,
											cedula : cedula,
											atencionRealizada : 'atencionRealizada,observar staidisticactrl.js'
										},
										function success(atencionDto, status) {
											if (angular
													.isDefined(atencionDto)
													&& angular
															.isDefined(atencionDto.cedula)
													&& atencionDto.cedula != null) {
												$scope.isCreateActividad = false;
												$scope.aviso = "Creado satisfactoriamente "
														+ atencionDto.cedula;
											} else {
												$scope.aviso = "No se pudo crear "
														+ cedula;
											}
										},
										function err(httpResponse) {

											$scope.status = status;
											$scope.aviso = 'Ha pasado algo inesperado estadisticaCtrl.js->atencionService.create';
										});

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

				  	$scope.accesosDirectos = function() {
						var path = $location.path();
						$timeout(function() {
							$location.path('/accesosDirectos');
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
					
					/** *Buscamos atencion****Grafico******** */
					estadisticaService
					.query(
							{
								nbModulo : $scope.modulo,
								nbGrupoModulo:  $scope.grupo,
								nbSubGrupoModulo:  $scope.subGrupo,
								nb_actividad:  $scope.actividad,
								nbHorario:  $scope.nbHorario
								
							},
							function success(chartDto, status) {
								var ctx = document.getElementById("myChart");
								var myChart = new Chart(ctx, chartDto);			},
							function err(httpResponse) {
								$scope.status = status;
								$scope.aviso = 'Ha pasado algo inesperado estadisticaCtrl.js->estadisticaService.query';
							});
					
					
					
					
					
					

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
							//			 
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
						var today = DayPilot.Date.today()
								.toString("yyyy-MM-dd");
						$scope.displayMode = "edit";
						$scope.text = args.e.text();
						$scope.id = args.e.id();
						$scope.start = args.e.start().toString();
						$scope.end = args.e.end().toString();
						$scope.show = true;
						$scope.aviso = "";
					}

					$scope.name = "me";

				});