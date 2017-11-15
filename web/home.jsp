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
            <div class="card-body" style="text-align: center;">
                <h2>Staff Console</h2>
                <div class="staff-button">
                    <a href="/order"><button type="button" class="btn btn-info btn-lg">PROCESS ORDERS</button></a>
                </div>
                <div>
                    ${hello}
                </div>
                <div class="staff-button">
                    <a href="/stock"><button type="button" class="btn btn-info btn-lg">MANAGE STOCK LEVELS</button></a>
                </div>
                <c:if test="${sessionScope.staff.staff_type == 'Manager'}">
                    <div class="staff-button">
                        <a href="/statistics"><button type="button" class="btn btn-info btn-lg">STATISTICS</button></a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>


</div>


</body>

<jsp:include page="footertags.jsp"/>

</html>
