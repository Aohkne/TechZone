<%-- 
    Document   : login
    Created on : 06-Sep-2024, 21:43:52
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="asset/css/login.css" rel="stylesheet" type="text/css"/>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
            />
    </head>
    <body>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="Login" method="post" enctype="multipart/form-data" id="registrationForm" onsubmit="return validateForm()">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="text" name="username" placeholder="Name" required=""  pattern="[A-Za-z0-9]+" 
                           title="Username must contain only letters and numbers without spaces." />
                    <input type="email" name="email" placeholder="Email" required=""/>
                    <input type="password" name="password" placeholder="Password" required="" pattern=".{6,30}" 
                           title="Password must be at least 6 to 30 characters long."/>

                    <button type="submit" name="btnAddNew">Sign Up</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="Login" method="post">
                    <%
                        String loginError = (String) session.getAttribute("loginError");
                        if (loginError != null) {
                    %>
                    <div class="alert alert-danger"><%= loginError%></div>
                    <%
                            session.removeAttribute("loginError"); // Remove error after displaying
                        }
                    %>
                    <br>
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input type="email" name="email" placeholder="Email" required=""/>
                    <input type="password" name="password" placeholder="Password" required=""/>
                    <label for="remember" class="checkbox-label">
                        <input type="checkbox" name="remember" id="remember" value="true" />
                        <span class="checkmark"></span>
                        <a>Save Information</a>
                    </label>
                    <a href="#">Forgot your password?</a>
                    <button type="submit" name="btnLogin">Sign In</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>
                            To keep connected with us please login with your personal info
                        </p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>

        <footer>
            <p>
                Created with <i class="fa fa-heart"></i> by
                <a target="_blank" href="https://florin-pop.com">Florin Pop</a>
                - Read how I created this and how you can join the challenge
                <a
                    target="_blank"
                    href="https://www.florin-pop.com/blog/2019/03/double-slider-sign-in-up-form/"
                    >here</a
                >.
            </p>
        </footer>
        <script>
            const signUpButton = document.getElementById('signUp');
            const signInButton = document.getElementById('signIn');
            const container = document.getElementById('container');

            signUpButton.addEventListener('click', () => {
                container.classList.add('right-panel-active');
            });

            signInButton.addEventListener('click', () => {
                container.classList.remove('right-panel-active');
            });


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
