angular.module('app',['ngStorage']).controller('indexController',function($scope, $http, $location, $localStorage) {

const contextPath = 'http://localhost:8189/app';





$scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: page,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;

            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

            console.log("PAGE FROM BACKEND")
            console.log($scope.productsPage);
        });
    };
$scope.addNewProduct = function () {
          $http.post(contextPath + '/api/v1/products', $scope.newProduct)
           .then(function successCallback(response) {
                           $scope.loadPPage();
                           $scope.newProduct = null;
                       }, function errorCallback(response) {
                           console.log(response.data);
                           alert('Error: ' + response.data.messages);
                       });
               }
   $scope.deleteProduct = function(id){
   $http({
            url: contextPath + '/api/v1/products',
                            method: 'Delete',
                            params: {
                               id : id
                               }
   }).then (function successCallback(response){
            $scope.loadPage();
         }), (function errorCallback(response){
         console.log(response.data);
         alert('Error'+ response.data.messages)})
         };



    $scope.addProductToCart = function(id){
    $http({
                url: contextPath + '/api/v1/carts/add',
                method: 'Get',
                params: {
                   id : id
                }
    }).then (function successCallback(response){
        console.log(response.data);
       $scope.loadCart();
    }), (function errorCallback(response){
    console.log(response.data);
    alert('Error'+ response.data)})
    };

   $scope.deleteProductFromCart = function(orderItemsId){
      $http({
               url: contextPath + '/api/v1/carts/deleteById',
                               method: 'Get',
                               params: {
                                  id : orderItemsId
                               }
      }).then (function successCallback(response){
               $scope.loadCart();
            }), (function errorCallback(response){
            console.log(response.data);
            alert('Error'+ response.data.messages)})
            };


   $scope.loadCart = function(){
   $http.get(contextPath + "/api/v1/carts")
   .then(function(response){
   $scope.cart = response.data;
   console.log(response.data);
   });
   }

  $scope.clearCart = function(){
  $http.get(contextPath + "/api/v1/carts/clear")
  .then (function successCallback(response){
                 $scope.loadCart();
              }), (function errorCallback(response){
              console.log(response.data);
              alert('Error'+ response.data.messages)})
              };

$scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

  $scope.tryToAuth = function () {
         $http.post(contextPath + '/auth', $scope.user)
             .then(function successCallback(response) {
                 if (response.data.token) {
                     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                     $localStorage.myMarketCurrentUser = {username: $scope.user.username, token: response.data.token};

                     $scope.user.username = null;
                     $scope.user.password = null;
                 }
             }, function errorCallback(response) {
             });
     };

     $scope.tryToLogout = function () {
         $scope.clearUser();
     };

     $scope.clearUser = function () {
         delete $localStorage.myMarketCurrentUser;
         $http.defaults.headers.common.Authorization = '';
     };

     $scope.isUserLoggedIn = function () {
         if ($localStorage.myMarketCurrentUser) {
             return true;
         } else {
             return false;
         }
     };


    if ($localStorage.myMarketCurrentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myMarketCurrentUser.token;
        }

    $scope.loadPage(1);
    $scope.loadCart();


 });

