<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 15/11/2017
  Time: 9:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Staff Home</title>

    <link rel="stylesheet" href="css/button_page_styles.css">

    <jsp:include page="headertags.jsp"/>
</head>
<body>

<!--======= Start Nav ==========-->
<jsp:include page="navbar.jsp"/>

<c:if test="${sessionScope.loginStatus ne 'active'}">
    <jsp:forward page="/"/>
</c:if>


<div class="button-container">

    <div class="button element">
        <div class="card">
            <div class="card-body">
                <button type="button" class="btn btn-primary btn-lg">Order Processing</button>
                <button type="button" class="btn btn-primary btn-lg">Stock Levels</button>
                <c:if test="${sessionScope.staff.staff_type == 'Manager'}">
                    <button type="button" class="btn btn-primary btn-lg">Monthly Reporting</button>
                </c:if>
            </div>
        </div>
    </div>


</div>


</body>

<jsp:include page="footertags.jsp"/>

</html>
