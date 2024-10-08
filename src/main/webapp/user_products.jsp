<%-- 
    Document   : user_products
    Created on : Sep 29, 2024, 9:37:36 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="DAOs.UserDAO"%>
<%@page import="DAOs.BrandDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
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
                <a href="./index.jsp">
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

    <%
        ProductDAO productdao = new ProductDAO();
        String id = (String) request.getParameter("id");
        ResultSet rs = productdao.getProductById(id);
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

    <!-- body -->
    <div class="body">

        <div class="product__container">
            <input type="hidden" value=<%= rs.getString("pro_id")%>>

            <div onclick="history.back()" class="comeBack__btn">
                <i class="fa-solid fa-circle-chevron-left"></i>
            </div>

            <div class="product__content">
                <div class="product__img">
                    <img src=<%= rs.getString("pro_image")%> alt="">
                </div>

                <div class="product__information">
                    <div class="product__name"><%= rs.getString("pro_name")%></div>

                    <%
                        if (rs.getString("pro_sale") == null) {
                    %>
                    <div class="product__price"><%= price%> VND</div>
                    <%
                    } else {
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
                    <div class="product__price"><%= sale%> VND</div>
                    <div class="product__sale"><%= price%> VND</div>
                    <%}%>

                    <%
                        BrandDAO branddao = new BrandDAO();
                        ResultSet brandList = branddao.getBrandById(rs.getString("brand_id"));
                        while (brandList.next()) {
                    %>
                    <div class="product__brand">
                        <span>Brand</span>
                        <%= brandList.getString("brand_name")%>
                    </div>
                    <%}%>

                    <div class="product__from">
                        <span>Made in</span>
                        <%= rs.getString("madein")%>
                    </div>

                    <div class="product__description">
                        <%= rs.getString("description")%>
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
                <div class="product_item">
                    <img src="<%= rs.getString("pro_image")%>" alt="">
                </div>
                <div class="product_item">
                    <img src="<%= rs.getString("pro_image")%>" alt="">
                </div>
                <div class="product_item">
                    <img src="<%= rs.getString("pro_image")%>" alt="">
                </div>
                <div class="product_item">
                    <img src="<%= rs.getString("pro_image")%>" alt="">
                </div>

            </div>

            <div class="product__btn">
                <button class="add__btn">Add Cart</button>
                <button class="buy__btn">Buy Now</button>
            </div>
        </div>
    </div>

    <%}%>


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

</body>

</html>
