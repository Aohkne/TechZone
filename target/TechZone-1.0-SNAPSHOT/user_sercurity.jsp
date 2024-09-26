<%-- 
    Document   : user_sercurity
    Created on : Sep 26, 2024, 3:53:05 PM
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
    <link rel="stylesheet" href="./asset/css/style_user_sercurity.css">
    <link rel="stylesheet" href="./asset/font_themify/themify-icons-font/themify-icons/themify-icons.css">

    <!-- link js -->
    <script src="./asset/js/js_user_sercurity.js" defer></script>

</head>

<body>

    <div class="row">
        <!-- Nav -->
        <div class="nav col l-2">
            <div class="nav__container">
                <i class="return-icon ti-arrow-left"></i>
                <div class="nav__Btn">
                    <img src="./asset/img/img_all/logo.png" alt="">
                </div>
            </div>
            <div class="nav__list">

                <li class="list__item"><a href="./user_profile.jsp" onclick="goToProfile()">My Profile</a></li>
                <li class="list__item"><a href="#" onclick="goToSecurity()">Security</a></li>
                <li class="list__item"><a href="#" onclick="confirmDeleteAccount()">Delete Account</a></li>
            </div>
        </div>
        <!-- Container -->
        <div class="container col l-10">
            <div class="content">
                <div class="sercurity">
                    <h1 class="sercurity__header">Sercurity</h1>
                </div>
                <form action="">
                    <div class="sercurity__form">
                        <div class="sercurity__title">Old password</div>
                        <div class="sercurity__edit">
                            <input type="password" class="form__control" id="old__password">
                        </div>
                    </div>

                    <div class="sercurity__form">
                        <div class="sercurity__title__1">New password</div>
                        <div class="sercurity__edit">
                            <input type="password" class="form__control" id="new__password">
                        </div>
                    </div>

                    <div class="sercurity__form">
                        <div class="sercurity__title__2">Confirm password</div>
                        <div class="sercurity__edit">
                            <input type="password" class="form__control" id="new__password">
                        </div>
                    </div>

                    <div class="submit">
                        <button class="cancle__btn">cancle</button>
                        <button class="save__btn">save</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</body>

</html>
