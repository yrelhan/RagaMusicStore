<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container-wrapper">
    <div class="container" ng-app = "orderApp">
        <div class="page-header">
            <h1>Order Management Page</h1>

            <p class="lead">This is the Order management page.</p>
        </div>
        <table class="table table-striped table-hover" ng-controller="orderCtrl">
            <thead>
            <tr class="bg-success">
                <th>User Name</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Shipping</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <c:forEach items="${pOrders}" var="pOrder">
            <c:if test="${pOrder.status != 3}">
                <tr id="${pOrder.id}">
                    <td>${pOrder.customerName}</td>
                    <td>${pOrder.productname}</td>
                    <td>${pOrder.quantity}</td>
                    <td><textarea rows="5">${pOrder.shippingAdress}</textarea></td>
                    <td><p class="text text-info">
                    <c:if test="${pOrder.status == 0 && pOrder.status1 == 0}">Pending</c:if>
                    <c:if test="${pOrder.status == 1 && pOrder.status1 == 0}">Order accepted, <br>user has not checkout out yet</c:if>
                    <c:if test="${pOrder.status == 1 && pOrder.status1 == 4}">Order accepted,<br>user has checkout out</c:if>
                    <c:if test="${pOrder.status == 2 && pOrder.status1 == 4}">Dispatched</c:if>
                    <c:if test="${pOrder.status == 3 && pOrder.status1 == 4}">Delivered</c:if>
                    </p></td>
                     <c:if test="${pOrder.status == 0}"><td id="accept"><a href="#" class="btn btn-success"
                           ng-click="acceptOrder(${pOrder.customerid},${pOrder.id}, ${pOrder.productid});"><span
                                class="glyphicon glyphicon-ok"></span>Accept</a>
                    </td></c:if>
                    <c:if test="${pOrder.status == 1 && pOrder.status1==4}"><td id="dispatch"><a href="#" class="btn btn-success"
                           ng-click="dispatchOrder(${pOrder.customerid},${pOrder.id}, ${pOrder.productid});"><span
                                class="glyphicon glyphicon-ok"></span>Dispatch</a>
                    </td></c:if>
                    <c:if test="${pOrder.status == 2 && pOrder.status1==4}"><td id="delivred"><a href="#" class="btn btn-success"
                           ng-click="deliverOrder(${pOrder.customerid},${pOrder.id}, ${pOrder.productid});"><span
                                class="glyphicon glyphicon-ok"></span>Delivered</a>
                    </td></c:if>
                    
                   
                </tr>
            </c:if>
            </c:forEach>
        </table>
        
        <script src="<c:url value="/resources/js/controller.js" /> "></script>
        <%@include file="/WEB-INF/views/template/footer.jsp" %>
