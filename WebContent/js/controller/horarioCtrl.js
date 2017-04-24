app
		.controller(
				"horarioCtrl",
				function($scope, $http, $resource, $location, sharedProperties,
						tipoCtrlService, $timeout, $rootScope, horarioService) {
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

					$timeout(
							function() {
								horarioService
										.query(
												{
													nbModulo : $scope.modulo,
													nbGrupoModulo : $scope.grupo,
													nbSubGrupoModulo : $scope.subGrupo,
													nb_actividad : $scope.actividad,
													nbHorario:$scope.nbHorario
													
												},
												function success(data, status) {
													$scope.events = data;
												},
												function err(httpResponse) {

													$scope.status = status;
													$scope.aviso = 'Ha pasado algo inesperado horarioCtrl.js->horarioService.query';
												});

							}, 50);

					$scope.userInfo = sharedProperties.getProperty();
					$scope.cedula = $scope.userInfo.cedula;
					$scope.username = $scope.userInfo.username;

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
						heightSpec : "Full",
						headerDateFormat : "dddd",
						locale : "es-es",
						// viewType : "Week T06:00:00 to T19:00:00",
						// scale : "Day",
						// startHours : "T06:00:00 to T19:00:00",
						// days : 31,
						// headerDateFormat : "dddd",
						// locale : "es-es",

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

						$scope.displayMode = "edit";
						$scope.text = args.e.text();
						$scope.id = args.e.id();
						$scope.start = args.e.start().toString();
						$scope.end = args.e.end().toString();
					}
					$scope.saveEdit = function(modulo, grupo, subGrupo,
							actividad, nbHorario, text, id,start,end) {
						horarioService
								.update(
										{
											nbModulo : modulo,
											nbGrupoModulo : grupo,
											nbSubGrupoModulo : subGrupo,
											nb_actividad : actividad,
											nbHorario :nbHorario,
											text : text,
											id : id,
											start : start,
											end : end
										},
										function success(horarioDto, status) {
											// Modificamos lo que esta en java
											// script, solo el elemento que se
											// altero
											for (var i = 0; i < $scope.events.length; i++) {
												if ($scope.events[i].id == id) {
													$scope.events[i].text = horarioDto.text;
													break;
												}
											}
										},
										function err(httpResponse) {
											$scope.aviso = 'Ha pasado algo inesperado horarioCtrl.js->horarioService.update';
										});
						$scope.text = "";
						$scope.id = "";
						$scope.displayMode = "list";
					}

					$scope.remove = function(modulo, grupo, subGrupo,
							actividad, nbHorario, text, id) {

						horarioService
								.deletex(
										{
											nbModulo : modulo,
											nbGrupoModulo : grupo,
											nbSubGrupoModulo : subGrupo,
											nb_actividad : actividad,
											nbHorario:nbHorario,
											text : text,
											id : id
										},
										function success(horarioDto, status) {
											for (var i = 0; i < $scope.events.length; i++) {
												if ($scope.events[i].id == id) {
													$scope.events
															.splice(
																	$scope.events
																			.indexOf($scope.events[i]),
																	1);
												}
											}
										},
										function err(httpResponse) {
											$scope.aviso = 'Ha pasado algo inesperado horarioCtrl.js->horarioService.deletex';
										});
						$scope.text = "";
						$scope.id = "";
						$scope.displayMode = "list";
					}

					$scope.cancelEdit = function() {
						$scope.text = "";
						$scope.id = "";
						$scope.displayMode = "list";
					}
					$scope.name = "me";

					$scope.add = function() {
						var today = DayPilot.Date.today()
								.toString("yyyy-MM-dd");
						console.log(today);
						$scope.events.push({
							start : new DayPilot.Date(today + "T10:00:00"),
							end : new DayPilot.Date(today + "T12:00:00"),
							id : DayPilot.guid(),
							text : "Nuevo Bloque de horas"
						});
					};

				});