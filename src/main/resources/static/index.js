

angular.module('app', []).controller('indexController', function($scope, $http){

    const contextPath = 'http://localhost:8080/app/api/v1/products';

    console.log(123);

    $scope.loadProducts = function(pageIndex=1){

    $http ({
        method:'GET',
        url: contextPath,
        params: {
            title_part: $scope.filter ? $scope.filter.title_part: null,
            min_cost: $scope.filter ? $scope.filter.min_cost: null,
            max_cost: $scope.filter ? $scope.filter.max_cost: null,
            p : page
        }
    })  .then(function (response) {
            $scope.ProductsPage = response.data;       /*сюда грузить всю страницу*/
    });

    };

/*

    $scope.deleteProduct = function(productId){
        $http.delete(contextPath + productId)
        .then(function (response){
            $scope.loadProducts();
            console.log(response.data)

        });

    }



     $scope.findProduct = function(productId){
        $http.get(contextPath+productId)
            .then(function (response){
            $scope.ProductsList = response.data
            });

     }

     $scope.findProductByTitle = function(productTitle){
        $http.get(contextPath+productTitle)
            .then(function(response){
            $scope.productId = response.data

            });
     }
*/



    $scope.loadProducts();


});