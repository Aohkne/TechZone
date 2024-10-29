<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Profile</title>
        <!-- CSS -->
        <link rel="stylesheet" href="/asset/css/style_admin_profile.css" />
        <!-- Fontawesome -->
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <!--JS-->
        <script
            src="/asset/js/js_admin_profile.js"
            defer
        ></script>
    </head>
    <body>
        <!-- Profile Picture, Address, and Social Media -->
        <div class="profile-card">
            <h1 class="card-title">User Profile</h1>
            <div class="profile-information">
                <a href="/Admin" class="return-to-dashboard-link"
                   >&lt; Return to Dashboard</a
                >
                <c:forEach var="userInfo" items="${user}">
                    <img
                        src="${userInfo.avatar}"
                        alt="Profile Picture"
                        class="avatar"
                        />
                </c:forEach>
                <p class="profile-username"></p>
                <p class="profile-address"></p>
            </div>
            <div class="social-media">
                <a href="https://web.facebook.com/quy.nguyentrong.3367/" target="_blank"
                   ><i class="fa-brands fa-facebook"></i
                    ></a>
                <a href=""><i class="fa-brands fa-instagram"></i></a>
                <a href=""><i class="fa-brands fa-twitter"></i></a>
                <a href=""><i class="fa-brands fa-linkedin"></i></a>
            </div>
        </div>
        <!--Admin Information Form-->
        <div class="user-information-card">
            <h1 class="card-title">User Information</h1>
            <div class="information-container">
                <form action="/Admin/Profile" method="POST" class="user-information-form">
                    <ul class="user-information-details">
                        <c:forEach var="userInfo" items="${user}">       
                            <input
                                type="hidden" value="${id}" name="user_id"/>
                            <li>
                                <label for="user-username">Username</label>
                                <input
                                    type="text"
                                    id="user-username"
                                    value="${userInfo.username}"
                                    name="username"
                                    required=""
                                    disabled
                                    oninput="updateProfileField('user-username', 'profile-username')"
                                    />
                            </li>
                            <li>
                                <label for="user-email">Email</label>
                                <input
                                    type="text"
                                    id="user-email"
                                    value="${userInfo.email}"
                                    name="email"
                                    disabled
                                    />
                            </li>
                            <li>
                                <label for="user-phone">Phone Number</label>
                                <input
                                    type="text"
                                    id="user-phone"
                                    value="${userInfo.phone}"
                                    name="phone"
                                    maxlength="10"
                                    pattern="\d{10}" 
                                    disabled
                                    />
                            </li>
                            <li>
                                <label for="user-address">Address</label>
                                <input
                                    type="text"
                                    id="user-address"
                                    value="${userInfo.address}"
                                    name="address"
                                    disabled
                                    oninput="updateProfileField('user-address', 'profile-address')"
                                    />
                            </li>
                        </c:forEach>
                    </ul>
                    <!--Admin Information Form Buttons-->
                    <button
                        type="button"
                        class="edit-profile-btn"
                        onclick="toggleEditProfile()"
                        >
                        Edit
                    </button>
                    <button
                        type="button"
                        class="cancel-edit-profile-btn"
                        onclick="cancelEditProfile()"
                        >
                        Cancel
                    </button>
                    <button type="submit" class="save-edit-profile-btn" name="btnSaveInfo">Save</button>
                </form>

                <!--Admin Password Form-->
                <form action="/Admin/Profile" method="POST" class="user-password-form" id="password-form" onsubmit="return validatePasswords();">
                    <ul class="user-password-details">
                        <input type="hidden" value="${id}" name="user_id">
                        <li class="user-password-container">
                            <label for="user-password">Password</label>
                            <input type="password" id="user-password" placeholder="***********">
                        </li>
                        <li class="user-old-password-container" style="display: none">
                            <label for="user-old-password">Old Password</label>
                            <input type="password" id="user-old-password" name="oldPass" required=""/>
                        </li>
                        <li class="user-new-password-container" style="display: none">
                            <label for="user-new-password">New Password</label>
                            <input type="password" id="user-new-password" name="newPass" minlength="6" maxlength="20" required=""/>
                        </li>
                        <li
                            class="user-confirm-new-password-container"
                            style="display: none"
                            >
                            <label for="user-confirm-new-password"
                                   >Confirm New Password</label
                            >
                            <input type="password" id="user-confirm-new-password" required=""/>
                            <span id="password-error" style="color: red; display: none;">Passwords do not match</span>
                        </li>
                    </ul>
                        
                    <!--Admin Password Form Buttons-->
                    <button
                        type="button"
                        class="change-password-btn"
                        onclick="toggleEditPassword()"
                        >
                        Change Password
                    </button>
                    <button
                        type="button"
                        class="cancel-change-password-btn"
                        onclick="cancelChangePassword()"
                        >
                        Cancel
                    </button>
                    <button type="submit" class="save-change-password-btn" name="btnNewPass">Save</button>
                </form>
            </div>
        </div>
        <script>
            document.getElementById("user-confirm-new-password").addEventListener("input", function () {
                const newPassword = document.getElementById("user-new-password").value;
                const confirmPassword = this.value;
                const errorSpan = document.getElementById("password-error");

                if (confirmPassword === "") {
                    errorSpan.textContent = "Confirm New Password cannot be empty";
                    errorSpan.style.display = "block";
                } else if (newPassword !== confirmPassword) {
                    errorSpan.textContent = "Passwords do not match";
                    errorSpan.style.display = "block";
                } else {
                    errorSpan.style.display = "none";
                }
            });

            document.getElementById("user-new-password").addEventListener("input", function () {
                const confirmPassword = document.getElementById("user-confirm-new-password").value;
                const errorSpan = document.getElementById("password-error");

                if (confirmPassword && this.value !== confirmPassword) {
                    errorSpan.textContent = "Passwords do not match";
                    errorSpan.style.display = "block";
                } else {
                    errorSpan.style.display = "none";
                }
            });
            function validatePasswords() {
                const newPassword = document.getElementById("user-new-password").value;
                const confirmPassword = document.getElementById("user-confirm-new-password").value;
                const passwordError = document.getElementById("password-error");

                // Check if passwords match
                if (newPassword !== confirmPassword) {
                    passwordError.style.display = "block";
                    passwordError.textContent = "Passwords do not match";
                    return false; // Prevent form submission
                } else {
                    passwordError.style.display = "none";
                    return true; // Allow form submission
                }
            }
        </script>
    </body>
</html>





