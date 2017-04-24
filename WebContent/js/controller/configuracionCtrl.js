app
.controller("configuracionCtrl", function($scope, $http, $resource, $location,
		sharedProperties, tipoCtrlService, $timeout,$rootScope,configuracionService) {
	
 
	

	var tipoCtrl=tipoCtrlService.getProperty();
    $scope.isCtrl=tipoCtrl.isCtrl;
    $scope.modulo = tipoCtrl.modulo;
	$scope.grupo = tipoCtrl.grupo;
	$scope.subGrupo = tipoCtrl.subGrupo;
	$scope.gruponbModificar = tipoCtrl.gruponbModificar;
	$scope.subGruponbModificar = tipoCtrl.subGruponbModificar;
	$scope.actividad = tipoCtrl.actividad;
	$scope.nbHorario = tipoCtrl.nbHorario;
	$scope.booleans=[{valor:'SI'},{valor:'NO'}];
	  $scope.personalizar_horario=false;
	   $scope.verPersonalizar_horario=false;
    
    $scope.numeroCupos=tipoCtrl.numeroCupos;
    $scope.userInfo=sharedProperties.getProperty();  
    $scope.cedula=$scope.userInfo.cedula ;
    $scope.username=$scope.userInfo.username;
    $scope.isModificar=false;
    
    
    /***
	  * Para cada parametro del modulo y actviidad.., chequeamos que esten por defectos las variabes de los paramtros 
	  * ******************/
	 $timeout(function() {
 
		 
		 configuracionService
			.query(
					{
						nbModulo : $scope.modulo,
						nbGrupoModulo:$scope.grupo,
						nbSubGrupoModulo:$scope.subGrupo,
						nbActividad:$scope.actividad,
						nbHorario:$scope.nbHorario
					},
					function success(data, status) {
						$scope.objetos=data;
					},
					function err(httpResponse) {
						 
						$scope.status = status;
						$scope.aviso = 'Ha pasado algo inesperado configuracionCtrl.js->configuracionService.query';
					});
		 
		}, 50);
	
	 
	$scope.update = function(nbModulo, nbActividad,txNombreParametro,txValorParametro,txObservaciones,tipo) {
 		configuracionService
		.update(
				{
					nbModulo : nbModulo,
					nbGrupoModulo : $scope.grupo,
					nbSubGrupoModulo : $scope.subGrupo,
					nbActividad:nbActividad,
					nbHorario : $scope.nbHorario,
					txNombreParametro:txNombreParametro,
					txValorParametro:txValorParametro,
					txObservaciones:txObservaciones,
					tipoParametro:tipo,
					personalizar_horario:$scope.personalizar_horario
				},
				function success(data, status) {
					if (angular.isDefined(data) && angular.isDefined(data.txNombreParametro)){
						for (var i = 0; i < $scope.objetos.length; i++) {
							console.log('$scope.objetos[i].txNombreParametro='+$scope.objetos[i].txNombreParametro);
							console.log('data.txNombreParametro='+data.txNombreParametro);
							if ($scope.objetos[i].txNombreParametro==data.txNombreParametro ){
								console.log('Encontrado');
								$scope.objetos[i]=data;
								 break;
							}
				        }
						   $scope.txNombreParametro="";
				           $scope.txValorParametro="";
				           $scope.txObservaciones="";
				           $scope.tipo="";
				           $scope.isModificar=false;
						$scope.aviso="Actualizado satisfactoriamente "+data.txNombreParametro;
					}else{
						$scope.aviso="No se pudo crear ";
					}
				},
				function err(httpResponse) {
					$scope.aviso = 'Ha pasado algo inesperado configuracionCtrl.js->configuracionService.update';
				});
		
     	   $scope.isFecha=false;
     	   $scope.isBoolean=false;
     	   $scope.personalizar_horario=false;
     	   $scope.verPersonalizar_horario=false;
     	   $scope.isModificar=false;		
 
	}
	
	
	$scope.modificar = function(nbModulo, nbActividad,txNombreParametro,txValorParametro,txObservaciones,tipo,personalizarHorario) {
           $scope.txNombreParametro=txNombreParametro;
           $scope.txValorParametro=txValorParametro;
           $scope.txObservaciones=txObservaciones;
           $scope.personalizar_horario = personalizarHorario
           $scope.tipo=tipo;
           if (tipo=='f'){
        	   $scope.isFecha=true;
           }
           if (tipo=='b'){
        	   $scope.isBoolean=true;
           }
           
           if ('accesoDirectoInscripcion'===$scope.txNombreParametro){
        	   $scope.verPersonalizar_horario=true;
        	   
        	   $scope.txObservaciones=$scope.actividad + '/' + $scope.nbHorario; 
        	   
           }
           
           $scope.isModificar=true;
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

	
 ////*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	///FECHAS
	 $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function() {
		    $scope.dt = null;
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
		    $scope.dt = new Date(year, month, day);
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
	
	 
	
	
});