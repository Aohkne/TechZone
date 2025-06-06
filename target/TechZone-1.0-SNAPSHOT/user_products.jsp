<%-- 
    Document   : user_products
    Created on : Sep 29, 2024, 9:37:36 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="DAOs.UserDAO"%>
<%@page import="DAOs.BrandDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link rel="stylesheet" href="asset/css/style_user_products.css">
        <link rel="stylesheet" href="asset/css/css_all/style_cart.css">
        <link rel="stylesheet" href="asset/css/css_all/style_chat.css">
        <link rel="stylesheet" href="asset/css/css_all/style_accountUser.css">
        <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">

        <!-- link js -->
        <script src="./asset/js/js_all/js_cart.js" defer></script>
        <script src="./asset/js/js_all/js_chat.js" defer></script>
        <script src="./asset/js/js_user_products.js" defer></script>
        <script src="./asset/js/js_all/js_accountUser.js" defer></script>
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
                            String idUser = "";
                            Cookie[] cookies = request.getCookies();
                            boolean isId = false;
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("id")) {
                        %>
                        <!-- Account User  -->
                        <div class="account__img">
                            <!-- <i class="fa-solid fa-circle-user"></i> -->
                            <img src="${avatar}" alt="" srcset="">
                            <span>${username}</span>
                        </div>
                    </div>

                    <!-- Account User -->
                    <div class="account__container">
                        <div class="account__content">
                            <div class="account__information">

                                <img src="${avatar}" alt="" srcset="">
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


<div class="app">

    <c:if test="${not empty product}">
        <!-- body -->
        <div class="body">
            <div class="product__container">
                <input type="hidden" value="${product.proDetail_id}">

                <div onclick="history.back()" class="comeBack__btn">
                    <i class="fa-solid fa-circle-chevron-left"></i>
                </div>

                <div class="product__content">
                    <div class="product__img">
                        <img src="${product.pro_image}" alt="">
                    </div>

                    <div class="product__information">
                        <div class="product__name">${product.pro_name}</div>

                        <c:choose>
                            <c:when test="${empty product.pro_sale}">
                                <div class="product__price">${product.pro_price} VND</div>
                            </c:when>
                            <c:otherwise>
                                <div class="product__price">${product.pro_sale} VND</div>
                                <div class="product__sale">${product.pro_price} VND</div>
                            </c:otherwise>
                        </c:choose>

                        <div class="product__from">
                            <span>Made in</span>
                            ${product.madein}
                        </div>

                        <div class="product__from">
                            <span>Remaining</span>
                            ${quantity}
                        </div>

                        <div class="product__description">
                            ${product.description}
                        </div>

                        <div class="product__quantity">
                            <span>Quantity</span>
                            <i class="fa-solid fa-plus"></i>
                            <div class="product__num">1</div>
                            <i class="fa-solid fa-minus"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="product__footer">
                <div class="product__list">
                    <c:forEach var="detail" items="${productDetails}">
                        <div class="product_item">
                            <c:choose>
                                <c:when test="${not empty detail.proDetail_id}">
                                    <a href="./Product?id=${detail.proDetail_id}">
                                        <img src="${detail.image}" alt="">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <img src="${detail.image}" alt="">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </div>
                <div class="product__btn">
                    <button class="add__btn">Add Cart</button>
                    <button class="buy__btn">Buy Now</button>
                </div>
            </div>
        </c:if>
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

    <!--
              Username: {entry.value[0][0]}
              Avatar: {entry.value[0][1]}
              Created At: {entry.value[0][2]}
              Content: {entry.value[0][3]}
           
           
              reply_user_name: {reply[0]}
              Avatar_reply: {reply[1]}
              content_reply: {reply[3]}
              time: {reply[2]}
    -->

    <!-- Comment Page -->
    <div class="Comment__Layout">
        <div class="comment">
            <form action="./Product" method="GET" class="comment__form">
                <div class="input__comment">
                    <input type="hidden" name="id" value="${id}">
                    <input type="text" class="form__input" name="comment_input" placeholder="Type your comment...">
                    <input type="submit" class="send__btn" value="Send">
                </div>
            </form>
        </div>

        <div class="comment__title">
            <div class="cmt-header">Comments</div>
            <div class="cmt-quantity">${countMessage != null ? countMessage : 0}</div>
        </div>



        <c:forEach var="entry" items="${userComments}">
            <form action="./Product" method="GET">     
                <input type="hidden" name="id" value="${id}">
                <c:set var="proId" value="${entry.key}" />
                <c:forEach var="comment" items="${entry.value}">
                <input type="hidden" name="commentID" value="${comment[4]}">

                    <div class="comment__user">
                        <div class="cmt__user-header">
                            <div class="cmt-picture">
                                <img src="${comment[1]}" alt="User_img">
                            </div>
                            <div class="cmt__name">${comment[0]}</div>
                            <div class="cmt__date">${comment[2]}</div>
                        </div>

                        <div class="cmt-content">
                            <input name="edit-content" class="cmt__user-content" value="${comment[3]}" readonly>
                        </div>

                        <div class="reply-content">

                            <div class="edit-cmt" onclick="toggleButtons(this)">
                                <i class="edit fa-solid fa-ellipsis-vertical"></i>
                            </div>
                            <a class="edit-btn" style="display: none;" onclick="enableEdit(this)">Edit</a>
                            <button class="save" name ="btn-save" type="submit" style="display: none;" onclick="saveEdit(this)">Save</button>
                            <button class="delete-btn" name ="btn-delete" type="submit" style="display: none;">Delete</button>

                        </div>
                    </div>
                </c:forEach>
            </form>  

        </c:forEach>




    </div>



</body>

</html>
