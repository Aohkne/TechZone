<%-- 
    Document   : user_category
    Created on : Oct 5, 2024, 7:46:37 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="DAOs.CategoryDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- link logo -->
        <link rel="icon" href="./asset/img/img_all/logo.png" type="image/x-icon">

        <!-- link css -->
        <link rel="stylesheet" href="asset/css/base.css">
        <link rel="stylesheet" href="asset/css/grid.css">
        <link rel="stylesheet" href="asset/css/style_user_category.css">
        <link rel="stylesheet" href="asset/css/css_all/style_cart.css">
        <link rel="stylesheet" href="asset/css/css_all/style_chat.css">
        <link rel="stylesheet" href="asset/css/css_all/style_accountUser.css">
        <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">


        <!-- link js -->
        <script src="./asset/js/js_all/js_chat.js" defer></script>
        <script src="./asset/js/js_all/js_cart.js" defer></script>
        <script src="./asset/js/js_all/js_accountUser.js" defer></script>
        <script src="./asset/js/js_user_category.js" defer></script>
    </head>

    <body>


        <!-- Header -->
        <div class="nav">

            <!-- Nav Header -->
            <div class="nav__header">

                <ul class="nav__shopInfo">

                    <li class="shopInfo__item">
                        <a class="shopInfo__link" href="">Download</a>

                        <!-- Download POPUP -->
                        <div class="download__popup">
                            <img src="./asset/img/img_index/img_qr/qr.png" alt="" class="qr__img">
                            <img src="./asset/img/img_index/img_qr/app.png" alt="" class="app__img">
                        </div>
                    </li>

                    <li class="shopInfo__item">
                        <a class="shopInfo__link" href="">Connect</a>
                        <i class="fa-brands fa-facebook"></i>
                        <i class="fa-brands fa-square-instagram"></i>
                    </li>
                </ul>


                <ul class="nav__user">

                    <li class="user__item">
                        <i class="fa-solid fa-bell"></i>
                        <a href="#" class="user__link" href="">Notification</a>

                        <!-- Notification POPUP -->
                        <div class="notification__popup">
                            <div class="notification__title">
                                Notification
                            </div>

                            <div class="notification__list">

                                <c:choose>
                                    <c:when test="${not empty orderDetails}">
                                        <c:forEach var="orderDetail" items="${orderDetails}">
                                            <c:choose>
                                                <c:when test="${orderDetail.check == 'true'}">
                                                    <a href="/History?orderDetailId=${orderDetail.orderDetailId}">
                                                        <div class="notification__item active">
                                                            <div class="notification__info">
                                                                <div class="notification__heading">Order successful</div>
                                                                <div class="notification__description">Order ${orderDetail.proName} successfully!</div>
                                                            </div>
                                                            <div class="notification__time">${orderDetail.orderDate}</div>
                                                        </div>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="/History?orderDetailId=${orderDetail.orderDetailId}">
                                                        <div class="notification__item">
                                                            <div class="notification__info">
                                                                <div class="notification__heading">Order successful</div>
                                                                <div class="notification__description">Order ${orderDetail.proName} successfully!</div>
                                                            </div>
                                                            <div class="notification__time">${orderDetail.orderDate}</div>
                                                        </div>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="9">No order details found for this user.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                        </div>


                    </li>

                    <li class="user__item">
                        <i class="fa-solid fa-circle-question"></i>
                        <a class="user__link" href="./user_help.jsp">Help</a>
                    </li>


                    <div class="user__account">
                        <%
                            Cookie[] cookies = request.getCookies();
                            boolean isId = false;
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("id")) {
                        %>
                        <!-- Account User  -->
                        <div class="account__img">
                            <img src="${avatar}" alt="">
                            <span>${username}</span>
                        </div>
                    </div>

                    <!-- Account User -->
                    <div class="account__container">
                        <div class="account__content">
                            <div class="account__information">
                                <img src="${avatar}" alt="">
                                <div class="account__description">
                                    <div class="account__username">${username}</div>
                                    <div class="account__mail">${email}</div>
                                </div>
                            </div>
                            <div class="account__list">
                                <div class="account__item">
                                    <a href="user_profile.jsp" class="account__link">
                                        My Profile
                                    </a>
                                </div>
                                <div class="account__item">
                                    <a href="/History" class="account__link">
                                        Order History
                                    </a>
                                </div>
                                <div class="account__item">
                                    <a href="/Voucher" class="account__link">
                                        Voucher
                                    </a>
                                </div>
                                <div class="account__item">
                                    <a href="/Cart" class="account__link">
                                        Cart
                                    </a>
                                </div>
                            </div>
                            <div class="account__logout">
                                <a href="/Logout">Log out</a>
                            </div>
                        </div>
                    </div

                    <%
                                    isId = true;
                                    break;
                                }
                            }
                        }

                        if (!isId) {
                    %>
                    <div class="account__register" onclick="window.location.href = '/Login'">Register</div>
                    <div class="account__login" onclick="window.location.href = '/Login'">Log in</div>
            </div>
            <%
                }
            %>  

        </ul>


    </div>

    <!-- Nav Search -->

    <div class="navSearch grid">
        <div class="row">

            <div class="navSearch__logo col l-2">
                <a href="/Home">
                    <img src="./asset/img/img_all/logoImage.png" alt="">
                </a>
            </div>

            <div class="navSearch__search col l-8">
                <form action="/Category" method="GET">
                    <input type="text" name="search">
                    <button type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>
            </div>

            <div class="navSearch__cart col l-2">
                <div class="navSearch__cartIcon">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <div class="list__quantity">0</div>
                </div>
            </div>

            <!-- Cart -->
            <div class="cart__container">
                <div class="cart__title">Cart</div>

                <div class="cart__list">


                    <!-- <div class="cart__item">

                            <div class="row">
                                <input type="hidden" value="P1">
                                <div class="cart__icon col l-1">
                                    <input type="checkbox" name="" id="">
                                </div>

                                <div class="cart__img col l-4">
                                    <img src="./asset/img/img_index/img_sale/laptopSale.png" alt="">
                                </div>

                                <div class="cart__content col l-5">
                                    <div class="row">

                                        <div class="cart__name col l-12">Air pod</div>

                                        <div class="cart__description col l-12">

                                            <div class="cart__price">? 300.000</div>

                                            <div class="cart__quantity">
                                                <i class="fa-solid fa-plus"></i>
                                                <div class="cart__num">1</div>
                                                <i class="fa-solid fa-minus"></i>
                                            </div>

                                        </div>

                                    </div>
                                </div>

                                <div class="cart__btnDelete col l-2">
                                    <button>Delete</button>
                                </div>
                            </div>

                        </div> -->


                </div>





                <div class="cart__footer">
                    <img class="img__EmptyCart" src="./asset/img/img_all/img_cart/emptyCart.png" alt="">
                    <!-- <div class="cartFooter__container">

                            <div class="cart__icon">
                                <input type="checkbox" name="" id=""> <span>All</span>
                            </div>

                            <div class="cart__totalPrice">Total: <span>600.000 VND</span></div>
                        </div>

                        <div class="cart__btn">
                            <button>Order</button>
                        </div> -->
                </div>

            </div>

        </div>
    </div>
