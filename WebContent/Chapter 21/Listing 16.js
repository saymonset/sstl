angular.module("exampleApp", ["increment", "ngResource"])
.constant("baseUrl", "http://localhost:8080/sstl/services/ldapServicio/")
.controller("defaultCtrl", function ($scope, $http, $resource, baseUrl) {

    $scope.displayMode = "list";
    $scope.currentProduct = null;

    $scope.productsResource = $resource(baseUrl + ":id", { id: "@id" },
        { create: { method: "POST" }, save: { method: "PUT" }});

    $scope.listProducts = function () {
        $scope.products = $resource(baseUrl + "products/" + ":id", { id: "@id" }).query();
    }

    $scope.deleteProduct = function (product) {
        product.$delete().then(function () {
            $scope.products.splice($scope.products.indexOf(product), 1);
        });
        $scope.displayMode = "list";
    }

    $scope.createProduct = function (product) {
    	alert('create');
        new $scope.productsResource(product).$create().then(function (newProduct) {
            $scope.products.push(newProduct);
            $scope.displayMode = "list";
        });
    }


    $scope.updateProduct = function (product) {
    	alert('save');
        product.$save();
        $scope.displayMode = "list";
    }

    $scope.editOrCreateProduct = function (product) {
        $scope.currentProduct = product ? product : {};
        $scope.displayMode = "edit";
    }

    $scope.saveEdit = function (product) {
    	alert('one');
        if (angular.isDefined(product.id)) {
            $scope.updateProduct(product);
        } else {
            $scope.createProduct(product);
        }
    }

    $scope.cancelEdit = function () {
        if ($scope.currentProduct && $scope.currentProduct.$get) {
            $scope.currentProduct.$get();
        }
        $scope.currentProduct = {};
        $scope.displayMode = "list";
    }

    $scope.listProducts();
});
