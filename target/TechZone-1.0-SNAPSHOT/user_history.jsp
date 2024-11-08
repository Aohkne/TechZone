<%-- 
    Document   : user_history
    Created on : Nov 3, 2024, 2:37:01 PM
    Author     : Le Huu Khoa - CE181099
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TechZone</title>
        <!-- link logo -->
        <link rel="icon" href="./asset/img/img_all/logo.png" type="image/x-icon">

        <!-- link css -->
        <link rel="stylesheet" href="asset/css/base.css">
        <link rel="stylesheet" href="asset/css/grid.css">
        <link rel="stylesheet" href="asset/css/style_user_history.css">
        <link rel="stylesheet" href="asset/css/css_all/style_cart.css">
        <link rel="stylesheet" href="asset/css/css_all/style_chat.css">
        <link rel="stylesheet" href="asset/css/css_all/style_accountUser.css">
        <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">


        <!-- link js -->
        <script src="./asset/js/js_all/js_chat.js" defer></script>
        <script src="./asset/js/js_all/js_cart.js" defer></script>
        <script src="./asset/js/js_all/js_accountUser.js" defer></script>
        <script src="./asset/js/js_user_history.js" defer></script>
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
                                        <div colspan="9">No order details found for this user.</div>
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
                                    <a href="/User" class="account__link">
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
                    </div>

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
    <div class="Bao__grid">
        <!-- C?t 1: 20% -->
        <div class="nav__1">
            <div class="history__profile">
                <div class="history__profile-picture">
                    <img src="${avatar}" alt="User_img">
                </div>
                <div class="profile__detail">
                    <div class="history__name">${username}</div>
                    <div class="history__location">${address}</div>
                </div>
            </div>
            <div class="nav__content">
                <div class="orderhistory__picture">
                    <img src="./asset/img/img_history/orderhistory.png" alt="">
                </div>
                <a href="/History">
                    <div class="orderhistory__content">Order History</div>
                </a>

            </div>
            <div class="nav__content">
                <div class="voucher__picture">
                    <img src="./asset/img/img_history/voucherorderhistory.png" alt="">
                </div>
                <a href="/Voucher">
                    <div class="voucher__content">Voucher</div>
                </a>
            </div>  
        </div>

        <!-- C?t 2: 80% -->
        <div class="nav__2">
            <div class="history__search">
                <form action="/History" method="GET">
                    <button type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                    <input type="text" placeholder="Search..." name="searchOrder" value="">
                </form>
            </div>

            <div class="historylist__items">

                <!-- entry.key user id
                     0 - Order Detail ID
                     1 - Quantity  
                     2 - Price
                     3 - Status
                     4 - Product Image
                     5 - Product Name
                     6 - Product Description
                     7 - Voucher Type
                     8 - Voucher Image
                     9 - Voucher Name
                -->
                <c:if test="${not empty orderDetailsMap}">
                    <c:forEach var="entry" items="${orderDetailsMap}">
                        <c:forEach var="orderDetail" items="${entry.value}">
                            <div class="history__list">
                                <div class="history__items">
                                    <div class="history__items-pro">
                                        <div class="list__picture">
                                            <img src="${orderDetail[5]}" alt="">
                                        </div>
                                        <div class="list__content">
                                            <div class="list__name">${orderDetail[6]}</div>
                                            <div class="list__des">${orderDetail[7]}</div>
                                        </div>
                                    </div>
                                    <div class="list__total">
                                        <div class="total__content">Total (${orderDetail[1]} product)</div>
                                        <div class="total__price">${orderDetail[2]} VND</div>
                                    </div>
                                </div>
                                <div class="voucher__list-title">
                                    <div class="voucherlist__content">
                                        <div class="voucher__list-picture">
                                            <img src="${orderDetail[9]}" alt="">
                                        </div>
                                        <div class="discount__content">${orderDetail[10]}</div>
                                    </div>
                                    <div class="historyorder__btn">

                                        <c:choose>
                                            <c:when test="${orderDetail[3] == 'Not Yet'}">
                                                <div class="list__delivery">
                                                    <div class="list__status">Status:</div>
                                                    <div class="list__status-content red">Not Yet</div>
                                                </div>
                                                <a href="/History?OrderDetailId=${orderDetail[0]}" class="list__btn red">Cancel</a>
                                            </c:when>
                                            <c:when test="${orderDetail[3] == 'Process'}">
                                                <div class="list__delivery">
                                                    <div class="list__status">Status:</div>
                                                    <div class="list__status-content yellow">Process</div>

                                                </div>
                                                <a href="/History?OrderDetailId=${orderDetail[0]}" class="list__btn red">Cancel</a>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="list__delivery">
                                                    <div class="list__status">Status:</div>
                                                    <div class="list__status-content green">Delivered</div>
                                                </div>
                                                <a href="/Product?id=${orderDetail[4]}" class="list__btn green">Reorder</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:forEach>
                </c:if>

                <!--                <div class="history__list">
                                    <div class="history__items">
                                        <div class="history__items-pro">
                                            <div class="list__picture">
                                                <img src="./asset/img/img_all/img_product/img_vr/vr.png" alt="">
                                            </div>
                                            <div class="list__content">
                                                <div class="list__name">Name</div>
                                                <div class="list__des">Description...</div>
                                            </div>
                                        </div>
                                        <div class="list__total">
                                            <div class="total__content">Total (1 product)</div>
                                            <div class="total__price">300.000 VND</div>
                                        </div>
                                    </div>
                                    <div class="voucher__list-title">
                                        <div class="voucherlist__content">
                                            <div class="voucher__list-picture">
                                                <img src="./asset/img/img_all/img_cart/voucher_rare.png" alt="">
                                            </div>
                                            <div class="discount__content">18% off/maximum discount 250.000 VND</div>
                                        </div>
                                        <div class="historyorder__btn">
                                            <div class="list__delivery">
                                                <div class="list__status">Status:</div>
                                                <div class="list__status-content green">Delivered</div>
                                            </div>
                                            <button class="list__btn green">Reorder</button>
                                        </div>
                                    </div>
                                </div>
                
                
                
                
                                <div class="history__list">
                                    <div class="history__items">
                                        <div class="history__items-pro">
                                            <div class="list__picture">
                                                <img src="./asset/img/img_all/img_product/img_vr/vr.png" alt="">
                                            </div>
                                            <div class="list__content">
                                                <div class="list__name">Name</div>
                                                <div class="list__des">Description...</div>
                                            </div>
                                        </div>
                                        <div class="list__total">
                                            <div class="total__content">Total (1 product)</div>
                                            <div class="total__price">300.000 VND</div>
                                        </div>
                                    </div>
                                    <div class="voucher__list-title">
                                        <div class="voucherlist__content">
                                            <div class="voucher__list-picture">
                                                <img src="./asset/img/img_all/img_cart/voucher_normal.png" alt="">
                                            </div>
                                            <div class="discount__content">18% off/maximum discount 250.000 VND</div>
                                        </div>
                                        <div class="historyorder__btn">
                                            <div class="list__delivery">
                                                <div class="list__status">Status:</div>
                                                <div class="list__status-content red">Not Yet</div>
                                            </div>
                                            <button class="list__btn red">Cancel</button>
                                        </div>
                                    </div>
                                </div>
                
                
                                <div class="history__list">
                                    <div class="history__items">
                                        <div class="history__items-pro">
                                            <div class="list__picture">
                                                <img src="./asset/img/img_all/img_product/img_vr/vr.png" alt="">
                                            </div>
                                            <div class="list__content">
                                                <div class="list__name">Name</div>
                                                <div class="list__des">Description...</div>
                                            </div>
                                        </div>
                                        <div class="list__total">
                                            <div class="total__content">Total (1 product)</div>
                                            <div class="total__price">300.000 VND</div>
                                        </div>
                                    </div>
                                    <div class="voucher__list-title">
                                        <div class="voucherlist__content">
                                            <div class="voucher__list-picture">
                                                <img src="./asset/img/img_all/img_cart/voucher_medium.png" alt="">
                                            </div>
                                            <div class="discount__content">18% off/maximum discount 250.000 VND</div>
                                        </div>
                                        <div class="historyorder__btn">
                                            <div class="list__delivery">
                                                <div class="list__status">Status:</div>
                                                <div class="list__status-content yellow">Progress</div>
                
                                            </div>
                                            <button class="list__btn red">Cancel</button>
                                        </div>
                                    </div>
                                </div>-->


            </div>
        </div>

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
                    <a href="">Earphone</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="">Watch</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="">Laptop</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="">Console</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="">Vr</a>
                </div>
                <div class="footer__categoryItem">
                    <a href="">Speaker</a>
                </div>


            </div>
        </div>

        <div class="footer__survey col l-4">
            <div class="survey__title">Evaluate</div>
            <div class="survey__input">
                <input type="text" placeholder="Enter Your Email">
                <a href="./feedback.html">
                    <button class="survey__btn">Subcrise</button>
                </a>
            </div>
        </div>

    </div>
</div>

<div class="footer__copyright">
    ©2024 - Group 6
</div>

</body>

</html>