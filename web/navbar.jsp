<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 13/11/2017
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--======= Start Nav ==========-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">

    <a class="navbar-brand" href="#">
        <img src="images/logo-transparent.png" width="100" height="50">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
            aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/home">HOME |</a>

            </li>
            <li class="nav-item">
                <a class="nav-link" href="/order">ORDERS |</a>

            </li>
            <li class="nav-item">
                <a class="nav-link" href="/restock">STOCK |</a>

            </li>
            <c:if test="${sessionScope.staff.staff_type == 'Manager'}">
            <li class="nav-item">
                <a class="nav-link" href="/statistics">STATISTICS |</a>

            </li>
            </c:if>
            <c:if test="${sessionScope.loginStatus == 'active'}">
                <li class="nav-item">
                    <a class="nav-link" href="/logout">LOGOUT</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>


