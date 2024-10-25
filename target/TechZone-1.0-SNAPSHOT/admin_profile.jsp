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
                <img
                    src="/asset/img/img_all/img_user/cat_stare_full.jpg"
                    alt="Profile Picture"
                    class="avatar"
                    />
                <p class="profile-username">QuyNguyen</p>
                <p class="profile-address">Can Tho, Thot Not</p>
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
                <form action="/Admin/Profile" method="GET" class="user-information-form">
                    <ul class="user-information-details">
                        <c:forEach var="userInfo" items="${user}">                          
                            <li>
                                <label for="user-username">Username</label>
                                <input
                                    type="text"
                                    id="user-username"
                                    value="${userInfo.username}"
                                    disabled
                                    />
                            </li>
                            <li>
                                <label for="user-email">Email</label>
                                <input
                                    type="text"
                                    id="user-email"
                                    value="${userInfo.email}"
                                    disabled
                                    />
                            </li>
                            <li>
                                <label for="user-phone">Phone Number</label>
                                <input
                                    type="text"
                                    id="user-phone"
                                    value="${userInfo.phone}"
                                    disabled
                                    />
                            </li>
                            <li>
                                <label for="user-address">Address</label>
                                <input
                                    type="text"
                                    id="user-address"
                                    value="${userInfo.address}"
                                    disabled
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
                    <button type="submit" class="save-edit-profile-btn">Save</button>
                </form>

                <!--Admin Password Form-->
                <form action="" class="user-password-form">
                    <ul class="user-password-details">
                        <li>
                            <label for="user-password">Password</label>
                            <input type="password" id="user-password" />
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
                    <button type="submit" class="save-change-password-btn">Save</button>
                </form>
            </div>
        </div>
    </body>
</html>





