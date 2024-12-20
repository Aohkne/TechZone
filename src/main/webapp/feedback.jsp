<%-- 
    Document   : feedback
    Created on : Sep 17, 2024, 10:27:30 PM
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
        <link rel="stylesheet" href="./asset/css/base.css">
        <link rel="stylesheet" href="./asset/css/grid.css">
        <link rel="stylesheet" href="./asset/css/style_feedback.css">
        <link rel="stylesheet" href="./asset/font/fontawesome-free-6.4.2-web/css/all.min.css">


        <!-- link js -->
        <script src="./asset/js/js_feedback.js" defer></script>
    </head>

    <body>
        <!-- Header -->
        <div class="nav">
            <div class="nav__logo">
                <a href="/Home">
                    <img src="./asset/img/img_all/logoImage.png" alt="">
                </a>
            </div>
            <div class="nav__title">Feedback</div>
        </div>

        <!-- Body -->
        <div class="feedback__container">
            <div class="feedback__title">Enhance Your Shopping Experience with Your Feedback</div>
            <div class="feedback__description">Join our survey and share your thoughts to help us serve you better!</div>

            <div class="feedback__content">
                <div class="row">

                    <div class="form__item col l-6 m-6 c-12">
                        <div class="form__container">
                            <input type="text" class="form__input" required="required">
                            <div class="label-title">Name</div>
                        </div>
                        <span class="label-message"></span>
                    </div>

                    <div class="form__item col l-6 m-6 c-12">
                        <div class="form__container">
                            <input type="text" class="form__input" required="required">
                            <div class="label-title">Email</div>
                        </div>
                        <span class="label-message"></span>
                    </div>


                    <div class="form__item col l-12 m-12 c-12">
                        <div class="form__container">
                            <textarea class="form__input" rows="5" required="required"></textarea>
                            <div class="label-title">Detail</div>
                        </div>
                        <span class="label-message"></span>
                    </div>


                    <div class="btn__feedback col l-12 m-12 c-12">
                        <button>SEND</button>
                    </div>

                </div>

            </div>
        </div>


    </body>

</html>
