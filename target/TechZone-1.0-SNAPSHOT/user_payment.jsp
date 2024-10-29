<%-- 
    Document   : user_payment.jsp
    Created on : Oct 26, 2024, 7:22:00 PM
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
    <link rel="stylesheet" href="asset/css/style_user_payment.css">
    <link rel="stylesheet" href="asset/font/fontawesome-free-6.4.2-web/css/all.min.css">


    <!-- link js -->
    <script src="asset/js/js_user_payment.js" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
</head>

<body onload="autoClick();">

    <div class="app">
        <!-- Left -->
        <div class="leftContainer col l-8">
            <div class="container">
                <div class="leftContainer__logo">
                    <a href="/"> <img src="./asset/img/img_all/logoImage.png" /></a>
                    <div class="leftContainer__title">
                        YOUR ORDER
                    </div>
                </div>

                <div class="leftContainer__list">

                    <div class="leftContainer__item">
                        <div class="leftContainer__img">
                            <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" />
                        </div>
                        <div class="leftContainer__content">
                            <div class="leftContainer__name">Air pod pro</div>
                            <div class="leftContainer__description">description</div>
                        </div>
                        <div class="leftContainer__price"><span>1 x </span>300.000 VND</div>
                    </div>
                    <div class="leftContainer__item">
                        <div class="leftContainer__img">
                            <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" />
                        </div>
                        <div class="leftContainer__content">
                            <div class="leftContainer__name">Air pod pro</div>
                            <div class="leftContainer__description">description</div>
                        </div>
                        <div class="leftContainer__price"><span>1 x </span>300.000 VND</div>
                    </div>
                    <div class="leftContainer__item">
                        <div class="leftContainer__img">
                            <img src="./asset/img/img_all/img_product/img_laptop/laptop.png" />
                        </div>
                        <div class="leftContainer__content">
                            <div class="leftContainer__name">Air pod pro</div>
                            <div class="leftContainer__description">description</div>
                        </div>
                        <div class="leftContainer__price"><span>1 x </span>300.000 VND</div>
                    </div>
                </div>


                <div class="leftContainer__totalPrice">
                    <div class="totalPrice__title">TOTAL</div>
                    <div class="totalPrice__content">1.500.000 VND</div>
                </div>
            </div>

        </div>

        <!-- Right -->
        <div class="rightContainer col l-4">
            <div class="container">
                <div class="rightContainer__title">Payment</div>
                <div class="rightContainer__user">
                    <i class="fa-solid fa-user"></i>
                    <span>${username}</span>
                </div>
                <div class="rightContainer__address">
                    <i class="fa-solid fa-location-dot"></i>
                    <span>${address}</span>
                </div>
            </div>

            <div class="rightContainer__feature">
                <a value="Cash" class="btn">
                    <i class="fa-solid fa-money-bill-1-wave"></i>
                    Cash
                </a>
                <a value="Credit" class="btn active">
                    <i class="fa-solid fa-qrcode"></i>
                    Credit
                </a>
            </div>

            <div class="rightContainer__img">
                <img src="./asset/img/img__payment/qr.png" />
            </div>

            <div class="rightContainer__action">
                <a href="/" class="payBtn">PAYMENT</a>
                <a class="saveBtn">SAVE PNG</a>
            </div>


        </div>
    </div>

    <!-- Hmtl to PNG -->
    <script>
        function autoClick() {
            $(".saveBtn").click();
        }
        $(document).ready(function () {
            var element = $(".app");

            $(".saveBtn").on('click', function () {

                html2canvas(element, {
                    onrendered: function (canvas) {
                        var imageData = canvas.toDataURL("image/png");
                        var newData = imageData.replace(/^data:image\/png/, "data:application/octet-stream");
                        $(".saveBtn").attr("download", "your-order.png").attr("href", newData);
                    }
                });
            });
        });
    </script>

</body>

</html>
