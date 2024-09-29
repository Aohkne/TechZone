<%-- 
    Document   : user_profile
    Created on : Sep 26, 2024, 3:52:38 PM
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
                <a href="./index.jsp">
                    <i class="return-icon ti-arrow-left"></i>
                </a>
                <div class="nav__Btn">
                    <img src="./asset/img/img_all/logo.png" alt="">
                </div>
            </div>
            <div class="nav__list">
                <li class="list__item"><a href="#" onclick="goToProfile()">My Profile</a></li>
                <li class="list__item"><a href="./user_sercurity.jsp" onclick="goToSecurity()">Security</a></li>
                <li class="list__item"><a href="#" onclick="confirmDeleteAccount()">Delete Account</a></li>

            </div>
        </div>
        <!-- Container -->
        <div class="container col l-10">
            <div class="content">
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
                            <img src="./asset/img/img_user/defaultAvt.jpg" alt="User_img">
                        </div>
                        <div class="profile__detail">
                            <div class="profile__name">Nguyen Thanh Bao</div>
                            <div class="profile__location">Can Tho</div>
                        </div>
                    </div>
                </div>


                <div class="profile">
                    <div class="profile__header">
                        <h2 class="profile__title">Personal infomation</h2>
                        <div class="profile__edit">
                            <div class="edit-logo" onclick="enableEditMode('personal-info')">Edit</div>
                            <i class="edit-icon ti-pencil-alt"></i>
                        </div>
                    </div>
                    <div class="profile__information-name">
                        <div class="profile__username-title">
                            <div class="username__title">Username</div>
                            <div class="username">khoalonnhokl</div>
                        </div>
                        <div class="profile__username">
                        </div>
                    </div>

                    <div class="profile__information-email">
                        <div class="profile__email-title">
                            <div class="email__title">Email</div>
                            <div class="email">user123@gmail.com</div>
                        </div>
                        <div class="profile__email">
                        </div>
                    </div>

                    <div class="profile__information-phone">
                        <div class="profile__phone-title">
                            <div class="phone__title">Phone</div>
                            <div class="phone">+0945634563</div>
                        </div>
                        <div class="profile__phone">
                        </div>
                    </div>

                    <div class="profile__information-location">
                        <div class="profile__address-title">
                            <div class="address__title">Address</div>
                            <div class="address">Ninh Kieu, Can Tho</div>
                        </div>
                        <div class="profile__address">
                        </div>
                    </div>

                </div>

            </div>
        </div>

    </div>



</body>


</html>
