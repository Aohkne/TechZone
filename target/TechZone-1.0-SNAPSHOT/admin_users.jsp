<%-- 
    Document   : admin_users
    Created on : Sep 17, 2024, 10:20:48 PM
    Author     : Le Huu Khoa - CE181099
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Models.Users"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.AccountDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Users</title>
        <link rel="stylesheet" href="/asset/css/style_admin_users.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
        <script src="/asset/js/js_admin_users.js" defer></script>
        <script src="/asset/js/js_all/js_delete-button.js"></script>
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <script src="/asset/js/js_all/js_account.js" defer></script>
    </head>
    <body>
        <!-- SIDE BAR -->
        <div class="sidebar">
            <div class="logo">
                <h1>TechZone</h1>
            </div>
            <div class="list-container">
                <ul class="list">
                    <li>
                        <a href="/Admin"
                           ><i class="fa-solid fa-list"></i>Dashboard</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Product"
                           ><i class="fa-solid fa-box"></i>Products</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Category"
                           ><i class="fa-solid fa-layer-group"></i>Categories</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Users"
                           ><i class="fa-solid fa-users"></i>Users</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Review"
                           ><i class="fa-solid fa-comment"></i>Reviews</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Brand"
                           ><i class="fa-solid fa-map"></i>Brands</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Vouchers"
                           ><i class="fa-solid fa-map"></i>Vouchers</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Orders"
                           ><i class="fa-solid fa-table-list"></i>Orders</a
                        >
                    </li>
                </ul>
            </div>
            <div class="account dropdown-button">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name">${name}</p>
                    <div class="dropdown-content">
                        <ul>
                            <li><a href="/Admin/Profile">Profile</a></li>
                            <li><a href="/Logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT -->
        <main>

            <nav>
                <p class="title">Users</p>
                <div class="search-bar">
                    <form method="POST" action="/Admin/Users"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearchUser" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>

                </div>
            </nav>

            <div class="card-container">
                <div class="card">
                    <p class="card-name">Users</p>
                    <p class="card-value">${countUser}</p>                   
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                        >
                        <i class="fa-solid fa-users"></i>
                    </div>
                    <hr />
                    <p class="card-graph">Graph Details</p>
                </div>
                <div class="buttons-container">

                    <form action="/Admin/Users" method="POST"> 
                        <button
                            class="add-btn"
                            style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                            >
                            Add
                        </button>
                        <button
                            class="sort-btn"
                            style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                            name="btnSort"
                            type="submit">
                            Sort
                        </button>
                    </form>
                </div>
            </div>

            <!-- USERS TABLE -->
            <div class="users-table">
                <h1 class="table-name">USERS LIST</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Avatar</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Created Date</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <c:choose>
                        <c:when test="${not empty allUsers}">
                            <c:forEach var="user" items="${allUsers}">
                                <tr>
                                    <td>${user.user_id}</td>
                                    <td>
                                        <img src="${user.avatar}" alt="User Avatar" class="user-img" />
                                    </td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.address}</td>
                                    <td>${user.create_at}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${verifiedEmails[user.user_id] == false}">
                                                <button class="btn btn-success block-btn" id="btn-${user.user_id}"
                                                        onclick="toggleButtons(${user.user_id}, true)">
                                                    Unblock
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-danger block-btn" id="btn-${user.user_id}"
                                                        onclick="toggleButtons(${user.user_id}, false)">
                                                    Block
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="9">No users found</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>


        </main>
        <script>
            function toggleButtons(id, show) {
                var btnShow = document.getElementById("btn-show-" + id);
                var btnHide = document.getElementById("btn-hide-" + id);

                if (show) {
                    btnShow.classList.remove("hidden");
                    btnShow.classList.add("visible");
                    btnHide.classList.remove("visible");
                    btnHide.classList.add("hidden");
                } else {
                    btnShow.classList.remove("visible");
                    btnShow.classList.add("hidden");
                    btnHide.classList.remove("hidden");
                    btnHide.classList.add("visible");
                }
            }


        </script>
    </body>
</html>
