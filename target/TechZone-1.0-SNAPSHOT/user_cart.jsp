<%-- 
    Document   : user_cart.jsp
    Created on : Sep 26, 2024, 3:52:07 PM
    Author     : Le Huu Khoa - CE181099
--%>

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
                            <!--<div class="account__register">Register</div>
                                <div class="account__login">Log in</div>-->

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
                                        <a href="#" class="account__link">
                                            Shipping
                                        </a>
                                    </div>
                                    <div class="account__item">
                                        <a href="./user_cart.jsp" class="account__link">
                                            Cart
                                        </a>
                                    </div>
                                </div>
                                <div class="account__logout">
                                    <a>Log out</a>
                                </div>
                            </div>
                        </div>


                    </ul>


                </div>

                <!-- Nav Title -->

                <div class="navTitle grid">
                    <div class="row">

                        <div class="navTitle__logo col l-2">
                            <a href="./index.jsp">
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
                                <div class="img__EmptyCart">
                                    <img src="./asset/img/img_all/img_cart/emptyCart.png" alt="">
                                </div>
                                <!-- <div class="row">
                                    <input type="hidden" value="P1">
                                    <div class="cart__icon col l-1">
                                        <input type="checkbox" name="" id="">
                                    </div>
    
                                    <div class="cart__title col l-4">
    
                                        <div class="title__content">
                                            <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                                            <div class="cart__name">
                                                Air pod pro
                                                <div class="cart__description">
                                                    Color: White
                                                </div>
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
    
                                </div> -->

                            </div>


                        </div>


                    </div>


                    <div class="cart__footer">
                        <!-- <div class="row">
                            <div class="cart__voucher l-7">
                                <img src="./asset/img/img_all/img_cart/voucher.png" alt="">
                                <span>Voucher</span>
                            </div>
    
                            <div class="cart__total l-3">
                                Total:
                                <span>300.000 VND</span>
                            </div>
    
                            <div class="cart__btn l-2">
                                <button>Order Now</button>
                            </div>
                        </div> -->
                    </div>


                </div>
            </div>

        </div>

    </body>

</html>