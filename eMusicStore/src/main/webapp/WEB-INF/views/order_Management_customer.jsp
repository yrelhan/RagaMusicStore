<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Order Management Page</h1>

            <p class="lead">View Your Orders.</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Product Name</th>
                <th>Quantity</th>
                <th>status</th>
            </tr>
            </thead>
            <c:forEach items="${pOrders}" var="pOrder">
                <tr>
                    <td>${pOrder.productname}</td>
                    <td>${pOrder.quantity}</td>
                    <c:if test="${pOrder.status == 0}"><td>Pending</td></c:if>
                    
                    <c:if test="${pOrder.status == 1 && pOrder.status1==0}"><td>Order Accepted, please complete checkout</td></c:if>
                    <c:if test="${pOrder.status == 1 && pOrder.status1==4}"><td>Order Accepted, will be shipped soon</td></c:if>
                    <c:if test="${pOrder.status == 2}"><td>Shipped</td></c:if>
                    <c:if test="${pOrder.status == 3}"><td>Delivered</td></c:if>
                    <!--  <td><c:choose><c:when test="${pOrder.status == 0}">Pending</c:when><c:otherwise>Accepted</c:otherwise></c:choose></td>
                    -->
                </tr>
            </c:forEach>
        </table>

        <%@include file="/WEB-INF/views/template/footer.jsp" %>
