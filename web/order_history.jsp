<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anderstimo1
  Date: 15/11/2017
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>    <meta charset="UTF-8">
    <title>Order History</title>

    <meta http-equiv="refresh" content="10" />

    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css" media="screen" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/checkout-sidebar.css"/>
    <link rel="stylesheet" type="text/css" href="css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="css/orderstyle.css"/>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="fonts/font-awesome.min.css" rel="stylesheet">
    <link rel="icon" href="" type="image/x-icon"> <!-- Add burger icon-->
</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="container">

<c:choose>
    <c:when test="${no_orders}">
        <div class="col-sm-3, col-md-4"></div>
        <div class="col-sm-6, col-md-4">
            <div class="card" style="background-color: #96b4ed">
                <h4 class="card-title"> No Orders </h4>
                <p class="card-text"> There are no orders to display. </p>
            </div>
        </div>
        <div class="col-sm-3, col-md-4"></div>
    </c:when>

    <c:otherwise>

    <div class="card" style="width: 100%">
        <h4 class="card-title" color="white"><strong>Orders Pending</strong></h4>
    </div>
    <c:forEach items="${pending_list}" var="order" varStatus="outerloop">
    <div class="col-sm-6, col-md-4, col-lg-3">
        <c:choose>
        <c:when test="${param['order']=='completed' && outerloop.index == 0 && order.status == 'Pending'}">
        <div class="card" style="background-color: #bd90b7; border-style: solid; border-width: 5px; border-color: #ab58a0 ">
            <a href="/update?order_id=${order.order_id}">
            <div class="card-body" style=" color: black;">
                <h4 class = "card-title"><strong> New Order! ${order.order_id}</strong></h4>
                <p class="card-text"><strong>Customer ID: ${order.customer.customer_id}</strong></p>
                <p class="card-text"><strong>Time: ${order.convertTime(order.order_datetime)}</strong></p>
                <p class="card-text"><strong>Status: ${order.status} (new) </strong></p>
                    ${order.setPriceString()}
                <p class="card-text"><strong>Price: ${order.priceString}</strong></p>

                <a class="btn btn-info" data-toggle="collapse" href="#${outerloop.index}" aria-expanded="false" aria-controls="${outerloop.index}">
                    Ingredients
                </a>
                <div class="collapse" id="${outerloop.index}">
                    <c:forEach items="${order.items}" var="item" varStatus="innerloop">
                        <div style="border-style: solid; border-width: 5px; border-color: #ab58a0 ">
                            <c:choose>
                                <c:when test="${item.item_type == 'burger'}">
                                    <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Burger </strong></p>
                                </c:when>
                                <c:when test="${item.item_type == 'side'}">
                                    <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Sides </strong></p>
                                </c:when>
                                <c:when test="${item.item_type == 'drink'}">
                                    <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Drinks </strong></p>
                                </c:when>
                            </c:choose>
                            <c:forEach items="${item.ingredients}" var="stock">
                                <p class="card-text" style="margin: 0"> ${stock.ingredient_name}</p>
                            </c:forEach>
                            <p></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            </a>
        </div>
    </div>
    </c:when>
    <c:when test="${order.status == 'Pending'}">
    <div class="card" style="background-color: #d0adbc; border-style: solid; border-width: 5px; border-color: #98506f; margin: 5px">
        <a href="/update?order_id=${order.order_id}">
        <div class="card-body">
            <h4 class = "card-title"><strong>Order ${order.order_id}</strong></h4>
            <%--<p class="card-text"><strong>Customer ID: ${order.customer.customer_id}</strong></p>--%>
            <p class="card-text"><strong>Time: ${order.convertTime(order.order_datetime)}</strong></p>
            <p class="card-text"><strong>Status: ${order.status}</strong></p>
                ${order.setPriceString()}
            <p class="card-text"><strong>Price: ${order.priceString}</strong></p>

            <a class="btn btn-info" data-toggle="collapse" href="#${outerloop.index}" aria-expanded="false" aria-controls="${outerloop.index}">
                Ingredients
            </a>
            <div class="collapse" id="${outerloop.index}">
                <c:forEach items="${order.items}" var="item" varStatus="innerloop">
                    <div  style="border-style: solid; border-width: 5px; border-color: #98506f ">
                        <c:choose>
                            <c:when test="${item.item_type == 'burger'}">
                                <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Burger </strong></p>
                            </c:when>
                            <c:when test="${item.item_type == 'side'}">
                                <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Sides </strong></p>
                            </c:when>
                            <c:when test="${item.item_type == 'drink'}">
                                <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Drinks </strong></p>
                            </c:when>
                        </c:choose>
                        <c:forEach items="${item.ingredients}" var="stock">
                            <p class="card-text" style="margin: 0"> ${stock.ingredient_name}</p>
                        </c:forEach>
                        <p></p>
                    </div>
                </c:forEach>
            </div>
        </div>
        </a>
    </div>
