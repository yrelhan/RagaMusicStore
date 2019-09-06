<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>





<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Product Detail</h1>

            <p class="lead">Here is the detail information of the product!       
            </p>
        </div>

        <div class="container" ng-app = "cartApp">
            <div class="row">
                <div class="col-md-5">
                    <img src="<c:url value="/resources/images/${product.productId}.png" /> " alt="image"
                             style="width:100%"/>
                </div>

                <div class="col-md-5">
                    <h3>${product.productName}</h3>
                    <p>${product.productDescription}</p>
                    <p>
                       <strong>Manufacturer</strong> : ${product.productManufacturer}
                    </p>
                    <p>
                        <strong>Category</strong> : ${product.productCategory}
                    </p>
                    <p>
                        <strong>Condition</strong> : ${product.productCondition}
                    </p>
                    <h4>${product.productPrice} INR</h4>
                    <h4 hidden="hidden" id="unitsstock">${product.unitInStock}</h4>
                    
                    

                    <br>

                    <c:set var="role" scope="page" value="${param.role}" />
                    <c:set var="url" scope="page" value="/product/productList" />
                    <c:if test="${role eq 'admin'}">
                        <c:set var="url" scope="page" value="/admin/productInventory" />
                    </c:if>
					<c:if test="${pageContext.request.userPrincipal.name  != null}">
					<c:if test="${pageContext.request.userPrincipal.name ne 'admin'}">
                    <p ng-controller="cartCtrl">
                        <!-- <a href="<c:url value="${url}" />" class="btn btn-default">Back</a>-->
                        <a href="#" class="btn btn-warning btn-large"
                           ng-click="addToCart('${product.productId}')"><span
                                class="glyphicon glyphicon-shopping-cart"></span>Order
                            Now</a>
	                        <div class="col-sm-2">
							  <div class="input-group spinner">
							    <input type="text" class="form-control" value="1" min="1" max="${product.unitInStock}" id="quantity" readonly="readonly">
							    <div class="input-group-btn-vertical">
							     <button class="btn btn-default" type="button"><i class="glyphicon glyphicon-chevron-up"></i></button>
							      <button class="btn btn-default" type="button"><i class="glyphicon glyphicon-chevron-down"></i></button>
							    </div>
							  </div>
							 </div>
                        
                        <a href="<spring:url value="/customer/cart" />"
                           class="btn btn-default"><span class="glyphicon glyphicon-hand-right"></span>View Cart</a></c:if></c:if>
                    </p>
                </div>
            </div>
        </div>



        <script src="<c:url value="/resources/js/controller.js" /> "></script>
        <%@include file="/WEB-INF/views/template/footer.jsp" %>
