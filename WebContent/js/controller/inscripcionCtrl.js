app
.controller("inscripcionCtrl", function($scope, $http, $resource, $location,
		sharedProperties, tipoCtrlService,miembroService,horarioService, $timeout,$rootScope,parametroService) {
	var tipoCtrl=tipoCtrlService.getProperty();
    $scope.isCtrl=tipoCtrl.isCtrl;
    $scope.modulo = tipoCtrl.modulo;
	$scope.grupo = tipoCtrl.grupo;
	$scope.subGrupo = tipoCtrl.subGrupo;
	$scope.gruponbModificar = tipoCtrl.gruponbModificar;
	$scope.subGruponbModificar = tipoCtrl.subGruponbModificar;
	$scope.actividad = tipoCtrl.actividad;
	$scope.nbHorario = tipoCtrl.nbHorario;
	$scope.personalizarHorario = tipoCtrl.personalizarHorario;
	
	  $scope.userInfo=sharedProperties.getProperty();  

      $scope.cedula="";
	/**Si no es definido, lo hace el administrador..
	 * Si es definido , lo hace un acceso directo y vemos que trae (true, false)
	 * */
	if (!angular.isDefined($scope.personalizarHorario)){
		$scope.personalizarHorario = true;
	}

	/**Si no es definido, lo hace el administrador..
	 * Si es definido , lo hace un acceso directo  trae (true)
	 * */
	$scope.accesoDirecto = false;
	$scope.accesoDirecto = tipoCtrl.accesoDirecto;
	if (!angular.isDefined($scope.accesoDirecto)){
		$scope.accesoDirecto = false;		
	}else{
		 $scope.cedula = $scope.userInfo.cedula;
	}
	
	
	
  
    
   
    
    $scope.previous=false;
    $scope.username=$scope.userInfo.username;
    $scope.horarioSeleccionado=[];
    $scope.miembros=[];
    $scope.pagContador=1;
    $scope.miembroDtoInscripcion={};
    $scope.numeroCupos=0;
    $scope.usuarioRegistrado=false;
    $scope.nombre=null;
    $scope.tipoEmp=null;
  
    
    
    
	
	 
	
    
  
	
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
					
					$scope.status = status;
					$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->parametroService.query';
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
					$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->miembroService.query';
				});
		
	}, 50);
 
	
	
	
	
	//validando cedula empleado
	$scope.cedulaEmpl = function() {
		miembroService
				.findUserFromPersonalTdoEmpleados(
						{
							cedula : $scope.cedula
						},
						function success(data, status) {
							$scope.nombre=data.nombre;
							$scope.tipoEmp=data.tipoEmp;
						},
						function err(httpResponse) {
							$scope.status = status;
							//$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->parametroNominaService.findUser';
						});
	}

	
	 $scope.fechaRenovacion = function(modulo,actividad) {
			miembroService
			.updateFechaRenovacion(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nb_actividad:$scope.actividad,
						nbHorario:$scope.nbHorario
					},
					function success(data, status) {
						$scope.miembroDtoInscripcion=data;
					},
					function err(httpResponse) {
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->miembroService.updateFechaRenovacion';
					});
		 
	 

		}
	 
	 $scope.saveInscripcion = function(nbModulo,grupo,subGrupo,nbActividad,nbHorario,cedula,renovable,profesor,fechaStr){
		 miembroDto = {};
		 miembroDto.nombre=$scope.nombre;
		 miembroDto.tipoEmp=$scope.tipoEmp;
		 miembroDto.nbModulo=nbModulo;
		 miembroDto.nbGrupo = grupo;
		 miembroDto.nbSubGrupo = subGrupo;
		 miembroDto.nbActividad=nbActividad;
		 miembroDto.nbHorario=nbHorario;
		 miembroDto.cedula=cedula;
		 miembroDto.fechaStr=fechaStr;
		 miembroDto.renovable=renovable;
		 miembroDto.profesor=profesor;
		 miembroDto.horarioDtos=$scope.events;
		 
		 /**
		  * 	private String nbModulo;
	private String nbActividad;
	private String ;
	private String ;
	private String ;
	private String cedula;
		  * **/
		 
		 
		 
			$http.put("services/miembroServiceResource/saveInscripcion", angular.toJson(miembroDto)).success(
					function(miembroDto) {
						if (miembroDto){
							$scope.horarioSeleccionado=[];
							
							$scope.aviso="Creado satisfactoriamente "+miembroDto.cedula;
							$scope.pagContador=1;
							$scope.cedula="";
							$scope.miembros.push(miembroDto);
						}else{
							$scope.aviso="No se pudo crear "+cedula;
						}
					});	 
		  
		}
	 
	 
	 
		
 
	
	
	
	$scope.deleteM = function(nbModulo, nbActividad,cedula) {
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
							if (value.nbActividad===nbActividad && value.nbModulo===$scope.nbModulo 
									&& value.nbGrupoModulo===$scope.grupo
									&& value.nbSubGrupoModulo===$scope.subGrupo
									&& value.nbActividad===$scope.actividad
									&& value.nbHorario===$scope.nbHorario
									&& value.cedula===cedula){
								$scope.miembros.splice($scope.miembros.indexOf(value), 1);
							}
						
						}, log);
						$scope.nbActividadModif="";
						$scope.aviso="Eliminado satisfactoriamente "+miembroDto.nbActividad  ;
					},
					function err(httpResponse) {
						$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->miembroService.deletex';
					});
		 
		 
		 
		 
	}
	
	
	$scope.goPagContadorNext=function(pagContador,cedula){
		$scope.aviso="";
		$scope.isAvisoConfHorario=false;
		if((pagContador==2) && angular.isDefined($scope.checkboxModel)  && angular.isDefined($scope.checkboxModel.isConfigHorario)
				            && $scope.checkboxModel.isConfigHorario==true && $scope.horarioSeleccionado.length == 0 ){
			$scope.isAvisoConfHorario=true;
		}else{
			/**Si estamos en la primera pagina, chequearemos si el usuario no existe en la tabla de miembro_horario*/
			$scope.miembroDto={};
			
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
						$scope.miembroDto=data;
						if (angular.isDefined($scope.miembroDto) && angular.isDefined($scope.miembroDto.cedula) && ($scope.miembroDto.cedula!=null)){
							/**El usuario se encuentra registrado*/
							$scope.usuarioRegistrado=true;
							$scope.pagContador=1;
						}else{
							$scope.pagContador=pagContador+1;
							$scope.llenaHorario($scope.pagContador);				
						}
					
					},
					function err(httpResponse) {
						 
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->miembroService.findByCedula';
					});
		}
			  
	}
	
	$scope.goPagContadorBack=function(pagContador){
		$scope.isAvisoConfHorario=false;
		if ((pagContador-1)>=1){
			$scope.pagContador=pagContador-1;	
		}
		$scope.llenaHorario($scope.pagContador);	
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
									$scope.aviso = 'Ha pasado algo inesperado inscripcionCtrl.js->horarioService.findByModuloActividadMoveDisabled';
								});
					}, 50);			
				}
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
	
 
//tratamiento del horario 
	
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
		    ingresarDiaEnHorario($scope.horarioSeleccionado, obj.id,obj);
    		
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
	 
	
	
});