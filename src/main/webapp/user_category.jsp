<%-- 
    Document   : user_category
    Created on : Oct 5, 2024, 7:46:37 PM
    Author     : Le Huu Khoa - CE181099
--%>

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

        <!-- Nav Search -->

        <div class="navSearch grid">
            <div class="row">

                <div class="navSearch__logo col l-2">
                    <a href="#">
                        <img src="./asset/img/img_all/logoImage.png" alt="">
                    </a>
                </div>

                <div class="navSearch__search col l-8">
                    <input type="text">
                    <i class="fa-solid fa-magnifying-glass"></i>
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

    <!-- slider -->
    <div style="background-image: url();" class="slider">
        <!-- slider img -->
        <div class=" slider-list">

            <div class="slider-item">
                <div style="background-image: url(./asset/img/img_slider/slider1.png);" class="slider-img">
                </div>
            </div>

            <div class="slider-item">
                <div style="background-image: url(./asset/img/img_slider/slider2.png);" class="slider-img">
                </div>
            </div>
        </div>

        <!-- slider button -->
        <div class="slider-button">
            <button id="button_pre">
                < </button>
                    <button id="button_next">></button>
        </div>

        <!-- sldier dots -->
        <ul class="slider-dot">
            <li class="dot-item__active"></li>
            <li class="dot-item"></li>
            <li class="dot-item"></li>
            <li class="dot-item"></li>
            <li class="dot-item"></li>
        </ul>

    </div>

    <!-- body -->
    <div class="app">
        <div class="content">
            <!-- Nav__content -->
            <div class="nav__content">
                <div class="nav__list-title">Sort by</div>
                <div class="nav__list">Popular</div>
                <div class="nav__list">Latest</div>
                <div class="nav__list">Best seller</div>
            </div>

            <!-- Cate__content -->
            <div class="cate__content">
                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>


                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>

                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>

                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>

                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>
            </div>
            <div class="cate__content">
                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>


                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>

                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>

                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>

                <div class="cate__list">
                    <a href="./user_products.jsp" class="cate__items">
                        <div class="cate__title">
                            <div class="cate__img">
                                <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" alt="">
                            </div>
                            <div class="content__list">
                                <div class="cate__name">Name</div>
                                <div class="cate__price">485.000 VND</div>
                                <div class="cate__discount">1.000.000</div>
                            </div>
                        </div>
                    </a>
                    <div class="order__list">
                        <i class="order__icon fa-solid fa-cart-plus"></i>
                    </div>
                </div>
            </div>

            <!-- Page trainsition bar -->
            <div class="page__bar">
                <a href="#" id="prev">&laquo;</a>
                <a href="#" class="page-num active" data-page="1">1</a>
                <a href="#" class="page-num" data-page="2">2</a>
                <a href="#" class="page-num" data-page="3">3</a>
                <a href="#" class="page-num" data-page="4">4</a>
                <a href="#" class="page-num" data-page="5">5</a>
                <a href="#" class="page-num" data-page="6">6</a>
                <a href="#" id="next">&raquo;</a>
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
                    <a href="./feedback.jsp">
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
