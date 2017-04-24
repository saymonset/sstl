//desde aqu� hacemos uso del objeto app que es nuestro modulo
//y de esta forma creamos un controlador, en este caso el controlador indexController,
//como vemos, hacemos uso de scope y sencillamente creamos un array y colocamos 
//dos objetos dentro de �l
app.controller("indexController", function indexController($scope){
 $scope.usuarios = [
 {
 nombre : "Israel Parra",
 web : "http://uno-de-piera.com",
 edad : "32 a�os",
 profesion : "programador web"
 },
 {
 nombre : "Pepito",
 web : "http://pepito.com",
 edad : "? a�os",
 profesion : "vender palotes!"
 }
 ]
})
 
//m�s de lo mismo, pero en este caso creamos una variable llamada saludo y una funci�n
//que gracias al objeto location y al m�todo url nos redirigir� al login al hacer uso de ella
app.controller("homeController", function homeController($scope, $location){
 $scope.saludo = "Hola desde el controlador home";
 $scope.toLogin = function(){
 $location.url("/login");
 }
});
 
app.controller("loginController", function loginController($scope, $location){
 $scope.saludo = "Hola desde el controlador login";
 $scope.toHome = function(){
 $location.url("/home");
 }
})