</div>
</c:when>
</c:choose>
</c:forEach>

<div class="card" style="width: 100%">
    <h4 class="card-title" color="white"><strong>Orders Being Made</strong></h4>
</div>
<c:forEach items="${making_list}" var="order" varStatus="outerloop">
    <div class="col-sm-6, col-md-4, col-lg-3">
    <c:choose>
        <c:when test="${order.status == 'Making'}">
            <div class="card" style="background-color: #96b4ed; border-style: solid; border-width: 5px; border-color: #6b99ec; margin: 5px">
                <div class="card-body">
                    <a href="/update?order_id=${order.order_id}">
                    <h4 class = "card-title"><strong>Order ${order.order_id}</strong></h4>
                    <%--<p class="card-text"><strong>Customer ID: ${order.customer.customer_id}</strong></p>--%>
                    <p class="card-text"><strong>Time: ${order.convertTime(order.order_datetime)}</strong></p>
                    <p class="card-text"><strong>Status: ${order.status}</strong></p>
                        ${order.setPriceString()}
                    <p class="card-text"><strong>Price: ${order.priceString}</strong></p>

                    <a class="btn btn-info" data-toggle="collapse" href="#${outerloop.index}" aria-expanded="false" aria-controls="${outerloop.index}">
                        Ingredients
                    </a>
                    <div class="collapse" id="${outerloop.index}">
                        <c:forEach items="${order.items}" var="item" varStatus="innerloop">
                            <div  style="border-style: solid; border-width: 5px; border-color: #6b99ec ">
                                <c:choose>
                                    <c:when test="${item.item_type == 'burger'}">
                                        <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Burger </strong></p>
                                    </c:when>
                                    <c:when test="${item.item_type == 'side'}">
                                        <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Sides </strong></p>
                                    </c:when>
                                    <c:when test="${item.item_type == 'drink'}">
                                        <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Drinks </strong></p>
                                    </c:when>
                                </c:choose>
                                <c:forEach items="${item.ingredients}" var="stock">
                                    <p class="card-text" style="margin: 0"> ${stock.ingredient_name}</p>
                                </c:forEach>
                                <p></p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </a>
            </div>
            </div>
        </c:when>
    </c:choose>
</c:forEach>

<div class="card" style="width: 100%">
    <h4 class="card-title" color="white"><strong>Completed Orders</strong></h4>
