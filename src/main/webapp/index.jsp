<%-- 
    Document   : index.jsp
    Created on : Sep 6, 2024, 1:17:47 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="DAOs.AccountDAO"%>
<%@page import="DAOs.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.UserDAO"%>
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
        <link rel="stylesheet" href="asset/css/css_all/style_cart.css">
        <link rel="stylesheet" href="asset/css/css_all/style_chat.css">
        <link rel="stylesheet" href="asset/css/css_all/style_accountUser.css">
        <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">


        <!-- link js -->
        <script src="./asset/js/js_index.js" defer></script>
        <script src="./asset/js/js_all/js_chat.js" defer></script>
        <script src="./asset/js/js_all/js_cart.js" defer></script>
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

                        <%
                            String idUser = "";
                            Cookie[] cookies = request.getCookies();
                            boolean isId = false;
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("id")) {
                                        idUser = cookie.getValue();
                                        UserDAO userdao = new UserDAO();
                                        ResultSet rs = userdao.getUserById(idUser);

                                        AccountDAO dao = new AccountDAO();
                                        int userId = Integer.parseInt(idUser);
                                        // Use the userId to find the user type (role)
                                        int userType = dao.getTypeById(userId);

                                        // Redirect based on the user type
                                        if (userType == 1) {
                                            response.sendRedirect("/Admin");
                                        }
                                        while (rs.next()) {
                        %>
                        <!-- Account User  -->
                        <div class="account__img">
                            <!-- <i class="fa-solid fa-circle-user"></i> -->
                            <img src=<%= rs.getString("avatar")%> alt="" srcset="">
                            <span><%= rs.getString("username")%></span>
                        </div>
                    </div>

                    <!-- Account User -->
                    <div class="account__container">
                        <div class="account__content">
                            <div class="account__information">

                                <img src=<%= rs.getString("avatar")%> alt="">
                                <div class="account__description">
                                    <div class="account__username"><%= rs.getString("username")%></div>
                                    <div class="account__mail"><%= rs.getString("email")%></div>
                                </div>
                                <%}%>
                            </div>
                            <div class="account__list">
                                <div class="account__item">
                                    <a href="user_profile.jsp" class="account__link">
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
                <a href="#">
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
                    <a class="category__btn" href="/Category?id=2">Browse</a>
                </div>

                <div class="category__item col l-2">
                    <img src="./asset/img/img_index/img_category/watch.png" alt="" class="category__img">
                    <a class="category__btn" href="/Category?id=3">Browse</a>
                </div>

                <div class="category__item col l-8">
                    <img src="./asset/img/img_index/img_category/laptop.png" alt="" class="category__img">
                    <a class="category__btn" href="/Category?id=4">Browse</a>
                </div>

                <div class="category__item col l-8">
                    <img src="./asset/img/img_index/img_category/console.png" alt="" class="category__img">
                    <a class="category__btn" href="/Category?id=5">Browse</a>
                </div>

                <div class="category__item col l-2">
                    <img src="./asset/img/img_index/img_category/vr.png" alt="" class="category__img">
                    <a class="category__btn" href="/Category?id=6">Browse</a>
                </div>

                <div class="category__item col l-2">
                    <img src="./asset/img/img_index/img_category/speaker.png" alt="" class="category__img">
                    <a class="category__btn" href="/Category?id=7">Browse</a>
                </div>

            </div>
        </div>
    </div>


    <!-- Product -->
    <div class="product__container">
        <div class="product__title">Product</div>

        <div class="product__list">
            <div class="row">
                <%
                    ProductDAO productdao = new ProductDAO();
                    ResultSet productDetail = productdao.getAllDefaultProduct();
                    String img = null;
                    String id = null;
                    String pro_id = null;
                    int countP = 0;
                    while (productDetail.next()) {
                        if (countP >= 6) {
                            break;
                        }
                        countP++;
                        img = productDetail.getString("image");
                        id = productDetail.getString("proDetail_id");
                        pro_id = productDetail.getString("pro_id");
                        ResultSet rs = productdao.getProductByIdNotSale(pro_id);
                        while (rs.next()) {
                            //Handle price to format
                            String price = rs.getString("pro_price");
                            price = price.substring(0, price.length() - 3);
                            StringBuilder result = new StringBuilder();
                            int count = 0;
                            for (int i = price.length() - 1; i >= 0; i--) {
                                if (count == 3) {
                                    result.append("." + price.charAt(i));
                                    count = 1;
                                } else {
                                    result.append(price.charAt(i));
                                    count++;
                                }
                            }
                            price = result.reverse() + "";

                %>

                <div class="product__item col l-2">
                    <a href="./user_products.jsp?id=<%= id%>">
                        <input type="hidden" value=<%= id%>>
                        <div class="product__content">
                            <div class="product__img">
                                <!--Get Image-->
                                <img src=<%= img%> alt="" class="sale__img">
                            </div>

                            <div class="product__name"><%= rs.getString("pro_name")%></div>
                            <div class="product__price"><%= price%> VND</div>
                        </div>
                    </a>

                    <div class="product__btn">
                        <i class="fa-solid fa-cart-plus"></i>
                    </div>

                </div>

                <%}
                    }%>
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

            <%
                productDetail = productdao.getAllDefaultProduct();
                img = null;
                pro_id = null;
                while (productDetail.next()) {
                    img = productDetail.getString("image");
                    pro_id = productDetail.getString("pro_id");
                    ResultSet rs = productdao.getProductByIdSale(pro_id);
                    while (rs.next()) {
                        //Handle price to format
                        String price = rs.getString("pro_price");
                        price = price.substring(0, price.length() - 3);
                        StringBuilder result = new StringBuilder();
                        int count = 0;
                        for (int i = price.length() - 1; i >= 0; i--) {
                            if (count == 3) {
                                result.append("." + price.charAt(i));
                                count = 1;
                            } else {
                                result.append(price.charAt(i));
                                count++;
                            }
                        }
                        price = result.reverse() + "";
                        //Handle sale to format
                        String sale = rs.getString("pro_sale");
                        sale = sale.substring(0, sale.length() - 3);
                        result = new StringBuilder();
                        count = 0;
                        for (int i = sale.length() - 1; i >= 0; i--) {
                            if (count == 3) {
                                result.append("." + sale.charAt(i));
                                count = 1;
                            } else {
                                result.append(sale.charAt(i));
                                count++;
                            }
                        }
                        sale = result.reverse() + "";
            %>

            <div class="sale__item">
                <img src=<%= img%> alt="" class="sale__img">
                <div class="sale__name"><%= rs.getString("pro_name")%></div>
                <div class="sale__price"><%= sale%> VND</div>
                <div class="sale__prePrice"><%= price%> VND</div>
            </div>
            <%}
                }%>

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
                <div class="chat__item user">
                    <span>???</span>
                </div>

            </div>
            <div class="chat__input">
                <input type="text" placeholder="Chat Something">
                <div class="chat__sent">
                    <i class="fa-solid fa-paper-plane"></i>
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
    ©2024 - Group 6
</div>




</body>

</html>