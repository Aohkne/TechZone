<%-- 
    Document   : user_profile
    Created on : Sep 26, 2024, 3:52:38 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="Models.Users"%>
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
        <link rel="stylesheet" href="./asset/css/style_user_profile.css">
        <link rel="stylesheet" href="./asset/font_themify/themify-icons-font/themify-icons/themify-icons.css">

        <!-- link js -->
        <script src="./asset/js/js_user_profile.js" defer></script>
    </head>

    <body>

        <div class="row">
            <!-- Nav -->
            <div class="nav col l-2">
                <div class="nav__container">
                    <a href="/Home">
                        <i class="return-icon ti-arrow-left"></i>
                    </a>
                    <div class="nav__Btn">
                        <img src="./asset/img/img_all/logo.png" alt="">
                    </div>
                </div>
                <div class="nav__list">
                    <li class="list__item"><a href="/User" onclick="goToProfile()">My Profile</a></li>
                    <li class="list__item"><a href="/Security" onclick="goToSecurity()">Security</a></li>

                </div>
            </div>


            <!-- Container -->
            <div class="container col l-10">
                <div class="content">
                   <% Users user = (Users) request.getAttribute("user"); %>
                    <div class="profile">
                        <div class="profile__header">
                            <h1 class="profile__title">My Profile</h1>
                            <div class="profile__edit">
                                <div class="edit-logo" onclick="enableEditMode('profile')">Edit</div>
                                <i class="edit-icon ti-pencil-alt"></i>
                            </div>
                        </div>
                        <div class="profile__container">
                            <div class="profile__picture">
                                <img src="<%= user.getAvatar() %>" alt="User_img">

                            </div> 

                            <form action="/User" method="POST" class="personal-profile__form">
                                <div class="profile__detail">
                                    <input type="hidden" name="action" value="DETAIL">
                                    <input type="text" class="profile__name" name="username" value="<%= user.getUsername()%>" readonly>
                                    <input type="text" class="profile__location" name="address" value="<%= user.getAddress()%>" readonly>
                                </div>
                            </form>

                        </div>
                    </div>


                    <div class="profile">
                        <div class="profile__header">
                            <h2 class="profile__title">Personal Infomation</h2>
                            <div class="profile__edit">
                                <div class="edit-logo" onclick="enableEditMode('personal-info')">Edit</div>
                                <i class="edit-icon ti-pencil-alt"></i>
                            </div>
                        </div>

                        <form action="/User" method="POST" class="personal-info__form">
                            <input type="hidden" name="action" value="INFO">
                            <div class="profile__information-name">
                                <div class="profile__username-title">
                                    <div class="username__title">Username</div>
                                    <input type="text" class="username" name="username" value="<%= user.getUsername()%>" readonly>
                                </div>
                            </div>

                            <div class="profile__information-email">
                                <div class="profile__email-title">
                                    <div class="email__title">Email</div>
                                    <input type="email" class="email" name="email" value="<%= user.getEmail()%>" readonly>
                                </div>
                            </div>

                            <div class="profile__information-phone">
                                <div class="profile__phone-title">
                                    <div class="phone__title">Phone</div>
                                    <input type="tel" class="phone" name="phone" value="<%= user.getPhone()%>" readonly>
                                </div>
                            </div>

                            <div class="profile__information-location">
                                <div class="profile__address-title">
                                    <div class="address__title">Address</div>
                                    <input type="text" class="address" name="address" value="<%=  user.getAddress()%>" readonly>
                                </div>
                            </div>
                        </form>

                    </div>

                </div>
            </div>

        </div>



    </body>


</html>
