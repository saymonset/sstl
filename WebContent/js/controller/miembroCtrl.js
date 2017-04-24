app
.controller("miembroCtrl", function($scope, $http, $resource, $location,
		sharedProperties, tipoCtrlService,parametroService,miembroService,horarioService,estadisticaService, $timeout,$rootScope,uibDateParser,$uibModal, $log) {
	
 
	

	var tipoCtrl=tipoCtrlService.getProperty();
    $scope.isCtrl=tipoCtrl.isCtrl;
    $scope.modulo = tipoCtrl.modulo;
	$scope.grupo = tipoCtrl.grupo;
	$scope.subGrupo = tipoCtrl.subGrupo;
	$scope.gruponbModificar = tipoCtrl.gruponbModificar;
	$scope.subGruponbModificar = tipoCtrl.subGruponbModificar;
	$scope.actividad = tipoCtrl.actividad;
	$scope.nbHorario = tipoCtrl.nbHorario;
    $scope.userInfo=sharedProperties.getProperty();  
    $scope.cedula=$scope.userInfo.cedula ;
    $scope.username=$scope.userInfo.username;
    $scope.numeroCupos=0;
    $scope.showIngHorario =false;
    $scope.miembroDto = {};
    $scope.selectionDelete = [];
    $scope.all=false;
    
    
    
    
    /** ****Bloque para eliminar********** */
    // toggle selection for a given fruit by name
    $scope.toggleSelection = function toggleSelection(valor) {
      var idx = $scope.selectionDelete.indexOf(valor);

      // is currently selected
      if (idx > -1) {
        $scope.selectionDelete.splice(idx, 1);
      }

      // is newly selected
      else {
        $scope.selectionDelete.push(valor);
      }
    };
    
    
    // Seleccionamos o deseleccionamos todos los usuarios
    $scope.selectionAll = function() {
    	$scope.selectionDelete = [];
    	if ($scope.all){
    		var log = [];
			angular.forEach($scope.miembros, function(value, key) {
				$scope.selectionDelete.push(value);
			}, log);
    	}
    	 
    };
    
 
    /** ****Fin Bloque para eliminar********** */
    
    
    $timeout(function() {
       	parametroService
		.query(
				{
					nbModulo : $scope.modulo,
					nbGrupoModulo : $scope.grupo,
					nbSubGrupoModulo : $scope.subGrupo,
					nb_actividad : $scope.actividad,
					nbHorario:$scope.nbHorario,
					txNombreParametro:'numerocupos'
				},
				function success(data, status) {
					$scope.numeroCupos=data.txValorParametro;
				},
				function err(httpResponse) {
					$scope.data = data
							|| "FALSE";
					$scope.status = status;
					$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->parametroService.query';
				});
	}, 50);
    
    
    
 
    
	$timeout(function() {

		miembroService
		.query(
				{
					
					nbModulo : $scope.modulo,
					nbGrupoModulo:$scope.grupo,
					nbSubGrupoModulo:$scope.subGrupo,
					nb_actividad:$scope.actividad,
					nbHorario:$scope.nbHorario
				},
				function success(data, status) {
					$scope.miembros=data;
				},
				function err(httpResponse) {
					$scope.status = status;
					$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.query';
				});
		
 
		
		
		
	}, 50);
	
	 
	$scope.crear = function(nbModulo, nbActividad,cedula) {
		
		miembroService
		.update(
				{
					nbModulo : $scope.modulo,
					nbGrupoModulo:$scope.grupo,
					nbSubGrupoModulo:$scope.subGrupo,
					nb_actividad:$scope.actividad,
					nbHorario:$scope.nbHorario,
					cedula:cedula
				},
				function success(miembroDto, status) {
					if (angular.isDefined(miembroDto) && angular.isDefined(miembroDto.tipoEmp)){
						$scope.isCreateActividad = false;
						$scope.miembros.push(miembroDto);
						$scope.nb_actividad = "";
						 
						$scope.aviso="Creado satisfactoriamente "+miembroDto.cedula;
					}else{
						$scope.aviso="No se pudo crear "+cedula;
					}
				},
				function err(httpResponse) {
					 
					$scope.status = status;
					$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.update';
				});
		
	}
	
	
	
	
	
	$scope.showHorario = function(nbModulo, nbActividad,cedula) {
		/** Buscamos el horario */
		$timeout(function() {
			
			miembroService
			.findHorarioSinColorByCedula(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nb_actividad:$scope.actividad,
						nbHorario:$scope.nbHorario,
						cedula:cedula
					},
					function success(data, status) {
						$scope.events = data;
						$scope.showIngHorario =true;
					},
					function err(httpResponse) {
						 
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.findHorarioSinColorByCedula';
					});
		}, 50);	
		
		/** Buscamos datos del miembro */
		$timeout(function() {
			miembroService
			.findByCedula(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nb_actividad:$scope.actividad,
						nbHorario:$scope.nbHorario,
						cedula:cedula
					},
					function success(data, status) {
						$scope.miembroDto = data;					},
					function err(httpResponse) {
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.findByCedula';
					});
		}, 50);	

		
		
 
		
		
	}
	
	$scope.updateMiembroGoLista= function(miembroDto) {
		miembroDto.fechaStr=miembroDto.feRenovacion;
		if (miembroDto!=null &&  miembroDto.feRenovacion==null && miembroDto.renovable){
			$scope.open('sm',"Falta la Fecha Renovar");
		}else{

			miembroService
			.updateMiembro(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nb_actividad:$scope.actividad,
						nbHorario:$scope.nbHorario,
						cedula:miembroDto.cedula,
						nombre:miembroDto.nombre,
						tipoEmp:miembroDto.tipoEmp,
						renovable:miembroDto.renovable,
						profesor:miembroDto.profesor,
						feRenovacion:miembroDto.feRenovacion
					}	,
					function success(miembroDto, status) {
						
						if (miembroDto){
						// Modificamos lo que esta en java script, solo el
						// elemento que se altero
				   		for (var i = 0; i < $scope.miembros.length; i++) {
				            if ($scope.miembros[i].cedula == miembroDto.cedula && $scope.miembros[i].nbModulo== miembroDto.nbModulo 
				            		&& $scope.miembros[i].nbGrupo== miembroDto.nbGrupo
				            		&& $scope.miembros[i].nbSubGrupo== miembroDto.nbSubGrupo
				            		&& $scope.miembros[i].nbHorario== miembroDto.nbHorario
				            		&& $scope.miembros[i].nbActividad== miembroDto.nbActividad) {
				                $scope.miembros[i] = miembroDto;
				                break;
				            }
				        }
				   		
						
						
						$scope.aviso="Modificado satisfactoriamente "+miembroDto.cedula;
						$scope.pagContador=1;
						$scope.cedula="";
					}else{
						$scope.aviso="No se pudo crear "+cedula;
					}
						
						 				},
					function err(httpResponse) {
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.updateMiembro';
					});
			

			
			$scope.showIngHorario =false;
			$scope.searchCedula="";	
		}
	}
	
	

 
	
	
	
	
	
	
	
	$scope.cancelar= function() {
		$scope.pagContador=1;
		$scope.cedula="";
		$scope.showIngHorario =false;
		$scope.searchCedula="";	
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
	
	
	
	 $scope.llenaHorario = function(pagContador) {
	    	if((pagContador==3) && angular.isDefined($scope.checkboxModel)  && angular.isDefined($scope.checkboxModel.isConfigHorario)
		            && $scope.checkboxModel.isConfigHorario==true && $scope.horarioSeleccionado.length > 0 ){
				$timeout(function() {
					$scope.events = $scope.horarioSeleccionado;
					
				}, 50);		
				}else{
					$timeout(function() {
						
						horarioService
						.findByModuloActividadMoveDisabled(
								{
									nbModulo : $scope.modulo,
									nbGrupoModulo:$scope.grupo,
									nbSubGrupoModulo:$scope.subGrupo,
									nb_actividad:$scope.actividad,
									nbHorario:$scope.nbHorario
								},
								function success(data, status) {
									$scope.events = data;
									},
								function err(httpResponse) {
									$scope.status = status;
									$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->horarioService.findByModuloActividadMoveDisabled';
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
			    customClass: getDayClass,
			    minDate: new Date(),
			    showWeeks: true
			  };

			  // Disable weekend selection
			  function disabled(data) {
			    var date = data.date,
			      mode = data.mode;
			    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
			  }

			  $scope.toggleMin = function() {
			    $scope.options.minDate = $scope.options.minDate ? null : new Date();
			  };

			  $scope.toggleMin();

			  $scope.setDate = function(year, month, day) {
			    $miembroDto.feRenovacion = new Date(year, month, day);
			  };

			  var tomorrow = new Date();
			  tomorrow.setDate(tomorrow.getDate() + 1);
			  var afterTomorrow = new Date(tomorrow);
			  afterTomorrow.setDate(tomorrow.getDate() + 1);
			  $scope.events = [
			    {
			      date: tomorrow,
			      status: 'full'
			    },
			    {
			      date: afterTomorrow,
			      status: 'partially'
			    }
			  ];

			  function getDayClass(data) {
			    var date = data.date,
			      mode = data.mode;
			    if (mode === 'day') {
			      var dayToCheck = new Date(date).setHours(0,0,0,0);

			      for (var i = 0; i < $scope.events.length; i++) {
			        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

			        if (dayToCheck === currentDay) {
			          return $scope.events[i].status;
			        }
			      }
			    }

			    return '';
			  }
// END FECHAS

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
		
		$scope.procesarEvent=function(args){
			
	    	$scope.text=args.e.text();
	    	$scope.id=args.e.id();
	    	$scope.start = args.e.start().toString();
			$scope.end = args.e.end().toString();

			var obj ={};
			    obj.text=$scope.text;
			    obj.id=$scope.id;
			    obj.start=$scope.start;
			    obj.end=$scope.end;
			    obj.backColor= "#fff099";
			    obj.moveDisabled =true;
			    ingresarDiaEnHorario($scope.events, obj.id,obj);
	    		
		}
		
		function ingresarDiaEnHorario(source, id,obj) {
			  for (var i = 0; i < source.length; i++) {
			    if (source[i].id === id) {
			      return source[i];
			    }
			  }
			  
			  if(angular.isDefined($scope.pagContador)  &&($scope.pagContador==2) && angular.isDefined($scope.checkboxModel)  && angular.isDefined($scope.checkboxModel.isConfigHorario)
			            && $scope.checkboxModel.isConfigHorario==true  ){
				 
				  $scope.horarioSeleccionado.push(obj);  
			  }
			  
			  
			  
			}
		
		
		$scope.deleteBloque = function(item) {
			$scope.horarioSeleccionado.splice($scope.horarioSeleccionado.indexOf(item), 1);
		}
				$scope.name = "me";
	
	

				
				
				
    // --------------------------------------------------------------------------------------------------------------
	// ***************************************MODAL
	// VENTANA***********************************************************
    // --------------------------------------------------------------------------------------------------------------
				$scope.items  = $scope.selectionDelete;;// ['item1', 'item2',
														// 'item3'];
				  
				    
				  $scope.animationsEnabled = true;

				  $scope.open = function (size,msg) {
					  
					  
					  
					  
					  tipoCtrlService.msg= msg;
				    var modalInstance = $uibModal.open({
				      animation: $scope.animationsEnabled,
				      templateUrl: 'myModalContent.html',
				      controller: 'ModalInstanceCtrl',
				      size: size,
				      resolve: {
				        items: function () {
				          return $scope.items;
				        }
				      }
				    });
				    modalInstance.result.then(function (selectedItem) {
				      $scope.selected = selectedItem;
				    }, function () {
				      $log.info('Modal dismissed at: ' + new Date());
				    });
				  };


				  
				   $scope.eliminar = function(selectionDelete) {
						$scope.openDelete('sm',"Seguro desea eliminar estos registros ?",selectionDelete);
					}

				  
				  $scope.openDelete = function (size,msg,selectionDelete) {
					
					  tipoCtrlService.msg= msg;
				    var modalInstance = $uibModal.open({
				      animation: $scope.animationsEnabled,
				      templateUrl: 'myModalContentDelete.html',
				      controller: 'ModalInstanceCtrl',
				      size: size,
				      resolve: {
				        items: function () {
				        	
				          return selectionDelete;
				        }
				      }
				    });
				    modalInstance.result.then(function (selectedItem) {

				    /** Borramos todos los seleccionados */	
				      $scope.deleteAllSelect();
				      
				      
				    }, function () {
				      $log.info('Modal dismissed at: ' + new Date());
				    });
				  };
				  
				  $scope.deleteAllSelect = function() {
						$http.put("services/miembroServiceResource/deletesMiembros", angular.toJson($scope.selectionDelete)).success(
								function(selectionDelete) {
									  var log = [];
										angular.forEach(selectionDelete, function(value, key) {
											var existe = true;
											angular.forEach($scope.miembros, function(value1, key1){
												  if(existe) {
												    if(value.cedula == value1.cedula){
												      $scope.miembros.splice($scope.miembros.indexOf(value1), 1);
												      $scope.selectionDelete.splice($scope.selectionDelete.indexOf(value), 1);
												      existe = false;
												    }
												  }
												});
										}, log);
								      
									$scope.aviso="Eliminado satisfactoriamente ";
									 
								});
					}

	
					$scope.deleteM = function(nbModulo, nbActividad,cedula) {
						 if (confirm("�Seguro desea eliminar  ?")) {	 
							 miembroService
								.deletex(
										{
											nbModulo : $scope.modulo,
											nbGrupoModulo:$scope.grupo,
											nbSubGrupoModulo:$scope.subGrupo,
											nb_actividad:$scope.actividad,
											nbHorario:$scope.nbHorario,
											cedula:cedula
										},
										function success(miembroDto, status) {
											var log = [];
											angular.forEach($scope.miembros, function(value, key) {
												if (value.nbActividad===nbActividad && value.nbModulo===nbModulo && value.cedula===cedula){
													$scope.miembros.splice($scope.miembros.indexOf(value), 1);
													$scope.selectionDelete.splice($scope.selectionDelete.indexOf(value), 1);
												}
											
											}, log);
											$scope.nbActividadModif="";
											$scope.aviso="Eliminado satisfactoriamente "+cedula  ;
											 
										},
										function err(httpResponse) {
											$scope.aviso = 'Ha pasado algo inesperado miembroCtrl.js->miembroService.deletex';
										});
							 
 
						 }
						 
						 
						 
					}

				  
				  
				  
				  $scope.toggleAnimation = function () {
				    $scope.animationsEnabled = !$scope.animationsEnabled;
				  };			
	 
	
	
});