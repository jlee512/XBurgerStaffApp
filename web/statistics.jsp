<%--
  Created by IntelliJ IDEA.
  User: Jack

  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="main.java.entity.Stock" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>

    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/statistics.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap">
    <link rel="stylesheet" href="/css/loginstyle.css">

    <script type="text/javascript" src="js/stocktable.js"></script>

</head>

<body>

<c:if test="${sessionScope.loginStatus ne 'active'}">
    <jsp:forward page="/"/>
</c:if>

<!--======= Start Nav ==========-->
<jsp:include page="navbarLogin.jsp"/>

<div class="container">

    <!-- Stock Table -->

    <div class="table-responsive col-lg-2 col-sm-3 col-xs-4" style="max-width: 30%; float:left; padding-top: 100px">
        <h3>Stock Levels</h3>
    <table id="stockTable" style="padding-top: 10px">
        <input type="text" id="stockInput" onkeyup="stockFilter()" placeholder="Search..">
        <tr class="header">
            <th style="width:60%;">Ingredient Name</th>
            <th style="width:20%;">Stock</th>
            <th style="width:20%;">Price</th>
        </tr>
        <tr>
        <c:forEach items="${all_stock}" var="ingredient">
        <tr>
            <td>${ingredient.ingredient_name}</td>
            <td>${ingredient.stock_level}</td>
            <td>${'$'}${ingredient.price}</td>
        </tr>
        </c:forEach>
        </tr>
    </table>
    </div>


    <!-- Low Stock Table -->
    <div class="table-responsive col-lg-2 col-sm-3 col-xs-4" style="max-width: 30%; float:left; padding-top: 100px">
        <h3>Low Stock!</h3>
        <table id="stockTable">

            <tr class="header">
                <th style="width:60%;">Ingredient Name</th>
                <th style="width:20%;">Stock</th>
            </tr>
            <tr>
                <c:forEach items="${all_stock}" var="ingredient">
                        <c:if test="${ingredient.stock_level < 5}">
            <tr>
                <td>${ingredient.ingredient_name}</td>
                <td>${ingredient.stock_level}</td>
            </tr>
            </c:if>
            </c:forEach>
            </tr>
        </table>
    </div>



    <!-- Last Month Table -->
    <div class="table-responsive col-lg-2 col-sm-3 col-xs-4" style="max-width: 30%; float:left; padding-top: 100px">
        <h3>Low Stock!</h3>
        <table id="stockTable">

            <tr class="header">
                <th style="width:60%;">Ingredient Name</th>
                <th style="width:20%;">Stock</th>
            </tr>
            <tr>
                <c:forEach items="${Order_API.getLastMonthOfOrders()}" var="current">
                <c:if test="${ingredient.stock_level < 5}">
            <tr>
                <td>${current.order_id_received}</td>
                <td>${current.order_id_received}</td>
            </tr>
            </c:if>
            </c:forEach>
            </tr>
        </table>
    </div>



</div>

</body>


</html>