</div>
<c:forEach items="${completed_list}" var="order" varStatus="outerloop">
    <div class="col-sm-6, col-md-4, col-lg-3">
    <c:choose>
        <c:when test="${order.status == 'Completed'}">
            <div class="card" style="background-color: #acf8ff; border-style: solid; border-width: 5px; border-color: #6cdde7; margin: 5px ">
                <div class="card-body">
                    <h4 class = "card-title"><strong>Order ${order.order_id}</strong></h4>
                    <%--<p class="card-text"><strong>Customer ID: ${order.customer.customer_id}</strong></p>--%>
                    <p class="card-text"><strong>Time: ${order.convertTime(order.order_datetime)}</strong></p>
                    <p class="card-text"><strong>Status: ${order.status}</strong></p>
                        ${order.setPriceString()}
                    <p class="card-text"><strong>Price: ${order.priceString}</strong></p>

                    <a class="btn btn-info" data-toggle="collapse" href="#${outerloop.index}" aria-expanded="false" aria-controls="${outerloop.index}">
                        Ingredients
                    </a>
                    <div class="collapse" id="${outerloop.index}">
                        <c:forEach items="${order.items}" var="item" varStatus="innerloop">
                            <div  style="border-style: solid; border-width: 5px; border-color: #6cdde7 ">
                                <c:choose>
                                    <c:when test="${item.item_type == 'burger'}">
                                        <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Burger </strong></p>
                                    </c:when>
                                    <c:when test="${item.item_type == 'side'}">
                                        <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Sides </strong></p>
                                    </c:when>
                                    <c:when test="${item.item_type == 'drink'}">
                                        <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Drinks </strong></p>
                                    </c:when>
                                </c:choose>
                                <c:forEach items="${item.ingredients}" var="stock">
                                    <p class="card-text" style="margin: 0"> ${stock.ingredient_name}</p>
                                </c:forEach>
                                <p></p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            </div>
        </c:when>
    </c:choose>
</c:forEach>

<div class="card" style="width: 100%">
    <h4 class="card-title" color="white"><strong>Insufficient Ingredients</strong></h4>
</div>
<c:forEach items="${insufficient_list}" var="order" varStatus="outerloop">
    <div class="col-sm-6, col-md-4, col-lg-3">
        <div class="card" style="background-color: lightcoral; border-style: solid; border-width: 5px; border-color: red; margin: 5px ">
            <div class="card-body">
                <a href="/update?order_id=${order.order_id}">
                <h4 class = "card-title"><strong>Order ${order.order_id}</strong></h4>
                <%--<p class="card-text"><strong>Customer ID: ${order.customer.customer_id}</strong></p>--%>
                <p class="card-text"><strong>Time: ${order.convertTime(order.order_datetime)}</strong></p>
                <p class="card-text"><strong>Status: ${order.status}</strong></p>
                    ${order.setPriceString()}
                <p class="card-text"><strong>Price: ${order.priceString}</strong></p>

                <a class="btn btn-info" data-toggle="collapse" href="#${outerloop.index}" aria-expanded="false" aria-controls="${outerloop.index}">
                    Ingredients
                </a>
                <div class="collapse" id="${outerloop.index}">
                    <c:forEach items="${order.items}" var="item" varStatus="innerloop">
                        <div  style="border-style: solid; border-width: 5px; border-color: red ">
                            <c:choose>
                                <c:when test="${item.item_type == 'burger'}">
                                    <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Burger </strong></p>
                                </c:when>
                                <c:when test="${item.item_type == 'side'}">
                                    <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Sides </strong></p>
                                </c:when>
                                <c:when test="${item.item_type == 'drink'}">
                                    <p class="card-text" style="margin: 0"><strong> Item ${innerloop.index+1}: Drinks </strong></p>
                                </c:when>
                            </c:choose>
                            <c:forEach items="${item.ingredients}" var="stock">
                                <p class="card-text" style="margin: 0"> ${stock.ingredient_name}</p>
                            </c:forEach>
                            <p></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            </a>
        </div>
    </div>
</c:forEach>

    </c:otherwise>
</c:choose>
</div>

<script type="text/javascript" src="/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="/js/main.js" ></script>
<!-- Bootstrap core JavaScript -->
<script src="/jquery/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/loginjs.js"></script>
<script src="js/checkpass.js"></script>

</body>
</html>
