<%-- 
    Document   : admin_reviews
    Created on : Sep 17, 2024, 10:18:42 PM
    Author     : Le Huu Khoa - CE181099
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="DAOs.AccountDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Comments</title>
        <link rel="stylesheet" href="/asset/css/style_admin_reviews.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_delete_modal.css">
        <script src="/asset/js/js_admin_reviews.js" defer></script>
        <script src="/asset/js/js_all/js_modal.js" defer></script>
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <script src="/asset/js/js_admin_reviews.js"></script>
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
                           ><i class="fa-solid fa-comment"></i>Comments</a
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
                    <li>
                        <a href="/Admin/Chat"
                           ><i class="fa-solid fa-table-list"></i>Chat</a
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
                <p class="title">Comments</p>
                <div class="search-bar">
                    <form method="POST" action="/Admin/Review"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearch" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Comments</p>
                    <p class="card-value">${countBrand}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                        >
                        <i class="fa-solid fa-comment"></i>
                    </div>
                    <hr />
                </div>
                <div class="buttons-container">
                    <form action="/Admin/Review" method="POST"> 
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
            <!-- REVIEWS TABLE -->
            <div class="reviews-table">
                <h1 class="table-name">COMMENTS LIST</h1>
                <table>
                    <tr>
                        <th class="rev">ID</th>
                        <th class="author">Content</th>
                        <th class="product-name">Date</th>
                        <th class="posted-date">Product</th>
                        <th class="posted-date">Name</th>
                        <th class="operations">Operations</th>
                    </tr>

                    <c:if test="${not empty allBrand}">
                        <c:forEach var="user" items="${allBrand}">
                            <tr>
                                <td>${user.comment_id}</td>
                                <td>${user.contents}</td>
                                <td>${user.create_at}</td>
                                <td>${user.product_name}</td>
                                <td>${user.username}</td>

                                <td>
                                    <button
                                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                        name=""
                                        type="button"
                                        class="delete-btn"
                                        onclick="showModal('delete-modal'); deleteCmt(${user.comment_id});"
                                        >
                                        Delete
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty allBrand}">
                        <tr>
                            <td colspan="4">No users found</td>
                        </tr>
                    </c:if>
                </table>
            </div>
            <!-- DELETE MODAL -->
            <div id="delete-modal" class="modal">
                <div class="modal-content">
                    <h1>Confirm delete?</h1>
                    <form action="/Admin/Review" method="post" class="delete-form">
                        <!-- Update the hidden input field to match the brand ID -->
                        <input type="hidden" id="delete-brand-id" name="comment_id"/>
                        <button
                            style="background:#ffffff; color:#000000"
                            class="cancel-btn"
                            type="button"
                            onclick="closeModal('delete-modal')">
                            Cancel
                        </button>
                        <button
                            type="submit"
                            style="background: linear-gradient(60deg, #ef5350, #e53935); color:#ffffff"
                            class="confirm-delete-btn"
                            name="btnDeleteComment">
                            Delete
                        </button>
                    </form>
                </div>
            </div>

        </main>
        <script>
            function deleteCmt(commentId) {
                // Set the value in the hidden input field
                document.getElementById('delete-brand-id').value = commentId;

                // Display the modal
                const deleteModal = document.getElementById('delete-modal');
                deleteModal.style.display = 'block';
            }
        </script>
    </body>
</html>

