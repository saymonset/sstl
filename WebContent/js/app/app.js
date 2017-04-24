var app = angular.module("app", [ "ngResource", "ngRoute", "daypilot",
		"ui.bootstrap", "ngCookies", "ngIdle" ]);
// realizamos la configuraci�n del enrutado dependiendo de la url
// con el objeto $routeProvider haciendo uso de when
// en este caso, cuando estemos en la p�gina principal, le decimos que
// cargue el archivo templates/index.html y que haga uso del controlador
// indexController, as� en todos los casos
app.config(function($routeProvider,IdleProvider, KeepaliveProvider) {
	
	 IdleProvider.idle(10 * 60); // 10 minutes idle
	  IdleProvider.timeout(30); // 30 second warning
	  KeepaliveProvider.interval(5 * 60); // 5 minute keep-alive ping
	  
	   
	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller : "loginCtrl"
	}).when("/accesosDirectos", {
		templateUrl : "accesosDirectos.html",
		controller : "accesosDirectosCtrl"
	}).when("/dashBoard", {
		templateUrl : "dashBoard.html",
		controller : "dashBoardCtrl"
	}).when("/moduloAnio", {
		templateUrl : "moduloAnio.html",
		controller : "moduloAnioCtrl"
	}).when("/moduloAnioMes", {
		templateUrl : "moduloAnioMes.html",
		controller : "moduloAnioMesCtrl"
	}).when("/moduloAnioMesActividad", {
		templateUrl : "moduloAnioMesActividad.html",
		controller : "moduloAnioMesActividadCtrl"
	}).when("/horarioNombre", {
		templateUrl : "horarioNombre.html",
		controller : "horarioNombreCtrl"
	}).when("/actividad", {
		templateUrl : "actividad.html",
		controller : "actividadCtrl"
	}).when("/horario", {
		templateUrl : "horario.html",
		controller : "horarioCtrl"
	}).when("/miembro", {
		templateUrl : "miembro.html",
		controller : "miembroCtrl"
	}).when("/atencion", {
		templateUrl : "atencion.html",
		controller : "atencionCtrl"
	}).when("/estadistica", {
		templateUrl : "estadistica.html",
		controller : "estadisticaCtrl"
	}).when("/inscripcion", {
		templateUrl : "inscripcion.html",
		controller : "inscripcionCtrl"
	}).when("/configuracion", {
		templateUrl : "configuracion.html",
		controller : "configuracionCtrl"
	}).when("/atencionByModulo", {
		templateUrl : "atencionByModulo.html",
		controller : "atencionByModuloCtrl"
	}).when("/inscripcionByModulo", {
		templateUrl : "inscripcionByModulo.html",
		controller : "inscripcionByModuloCtrl"
	}).when("/asistenciaByModulo", {
		templateUrl : "asistenciaByModulo.html",
		controller : "asistenciaByModuloCtrl"
	})

	// este es digamos, al igual que en un switch el default, en caso que
	// no hayamos concretado que nos redirija a la p�gina principal
	.otherwise({
		reditrectTo : "/"
	});
} ).run(function($rootScope, $location, sharedProperties, Idle,$uibModal) {

	salir = function() {
		var path = $location.path();
	 
			userInfo = sharedProperties.getProperty();
			userInfo={};
			/****En sharedProperties , lleva la info del usuario.. si es admin, la propioedad admin = true***********/
			sharedProperties.setProperty(null);

			$location.path('/');
		 
	}
  
sessionTimeout = function(){
	// start watching when the app runs. also starts the Keepalive service by default.
	    Idle.watch();
	    $rootScope.$on('IdleStart', function() {
			//alert('Modal'); /* Display modal warning or sth */
			//salir();
			
		});

		$rootScope.$on('IdleTimeout', function() { /* Logout user */
		
			salir();
		});
 } 

/***Controlaremos la session***/
sessionTimeout();
	
	

/*******************/

 $rootScope.events = [];

    $rootScope.$on('IdleStart', function() {
    	  closeModals();

          $rootScope.warning = $uibModal.open({
        	     templateUrl: 'warning-dialog.html',
            windowClass: 'modal-danger'
          });
        // the user appears to have gone idle
    });

    $rootScope.$on('IdleWarn', function(e, countdown) {
    //	alert('2');
        // follows after the IdleStart event, but includes a countdown until the user is considered timed out
        // the countdown arg is the number of seconds remaining until then.
        // you can change the title or display a warning dialog from here.
        // you can let them resume their session by calling Idle.watch()
    });

    $rootScope.$on('IdleTimeout', function() {
    	 closeModals();
         $rootScope.timedout = $uibModal.open({
        	    templateUrl: 'timedout-dialog.html',
           windowClass: 'modal-danger'
         });
         salir();
        // the user has timed out (meaning idleDuration + timeout has passed without any activity)
        // this is where you'd log them
    });

    $rootScope.$on('IdleEnd', function() {
    	 closeModals();
        // the user has come back from AFK and is doing stuff. if you are warning them, you can use this to hide the dialog
    });

    $rootScope.$on('Keepalive', function() {
    	
        // do something to keep the user's session alive
    });

    
    function closeModals() {
        if ($rootScope.warning) {
          $rootScope.warning.close();
          $rootScope.warning = null;
        }

        if ($rootScope.timedout) {
          $rootScope.timedout.close();
          $rootScope.timedout = null;
        }
      }

/*******************/

	$rootScope.$on("$routeChangeStart", function(event, next, current) {
		
		
		
		
		userInfo = sharedProperties.getProperty();
		if (!userInfo) {
			// no logged user, redirect to /login
			if (next.templateUrl === "/login.html") {
			} else {
				$location.path("/");
			}
		}
	});
});
