<%-- 
    Document   : index.jsp
    Created on : Sep 6, 2024, 1:17:47 PM
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


    <!-- Body -->
    <div class="app">

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

        <!-- Category -->
        <div class="category__container">
            <div class="category__title">Categories</div>

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

    </div>



</body>

</html>
