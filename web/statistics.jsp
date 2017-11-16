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

    <link rel="stylesheet" type="text/css" href="css/statistics.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap">
    <link rel="stylesheet" href="css/loginstyle.css">

    <script type="text/javascript" src="js/stocktable.js"></script>

</head>

<body style="background-color: black">

<!--======= Start Nav ==========-->
<jsp:include page="navbarLogin.jsp"/>

<div class="container d-flex flex-row justify-content-around">

    <!-- Stock Table -->

    <div class="d-flex flex-column" style="max-width: 30%; float:left; padding-top: 100px">
        <h3 style="color: blueviolet; text-decoration: underline">Stock Levels</h3>
        <input type="text" id="stockInput" onkeyup="stockFilter()" placeholder="Search.." style="margin: 10px;">
        <div class="d-flex flex-row justify-content-center">
            <table id="stockTable" style="padding-top: 10px">
                <tr class="header">
                    <th style="width:60%;">Ingredient Name</th>
                    <th style="width:20%;">Stock</th>
                    <th style="width:20%;">Used This Month</th>
                </tr>
                <tr>
                    <c:forEach items="${all_stock}" var="ingredient">
                <tr>
                    <td>${ingredient.ingredient_name}</td>
                    <td>${ingredient.stock_level}</td>
                    <td>${ingredient.used_this_month}</td>
                </tr>
                </c:forEach>

            </table>


        </div>
    </div>

    <div class="d-flex flex-column" style="max-width: 30%; float:left; padding-top: 100px">
        <h3 style="color: blueviolet; text-decoration: underline">Item Purchasing Trends</h3>
        <div class="d-flex flex-row justify-content-center">
            <table id="itemTable" style="padding-top: 10px">
                <tr class="header">
                    <th style="width:60%;">Item Type</th>
                    <th style="width:20%;">Bought This Month</th>
                </tr>
                <tr>
                    <c:forEach items="${item_names}" var="name" varStatus="loop">
                <tr>
                    <td>${name}</td>
                    <td><c:out value="${item_counts[loop.index]}" /></td>
                </tr>
                </c:forEach>

            </table>


        </div>
    </div>


    <!-- Chart -->
    <div class="d-flex flex-row justify-content-center" style="max-width: 30%; float:left; padding-top: 100px">
        <h3 style="color: blueviolet; text-decoration: underline">Chart</h3>

    </div>

</div>

</div>

</body>

</html>
