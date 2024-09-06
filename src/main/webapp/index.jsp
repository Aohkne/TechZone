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
    <link rel="stylesheet" href="asset/css/style_index.css">
    <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">


    <!-- link js -->
    <script src="./asset/js/js_index.js" defer></script>
</head>

<body>

    <!-- Header -->
    <div class="nav">

        <!-- Nav Header -->
        <div class="nav__header">

            <ul class="nav__shopInfo">

                <li class="shopInfo__item">
                    <a class="shopInfo__link" href="">Seller</a>
                </li>

                <li class="shopInfo__item">
                    <a class="shopInfo__link" href="">Download</a>
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
                    <a class="user__link" href="">Notification</a>
                </li>

                <li class="user__item">
                    <i class="fa-solid fa-circle-question"></i>
                    <a class="user__link" href="">Help</a>
                </li>


                <div class="user__account">
                    <div class="account__register">Register</div>
                    <div class="account__login">Log in</div>
                </div>
            </ul>


        </div>

        <!-- Nav Search -->

        <div class="navSearch grid">
            <div class="row">

                <div class="navSearch__logo col l-2">
                    <img src="./asset/img/img_all/logoImage.png" alt="">
                </div>

                <div class="navSearch__search col l-8">
                    <input type="text">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>

                <div class="navSearch__cart col l-2">
                    <i class="fa-solid fa-cart-shopping"></i>
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

    <!-- Body -->
    <div class="app">


        <!-- Category -->
        <div class="category__container">
            <div class="category__title">Category</div>

            <div class="category__list">
                <div class="row">

                    <div class="category__item col l-2">
                        <img src="./asset/img/img_index/img_category/earphone.png" alt="" class="category__img">
                        <button class="category__btn">Browse</button>
                    </div>

                    <div class="category__item col l-2">
                        <img src="./asset/img/img_index/img_category/watch.png" alt="" class="category__img">
                        <button class="category__btn">Browse</button>
                    </div>

                    <div class="category__item col l-8">
                        <img src="./asset/img/img_index/img_category/laptop.png" alt="" class="category__img">
                        <button class="category__btn">Browse</button>
                    </div>

                    <div class="category__item col l-8">
                        <img src="./asset/img/img_index/img_category/console.png" alt="" class="category__img">
                        <button class="category__btn">Browse</button>
                    </div>

                    <div class="category__item col l-2">
                        <img src="./asset/img/img_index/img_category/vr.png" alt="" class="category__img">
                        <button class="category__btn">Browse</button>
                    </div>

                    <div class="category__item col l-2">
                        <img src="./asset/img/img_index/img_category/speaker.png" alt="" class="category__img">
                        <button class="category__btn">Browse</button>
                    </div>

                </div>
            </div>
        </div>


        <!-- Sale -->
        <div class="sale__container">
            <div class="sale__title">Flash sale</div>

            <div class="sale__counter">
                <span>01</span>
                <span>25</span>
                <span>30</span>
            </div>

            <div class="sale__list">

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/laptopSale.png" alt="" class="sale__img">
                    <div class="sale__name">MacBook Air 2022</div>
                    <div class="sale__price">999.999</div>
                    <div class="sale__prePrice">1.211.000</div>
                </div>

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/laptopSale.png" alt="" class="sale__img">
                    <div class="sale__name">MacBook Air 2022</div>
                    <div class="sale__price">999.999</div>
                    <div class="sale__prePrice">1.211.000</div>
                </div>


                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/vrSale.png" alt="" class="sale__img">
                    <div class="sale__name">Oculus Quest 2</div>
                    <div class="sale__price">5.999.999</div>
                    <div class="sale__prePrice">11.211.000</div>
                </div>

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/vrSale.png" alt="" class="sale__img">
                    <div class="sale__name">Oculus Quest 2</div>
                    <div class="sale__price">5.999.999</div>
                    <div class="sale__prePrice">11.211.000</div>
                </div>

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/vrSale.png" alt="" class="sale__img">
                    <div class="sale__name">Oculus Quest 2</div>
                    <div class="sale__price">5.999.999</div>
                    <div class="sale__prePrice">11.211.000</div>
                </div>

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/vrSale.png" alt="" class="sale__img">
                    <div class="sale__name">Oculus Quest 2</div>
                    <div class="sale__price">5.999.999</div>
                    <div class="sale__prePrice">11.211.000</div>
                </div>

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/vrSale.png" alt="" class="sale__img">
                    <div class="sale__name">Oculus Quest 2</div>
                    <div class="sale__price">5.999.999</div>
                    <div class="sale__prePrice">11.211.000</div>
                </div>

                <div class="sale__item">
                    <img src="./asset/img/img_index/img_sale/vrSale.png" alt="" class="sale__img">
                    <div class="sale__name">Oculus Quest 2</div>
                    <div class="sale__price">5.999.999</div>
                    <div class="sale__prePrice">11.211.000</div>
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
                            <a href="" class="icon__link">
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


            <div class="footer__contact col l-4">
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

            <div class="footer__survey col l-4">
                <div class="survey__title">Evaluate</div>
                <div class="survey__input">
                    <input type="text" placeholder="Enter Your Email">
                    <button class="survey__btn">Subcrise</button>
                </div>
            </div>

        </div>
    </div>

    <div class="footer__copyright">
        ©2024 - Group 6
    </div>




</body>

</html>