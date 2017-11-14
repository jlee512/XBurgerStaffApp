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
</head>
<body>

<!--======= Start Nav ==========-->
<jsp:include page="navbar.jsp"/>

<c:if test="${sessionScope.loginStatus ne 'active'}">
    <jsp:forward page="/"/>
</c:if>



</body>
</html>
