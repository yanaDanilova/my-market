angular.module('app',[]).controller('indexController',function($scope,$http) {

const contextPath = 'http://localhost:8189/app';


$scope.loadProducts = function(pageIndex = 1){
 $http({
            url: contextPath + '/api/v1/products',
            method: 'Get',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null

            }
        }).then(function (response) {
            $scope.products = response.data.content;
        });}

   $scope.addNewProduct = function () {
          $http.post(contextPath + '/api/v1/products', $scope.newProduct)
              .then(function successCallback(response) {
                  $scope.loadProducts();
                  $scope.newProduct = null;
              }, function errorCallback(response) {
                  console.log(response.data);
                  alert('Error: ' + response.data.messages);
              });
      };


$scope.deleteProduct = function(){

}

$scope.loadProducts();

});