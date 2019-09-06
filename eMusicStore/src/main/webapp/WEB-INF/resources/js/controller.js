$('.spinner .btn:first-of-type').on('click', function() {
      var btn = $(this);
      var input = btn.closest('.spinner').find('input');
      if (input.attr('max') == undefined || parseInt(input.val()) < parseInt(input.attr('max'))) {    
        input.val(parseInt(input.val(), 10) + 1);
      } else {
        btn.next("disabled", true);
      }
    });
    $('.spinner .btn:last-of-type').on('click', function() {
      var btn = $(this);
      var input = btn.closest('.spinner').find('input');
      if (input.attr('min') == undefined || parseInt(input.val()) > parseInt(input.attr('min'))) {    
        input.val(parseInt(input.val(), 10) - 1);
      } else {
        btn.prev("disabled", true);
      }
    });




var cartApp = angular.module ("cartApp", []);

cartApp.controller("cartCtrl", function ($scope, $http){

    $scope.refreshCart = function () {
        $http.get('/emusicstore/rest/cart/'+$scope.cartId).success(function (data) {
           $scope.cart=data;
        });
    };

    $scope.clearCart = function () {
        $http.delete('/emusicstore/rest/cart/'+$scope.cartId).success($scope.refreshCart());
    };

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };
    
    $scope.addToCart = function (productId) {
        $http.put('/emusicstore/rest/cart/processing/'+productId+'/'+parseInt($("#quantity").val())).success(function (data) {
            alert("Your Order is being proccessing Now, Please check My orders Tab for further information!")
        });
    	
    };

    $scope.removeFromCart = function (productId) {
        $http.put('/emusicstore/rest/cart/remove/'+productId).success(function (data) {
            $scope.refreshCart();
        });
    };

    $scope.calGrandTotal = function () {
        var grandTotal=0;
        for (var i=0; i<$scope.cart.cartItems.length; i++) {
            grandTotal+=$scope.cart.cartItems[i].totalPrice;
        }
        return grandTotal;
    };
});

var orderApp = angular.module ("orderApp", []);

orderApp.controller("orderCtrl", function ($scope, $http){
    
	$scope.acceptOrder = function (customerId,pOrderId, productid){
		var delivered=0;
        $http.put('/emusicstore/rest/cart/add/'+customerId+'/'+pOrderId+'/'+productid+'/'+delivered).success(function () {
            alert("The Order has been accepted successfully");
            window.location.reload();
            //document.getElementById(pOrderId).style.display = 'none';            
        });
    };
    $scope.dispatchOrder = function (customerId,pOrderId, productid){
    	var delivered=1;
        $http.put('/emusicstore/rest/cart/add/'+customerId+'/'+pOrderId+'/'+productid+'/'+delivered).success(function () {
            alert("The Order has been dispatched successfully");
            window.location.reload();
            //document.getElementById(pOrderId).style.display = 'none';            
        });
    };
    $scope.deliverOrder = function (customerId,pOrderId, productid){
    	var delivered=2;
        $http.put('/emusicstore/rest/cart/add/'+customerId+'/'+pOrderId+'/'+productid+'/'+delivered).success(function () {
            alert("The Order has been delivered successfully");
            window.location.reload();
            //document.getElementById(pOrderId).style.display = 'none';            
        });
    };
});
