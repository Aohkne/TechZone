<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>Snippet - BBBootstrap</title>
        <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
        <link
            href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'
            rel='stylesheet'>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script type='text/javascript'
        src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>
            .placeicon {
                font-family: fontawesome
            }

            .custom-control-label::before {
                background-color: #007bff;
                border: #dee2e6
            }
            .titles h1 {
                text-align: center;
            }
        </style>
    </head>
    <body oncontextmenu='return false' class='snippet-body bg-info'>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
        <div>
            <!-- Container containing all contents -->
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-5">
                        <!-- White Container -->
                        <div class="container bg-white rounded mt-2 mb-2 px-0">
                            <!-- Main Heading -->
                            <div class="row justify-content-center align-items-center pt-3 titles">
                                <h1>
                                    <strong>Reset Password</strong>
                                </h1>
                            </div>
                            <div class="pt-3 pb-3">
                                <%
                                    //if (session.getAttribute("email") == null || session.getAttribute("email").equals("")) {
                                    //   response.sendRedirect("/Login");
                                    // }
                                %>
                                <form class="form-horizontal" action="Login" method="POST" enctype="multipart/form-data" id="registrationForm" onsubmit="return validateForm()">
                                    <!-- User Name Input -->
                                    <div class="form-group row justify-content-center px-3">
                                        <div class="col-9 px-0">
                                            <input type="password" name="password" placeholder="Password" required="" pattern=".{6,30}" 
                                                   title="Password must be at least 6 to 30 characters long."
                                                   class="form-control border-info placeicon">
                                        </div>
                                    </div>
                                    <!-- Password Input -->
                                    <div class="form-group row justify-content-center px-3">
                                        <div class="col-9 px-0">
                                            <input type="password" name="confPassword"
                                                   placeholder="&#xf084; &nbsp; Confirm New Password"
                                                   class="form-control border-info placeicon" required="">
                                        </div>
                                    </div>

                                    <%
                                        String loginError = (String) session.getAttribute("NewPasswordError");
                                        if (loginError != null) {
                                    %>
                                    <div class="alert alert-danger"><%= loginError%></div>
                                    <%
                                            session.removeAttribute("NewPasswordError"); // Remove error after displaying
                                        }
                                    %>
                                    <!-- Log in Button -->
                                    <div class="form-group row justify-content-center">
                                        <div class="col-3 px-3 mt-3">
                                            <input type="submit" value="Reset" name="ResetPassword"
                                                   class="btn btn-block btn-info">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
        <script>
                                    // Function to handle additional validation
                                    function validateForm() {
                                        const username = document.forms["registrationForm"]["username"].value;
                                        const email = document.forms["registrationForm"]["email"].value;

                                        // Validate that the username contains no diacritics (accents, etc.)
                                        const diacriticRegex = /^[A-Za-z0-9]+$/;
                                        if (!diacriticRegex.test(username)) {
                                            alert("Username must not contain special characters or spaces.");
                                            return false;
                                        }

                                        // Validate email confirmation - check if email is already registered or format is incorrect
                                        if (!email.includes("@") || !email.includes(".")) {
                                            alert("Please enter a valid email address.");
                                            return false;
                                        }

                                        // Additional custom validations can go here
                                        return true; // If all validations pass
                                    }
        </script>
    </body>
</html>