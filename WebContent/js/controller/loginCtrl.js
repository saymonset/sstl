app.controller(
		"loginCtrl",
		function($scope, $http, $resource, $location,  ZipCodes,
				sharedProperties, tipoCtrlService, loginService,Idle) {

			$scope.$on("zipCodeUpdated", function(event, args) {
				$scope[args.type] = args.zipCode;
			});
			var tipoCtrl = {
				isCtrl : "loginCtrl"
			};
			tipoCtrlService.setProperty(tipoCtrl);
			ZipCodes.setZipCode('isCtrl', 'loginCtrl');

			$scope.loadData = function(username) {
				
				loginService.userInfo({
					login : username.login,
					password : username.password
				}, function success(data, status) {
					$scope.userInfo = data;
					$scope.userInfo = data;
					if (angular.isDefined($scope.userInfo.login)
							&& $scope.userInfo.login != null) {
						
 
						$scope.userInfo=$scope.userInfo;
						

						ZipCodes.setZipCode('username',
								$scope.userInfo.username);
						ZipCodes.setZipCode('cedula', $scope.userInfo.cedula);
						ZipCodes.setZipCode('userInfo', $scope.userInfo);

						/****En sharedProperties , lleva la info del usuario.. si es admin, la propioedad admin = true***********/
						sharedProperties.setProperty(data);
						// start watching when the app runs. also starts the Keepalive service by default.
						/**Activamos el time de session */
					    Idle.watch();
						var path = $location.path();
						$location.path('/accesosDirectos');

						
					} else {
						$scope.aviso = 'Usuario no encontrado';
					}
				}, function err(httpResponse) {

					$scope.status = status;
					$scope.aviso = 'Ha pasado algo inesperado';
				});
			}
		});