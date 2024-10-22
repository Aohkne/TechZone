<%-- 
    Document   : user_cart.jsp
    Created on : Sep 26, 2024, 3:52:07 PM
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
        <link rel="stylesheet" href="asset/css/style_user_cart.css">
        <link rel="stylesheet" href="asset/css/css_all/style_accountUser.css">
        <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">

        <!-- link js -->
        <script src="./asset/js/js_user_cart.js" defer></script>
        <script src="./asset/js/js_all/js_accountUser.js" defer></script>
    </head>

    <body>


        <div class="app">
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

                                    <div class="notification__item">
                                        <div class="notification__info">
                                            <div class="notification__heading">Order successful</div>
                                            <div class="notification__description">Order ipad 11 pro successfully!</div>
                                        </div>

                                        <div class="notification__time">24/08</div>
                                    </div>


                                    <div class="notification__item">
                                        <div class="notification__info">
                                            <div class="notification__heading">Order successful</div>
                                            <div class="notification__description">Order ipad 11 pro successfully!</div>
                                        </div>

                                        <div class="notification__time">24/08</div>
                                    </div>


                                </div>
                            </div>


                        </li>

                        <li class="user__item">
                            <i class="fa-solid fa-circle-question"></i>
                            <a class="user__link" href="">Help</a>
                        </li>


                        <div class="user__account">
                            <!-- <div class="account__register">Register</div>
                            <div class="account__login">Log in</div> -->

                            <!-- Account User  -->
                            <div class="account__img">
                                <!-- <i class="fa-solid fa-circle-user"></i> -->
                                <img src="./asset/img/img_all/img_user/chihuahua.jpg" alt="" srcset="">
                                <span>Le Huu Khoa</span>
                            </div>

                        </div>
                        <!-- Account User -->
                        <div class="account__container">
                            <div class="account__content">
                                <div class="account__information">
                                    <img src="./asset/img/img_all/img_user/chihuahua.jpg" alt="">
                                    <div class="account__description">
                                        <div class="account__username">Le Huu Khoa</div>
                                        <div class="account__mail">khoa@gmail.com</div>
                                    </div>
                                </div>

                                <div class="account__list">

                                    <div class="account__item">
                                        <a href="./user_profile.jsp" class="account__link">
                                            My Profile
                                        </a>
                                    </div>
                                    <div class="account__item">
                                        <a href="#" class="account__link">
                                            Order History
                                        </a>
                                    </div>
                                    <div class="account__item">
                                        <a href="/Voucher" class="account__link">
                                            Voucher
                                        </a>
                                    </div>
                                    <div class="account__item">
                                        <a href="#" class="account__link">
                                            Shipping
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


                    </ul>


                </div>

                <!-- Nav Title -->

                <div class="navTitle grid">
                    <div class="row">

                        <div class="navTitle__logo col l-2">
                            <a href="/Home">
                                <img src="./asset/img/img_all/logoImage.png" alt="">
                            </a>
                        </div>

                        <div class="navTitle__title col l-8">
                            CART
                        </div>

                    </div>
                </div>
            </div>


            <!-- Body -->
            <div class="cart__container grid">

                <div class="row">

                    <div class="cart__content col l-12">

                        <div class="row">
                            <div class="cart__icon col l-1">
                                <input type="checkbox" name="" id="">
                            </div>

                            <div class="cart__title col l-4">
                                Product
                            </div>

                            <div class="cart__title col l-2">
                                Price
                            </div>

                            <div class="cart__title col l-2">
                                Quantity
                            </div>

                            <div class="cart__title col l-2">
                                Total
                            </div>

                            <div class="cart__title col l-1">
                                Action
                            </div>

                        </div>


                    </div>

                    <div class="cart__list col l-12">
                        <div class="row">

                            <div class="cart__item col l-12">
                                <!-- <div class="img__EmptyCart">
                                    <img src="./asset/img/img_all/img_cart/emptyCart.png" alt="">
                                </div> -->
                                <div class="cart_content">
                                    <div class="row">
                                        <input type="hidden" value="P1">
                                        <div class="cart__icon col l-1">
                                            <input type="checkbox" name="" id="">
                                        </div>

                                        <div class="cart__title col l-4">

                                            <div class="title__content">
                                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                                                <div class="cart__name">
                                                    Air pod pro
                                                    <!-- <div class="cart__description">
                                                        Color: White
                                                    </div> -->
                                                </div>

                                            </div>
                                        </div>

                                        <div class="cart__title col l-2">
                                            300. 000 VND
                                        </div>

                                        <div class="cart__title col l-2">
                                            <div class="cart__quantity">
                                                <i class="fa-solid fa-plus"></i>
                                                <div class="cart__num">1</div>
                                                <i class="fa-solid fa-minus"></i>
                                            </div>
                                        </div>

                                        <div class="cart__title col l-2">
                                            300. 000 VND
                                        </div>

                                        <div class="cart__title col l-1">
                                            <button>Delete</button>
                                        </div>

                                    </div>
                                </div>

                                <div class="cart__voucher">
                                    <img src="./asset/img/img_all/img_cart/voucher_rare.png" alt="">
                                    <span>Voucher</span>
                                </div>

                            </div>


                        </div>


                    </div>

                    <!-- Voucher -->
                    <div class="voucher_wrapper">
                        <div class="voucher_container">

                            <!-- Header -->
                            <div class="voucher_header">
                                <div class="voucher__title">Voucher</div>

                                <div class="voucher__typeList">
                                    <div class="voucher__typeItem">
                                        <img src="./asset/img/img_all/img_cart/voucher_normal.png" alt="normal">
                                        <span>Normal</span>
                                    </div>
                                    <div class="voucher__typeItem">
                                        <img src="./asset/img/img_all/img_cart/voucher_medium.png" alt="medium">
                                        <span>Medium</span>
                                    </div>
                                    <div class="voucher__typeItem">
                                        <img src="./asset/img/img_all/img_cart/voucher_rare.png" alt="rare">
                                        <span>Rare</span>
                                    </div>
                                </div>
                            </div>

                            <!-- Body -->
                            <div class="voucher__body">
                                <div class="voucher__list">

                                    <c:if test="${empty voucher}">
                                        <p>No vouchers available.</p>
                                    </c:if>
                                    <c:if test="${not empty voucher}">
                                        <c:forEach var="v" items="${voucher}">
                                            <div class="voucher__item
                                                 <c:choose>
                                                     <c:when test="${v.voucher_id == 1}">
                                                         gray
                                                     </c:when>
                                                     <c:when test="${v.voucher_id == 2}">
                                                         green
                                                     </c:when>
                                                     <c:when test="${v.voucher_id == 3}">
                                                         yellow
                                                     </c:when>
                                                     <c:otherwise>
                                                         default-color
                                                     </c:otherwise>
                                                 </c:choose>">

                                                <input type="hidden" value="${v.voucherDetail_id}">

                                                <div class="voucher__itemImg">
                                                    <img src="${v.voucher_img}" alt="voucher image">
                                                </div>

                                                <div class="voucher__content">
                                                    <div class="voucher__description">
                                                        <div class="voucher__name">
                                                            <span>${v.voucher_name}</span>
                                                            <span class="voucher__quantity">x${v.voucher_quantity}</span>
                                                        </div>
                                                        <div class="voucher__expiry">
                                                            Expiry: ${v.voucher_expire_date}
                                                        </div>
                                                    </div>

                                                    <div class="voucher__action">
                                                        <input type="radio" name="voucher">
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                    <!--<div class="voucher__item yellow">
                                        <input type="hidden" value="1">
                                        <input type="hidden" value="yellow">
                                        <div class="voucher__itemImg">
                                            <img src="./asset/img/img_all/img_cart/voucher_rare.png" alt="normal">
                                        </div>

                                        <div class="voucher__content">
                                            <div class="voucher__description">
                                                <div class="voucher__name">
                                                    <span>
                                                        Name Voucher
                                                    </span>
                                                    <span class="voucher__quantity">x1</span>
                                                </div>

                                                <div class="voucher__expiry">
                                                    Expiry: 23-11-2024
                                                </div>
                                            </div>

                                            <div class="voucher__action">
                                                <input type="radio" name="voucher">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="voucher__item green">
                                        <input type="hidden" value="2">
                                        <input type="hidden" value="green">
                                        <div class="voucher__itemImg">
                                            <img src="./asset/img/img_all/img_cart/voucher_medium.png" alt="normal">
                                        </div>

                                        <div class="voucher__content">
                                            <div class="voucher__description">
                                                <div class="voucher__name">
                                                    <span>
                                                        Name Voucher
                                                    </span>
                                                    <span class="voucher__quantity">x1</span>
                                                </div>

                                                <div class="voucher__expiry">
                                                    Expiry: 23-11-2024
                                                </div>
                                            </div>

                                            <div class="voucher__action">
                                                <input type="radio" name="voucher">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="voucher__item gray">
                                        <input type="hidden" value="3">
                                        <input type="hidden" value="gray">
                                        <div class="voucher__itemImg">
                                            <img src="./asset/img/img_all/img_cart/voucher_normal.png" alt="normal">
                                        </div>

                                        <div class="voucher__content">
                                            <div class="voucher__description">
                                                <div class="voucher__name">
                                                    <span>
                                                        Name Voucher
                                                    </span>
                                                    <span class="voucher__quantity">x1</span>
                                                </div>

                                                <div class="voucher__expiry">
                                                    Expiry: 23-11-2024
                                                </div>
                                            </div>

                                            <div class="voucher__action">
                                                <input type="radio" name="voucher">
                                            </div>
                                        </div>

                                    </div>-->

                                </div>
                            </div>

                            <!-- Footer -->

                            <div class="voucher__footer">
                                <div class="voucher__btn">
                                    <button>Apply</button>
                                </div>
                            </div>

                        </div>
                    </div>


                    <div class="cart__footer">
                        <div class="cart__total">
                            Total:
                            <span>300.000 VND</span>
                        </div>

                        <div class="cart__btn">
                            <button>Order Now</button>
                        </div>
                    </div>


                </div>
            </div>

        </div>

    </body>

</html>