</div>


<!-- body -->
<div class="app">
    <div class="content">
        <!-- Nav__content -->
        <div class="nav__content">
            <div class="nav__list-title">Filter By</div>
            <div class="nav__list" id="popular">
                Category
                <div class="dropdown-list" id="popular-list">
                    <a href="/Category?search=""">All</a>
                    <%
                        ResultSet rs = (ResultSet) request.getAttribute("categories");
                        while (rs != null && rs.next()) {
                    %>
                    <a href="/Category?id=<%= rs.getString("cat_id")%>"><%= rs.getString("cat_name")%></a>
                    <%}%>                    
                </div>
            </div>
            <div class="nav__action">
                <%
                    String id = null;

                    if (request.getAttribute(
                            "idCat") != null) {
                        id = (String) request.getAttribute("idCat");
                    } else if (request.getAttribute(
                            "increase") != null && !(request.getAttribute("increase").equals("increase"))) {
                        id = (String) request.getAttribute("increase");
                    } else if (request.getAttribute(
                            "decrease") != null && !(request.getAttribute("decrease").equals("decrease"))) {
                        id = (String) request.getAttribute("decrease");
                    }
                %>
                <a href="/Category?increase=<%= id != null ? id : "increase"%>">
                    Increase
                </a>
            </div>
            <div class="nav__action">
                <a href="/Category?decrease=<%= id != null ? id : "decrease"%>">
                    Decrease
                </a>
            </div>
        </div>

        <div class="cate__content">
            <c:forEach var="product" items="${productList}">
                <c:if test="${product.pro_quantity > 0}">
                    <div class="cate__list">
                        <input type="hidden" value="${product.proDetail_id}">
                        <a href="./Product?id=${product.proDetail_id}" class="cate__items">
                            <div class="cate__title">
                                <div class="cate__img">
                                    <img src="${product.pro_image}" alt="${product.pro_name}">
                                </div>
                                <div class="content__list">
                                    <div class="cate__name">${product.pro_name}</div>

                                    <c:choose>
                                        <c:when test="${empty product.pro_sale}">
                                            <div class="cate__price">${product.pro_price} VND</div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="cate__price">${product.pro_sale} VND</div>
                                            <div class="cate__discount">${product.pro_price} VND</div>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                        </a>
                        <div class="order__list">
                            <i class="order__icon fa-solid fa-cart-plus"></i>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>





        <!-- Page transition bar -->
