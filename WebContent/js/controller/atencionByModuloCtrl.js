app
		.controller(
				"atencionByModuloCtrl",
				function($scope, $http, $resource, $location, sharedProperties,
						tipoCtrlService, $timeout, seguridadService,
						configuracionService, estadisticaService,$uibModal,$log) {

					var tipoCtrl = tipoCtrlService.getProperty();
					$scope.isCtrl = tipoCtrl.isCtrl;
					$scope.modulo = tipoCtrl.modulo;
					$scope.grupo = tipoCtrl.grupo;
					$scope.subGrupo = tipoCtrl.subGrupo;
					$scope.gruponbModificar = tipoCtrl.gruponbModificar;
					$scope.subGruponbModificar = tipoCtrl.subGruponbModificar;
					$scope.actividad = tipoCtrl.actividad;
					$scope.nbActividadModificar = tipoCtrl.actividad == 'none' ? ''
							: tipoCtrl.actividad;
					$scope.nbHorario = tipoCtrl.nbHorario;
					$scope.nbHorarioModificar = tipoCtrl.nbHorario == 'none' ? ''
							: tipoCtrl.nbHorario;
					$scope.htmlGo = tipoCtrl.htmlGo;
					$scope.ctrlGo = tipoCtrl.ctrlGo;
					$scope.matchPattern = new RegExp("^[A-Za-z]");
					
	 					
					$scope.userInfo = sharedProperties.getProperty();
					$scope.cedula = $scope.userInfo.cedula;
					$scope.username = $scope.userInfo.username;
					$scope.seguridadDto = {};
					$scope.estadisticasByModuloDto = {};
					$scope.estadisticasByModuloDto.feDesde = new Date();
					$scope.estadisticasByModuloDto.feHasta = new Date();
					$scope.actividades = {};
					
					$scope.miembroAsistenciaHorarioDto =  [];

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
					
					
					$scope.atras = function(htmlGo, ctrlGo) {
						tipoCtrl = {
							isCtrl : ctrlGo,
							modulo : $scope.modulo,
							grupo : $scope.grupo,
							subGrupo : $scope.subGrupo,
							gruponbModificar : $scope.gruponbModificar,
							subGruponbModificar : $scope.subGruponbModificar,
							actividad : $scope.actividad,
							nbHorario : $scope.nbHorario
						};
						console.log(tipoCtrl);
						tipoCtrlService.setProperty(tipoCtrl);
						var path = $location.path();
						$timeout(function() {
							$location.path(htmlGo);
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


					// //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

					// /FECHAS
					$scope.defaultValuesCheck = function() {
						
						$scope.miembroAsistenciaHorarioDto =[];
						
						if ($scope.estadisticasByModuloDto.swFeDesde) {
							if (angular
									.isDate($scope.estadisticasByModuloDto.feDesde)) {
								$scope.estadisticasByModuloDto.swFeDesde = false;
								$scope.estadisticasByModuloDto.feHasta = null;
								
							}
						}
						if ($scope.estadisticasByModuloDto.swFeHasta) {
							if (angular
									.isDate($scope.estadisticasByModuloDto.feHasta)) {
								$scope.estadisticasByModuloDto.swFeHasta = false;

							}
						}
				
						if (validaFechaClick()) {
							$scope.graficar();
						}
					};

					$scope.defaultValuesNullDesdeCheck = function() {
						$scope.estadisticasByModuloDto.feDesde = null;
						$scope.estadisticasByModuloDto.feHasta = null;
					}
					$scope.defaultValuesNullHastaCheck = function() {
						$scope.estadisticasByModuloDto.feHasta = null;
					}

					$scope.today = function() {

						$scope.miembroAsistenciaHorarioDto =[];
						
						if ($scope.estadisticasByModuloDto.swFeDesde) {
							$scope.estadisticasByModuloDto.feDesde = new Date();
							$scope.estadisticasByModuloDto.swFeDesde = false;
							$scope.estadisticasByModuloDto.feHasta =null;
						}
						if ($scope.estadisticasByModuloDto.swFeHasta) {
							$scope.estadisticasByModuloDto.feHasta = new Date();
							$scope.estadisticasByModuloDto.swFeHasta = false;
						}
						if (validaFechaClick()) {
							$scope.graficar();
						}
						$scope.defaultValuesCheck();
					};

					validaFechaClick = function() {
						var isGraficar = false;
						var fechaDesde = $scope.estadisticasByModuloDto.feDesde;
						var feHasta = $scope.estadisticasByModuloDto.feHasta;
						document.getElementById("chartContainer").innerHTML = '&nbsp;';

						if (null != fechaDesde && null != feHasta) {
							if (fechaDesde.getTime() > feHasta.getTime()) {
								alert('Error en fecha.');
								$scope.estadisticasByModuloDto.feHasta = null;
								$scope.estadisticasByModuloDto.feDesde = null;
								isGraficar = false;
							} else {
								isGraficar = true;
							}
						}
						return isGraficar;
					};

					$scope.graficar = function graficar() {
						/** *Buscamos atencion****Grafico******** */
							estadisticaService
							.estadisticasByModuloActividad(
									{
										nbModulo : $scope.modulo,
										nbGrupoModulo:  $scope.grupo,
										nbSubGrupoModulo:  $scope.subGrupo,
										nb_actividad:  $scope.actividad,
										nbHorario:$scope.nbHorario,
										feDesde : $scope.estadisticasByModuloDto.feDesde,
										feHasta : $scope.estadisticasByModuloDto.feHasta
									},
									function success(miembroChartDto, status) {

										chartDto = miembroChartDto.chartDto;
										
										$scope.miembroAsistenciaHorarioDto = miembroChartDto.miembroDtos;
										
										document
												.getElementById("chartContainer").innerHTML = '&nbsp;';
										document
												.getElementById("chartContainer").innerHTML = '<canvas id="myChart"></canvas>';
										var ctx = document.getElementById(
												"myChart").getContext("2d");
										/** ****GRAFICAMOS******* */
										/** Inicializamos grafico */
										myChart = {};
										/** Fin grafico */

										myChart = new Chart(ctx, chartDto);
									},
									function err(httpResponse) {
										$scope.status = status;
										$scope.aviso = 'Ha pasado algo inesperado atencionByModuloCtrl.js->estadisticaService.estadisticasByModulo';
									});
					}
					
					
					 
					
					/**********Main**********************/
					$scope.graficar();
					/**********End Main**********************/
					
					$scope.getTotal = function(miembroAsistenciaHorarioDto){
					    var total = 0;
					    for(var i = 0; i < miembroAsistenciaHorarioDto.length; i++){
					        var cant = miembroAsistenciaHorarioDto[i].asistenciaDtos.length;
					        total += cant;
					    }
					    return total;
					}
					
					
					
					/***********POP-PUP****************/
					   $scope.popup = function(items) {
							$scope.openModal('sm',"Detalles",items);
						}

					  
					  $scope.openModal = function (size,msg,arreglo) {
						
						  tipoCtrlService.msg= msg;
					    var modalInstance = $uibModal.open({
					      animation: $scope.animationsEnabled,
					      templateUrl: 'myModalContent.html',
					      controller: 'ModalInstanceCtrl',
					      size: size,
					      resolve: {
					        items: function () {
					        	
					          return arreglo;
					        }
					      }
					    });
					    modalInstance.result.then(function (selectedItem) {

					    /** Borramos todos los seleccionados */	
					  //  alert('Awesome!');
					      
					      
					    }, function () {
					      $log.info('Modal dismissed at: ' + new Date());
					    });
					  };

                    /*******END****POP-PUP****************/
					
					
					

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

						if ($scope.estadisticasByModuloDto.swFeDesde) {
							$scope.estadisticasByModuloDto.feDesde = new Date(
									year, month, day);
							$scope.estadisticasByModuloDto.swFeDesde = false;
						}
						if ($scope.estadisticasByModuloDto.swFeHasta) {
							$scope.estadisticasByModuloDto.feHasta = new Date(
									year, month, day);
							$scope.estadisticasByModuloDto.swFeHasta = false;
						}
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
					// END FECHAS

				});