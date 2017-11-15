<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 15/11/2017
  Time: 11:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Restock Ingredients</title>

    <link rel="stylesheet" href="css/button_page_styles.css"/>

    <jsp:include page="headertags.jsp"/>

</head>
<body>

<c:if test="${sessionScope.loginStatus ne 'active'}">
    <jsp:forward page="/"/>
</c:if>

<%--Navbar--%>
<jsp:include page="navbar.jsp"/>

<div class="button-container-ingredients" style="margin-top: 70px; width: 100vw;">

    <div class="button element">
        <div class="card">
            <div class="card-body" style="text-align: center;">
                <h2>Restock Ingredients (Servings Remaining)</h2>
                <c:forEach items="${stock_list}" var="stock">
                <a href="/update_stock?stock=${stock.ingredient_id}">
                    <button type="button" class="btn btn-info btn-lg" style="width: 320px; margin-top: 10px; margin-right: 5px; margin-left: 5px;" ><img src="${stock.img_file_name}" class="img-fluid" alt="ingredient-image" style="margin-right: 15px; height: 40px; border-radius: 15px;"> ${stock.stock_level} <span class="badge badge-light">${stock.ingredient_name}</span></button>
                </a>
                </c:forEach>
            </div>
        </div>
    </div>


</div>


</body>

<jsp:include page="footertags.jsp"/>

</html>