<!--        <div class="page__bar">
            <a href="#" id="prev">&laquo;</a>
            <a href="#" class="page-num active" data-page="1">1</a>
            <a href="#" class="page-num" data-page="2">2</a>
            <a href="#" class="page-num" data-page="3">3</a>
            <a href="#" class="page-num" data-page="4">4</a>
            <a href="#" class="page-num" data-page="5">5</a>
            <a href="#" class="page-num" data-page="6">6</a>
            <a href="#" id="next">&raquo;</a>
        </div>-->
    </div>

</div>


<!-- chat -->
<div class="chat__container">
    <img src="./asset/img/img_all/img_cart/chat.png" alt="">

    <div class="chat__content">
        <div class="chat__title">
            <span>Chat</span>
            <i class="fa-solid fa-xmark"></i>
        </div>
        <div class="chat__list">
            <div class="chat__item admin">
                <span>How can I help you ?</span>
            </div>
            <!--                <div class="chat__item user">
                                <span>???</span>
                            </div>-->

            <c:forEach items="${messages}" var="message">
                <div class="chat__item ${message.senderId == userId ? 'user' : 'admin'}"> 
                    <span>${message.messageText}</span>
                </div>
            </c:forEach>

        </div>
        <div class="chat__input">
            <form action="/Home" method="GET">
                <input type="text" placeholder="Chat Something" name="messageInput">
                <button type="submit" class="chat__sent">
                    <i class="fa-solid fa-paper-plane"></i>
                </button>
            </form>
        </div>
    </div>
</div>


<!-- Footer -->
<div class="footer">
    <div class="row">

        <div class="footer__social col l-4">
            <img src="./asset/img/img_all/logoImage.png" alt="" class="footer__logo">

            <div class="footer__description">
                The best experience for customers is our principle and motivation
            </div>
            <div class="footer__icon">
                <div class="row">

                    <div class="icon__item col l-3">
                        <a href="" class="icon__link">
                            <i class="fa-brands fa-facebook"></i>
                        </a>
                    </div>

                    <div class="icon__item col l-3">
                        <a href="" class="icon__link">
                            <i class="fa-brands fa-square-instagram"></i>
                        </a>
                    </div>

                    <div class="icon__item col l-3">
                        <a href="https://github.com/Aohkne/TechZone" class="icon__link">
                            <i class="fa-brands fa-github"></i>
                        </a>
                    </div>

                    <div class="icon__item col l-3">
                        <a href="" class="icon__link">
                            <i class="fa-solid fa-envelope"></i>
                        </a>
                    </div>

                </div>

            </div>
        </div>


        <div class="footer__contact col l-2">
            <div class="contact__title">Contact</div>

            <div class="contact__list">

                <div class="contact__item">
                    <i class="fa-solid fa-phone"></i>
                    0123421xxx
                </div>

                <div class="contact__item">
                    <i class="fa-solid fa-location-dot"></i>
                    9/123, can tho
                </div>

            </div>
        </div>

        <div class="footer__category col l-2">
            <div class="footer__categoryTitle">Category</div>

            <div class="footer__categoryList">

                <div class="footer__categoryItem">
                    <a href="/Category?id=2">Earphone</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="/Category?id=3">Watch</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="/Category?id=4">Laptop</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="/Category?id=5">Console</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="/Category?id=6">Vr</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="/Category?id=7">Speaker</a>
                </div>


            </div>
        </div>

        <div class="footer__survey col l-4">
            <div class="survey__title">Evaluate</div>
            <div class="survey__input">
                <input type="text" placeholder="Enter Your Email">
                <a href="./feedback.jsp">
                    <button class="survey__btn">Subcrise</button>
                </a>
            </div>
        </div>

    </div>
</div>

<div class="footer__copyright">
    ?2024 - Group 6
</div>

</body>

</html>
