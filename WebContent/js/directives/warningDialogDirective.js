'use strict';
app.directive("warningDialog", function () {
return {
	
link: function (scope, element, attrs) {
scope.nada = {}
alert('gg');
},
restrict: "A",
templateUrl: "../../warning-dialog.html"
}
})