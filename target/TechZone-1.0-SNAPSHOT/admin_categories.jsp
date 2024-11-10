<%-- 
    Document   : admin_categories
    Created on : Sep 17, 2024, 10:13:48 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.Category"%>
<%@page import="java.util.List"%>
<%@page import="DAOs.CategoryDAO"%>
<%@page import="DAOs.AccountDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Categories</title>
        <link rel="stylesheet" href="/asset/css/style_admin_categories.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <script src="/asset/js/js_admin_categories.js" defer></script>
        <script src="/asset/js/js_all/js_modal.js" defer></script>
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
                <p class="title">Categories</p>              
                <div class="search-bar">

                    <form method="POST" action="/Admin/Category"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearchCategory" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </nav>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">
                    ${errorMessage}
                </div>
            </c:if>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Categories</p>
                    <p class="card-value">${countCategory}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-layer-group"></i>
                    </div>
                    <hr />
                </div>

                <div class="buttons-container">
                    <button
                        class="add-btn"
                        style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                        >
                        Add
                    </button>

                    <form action="/Admin/Category" method="POST"> 
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
            <!-- CATEGORIES TABLE -->
            <div class="categories-table">
                <h1 class="table-name">CATEGORIES LIST</h1>
                <table>
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                        <th>Description</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <c:if test="${not empty allCategories}">
                        <c:forEach var="user" items="${allCategories}">
                            <tr>
                                <td>${user.cat_id}</td>
                                <td>${user.cat_name}</td>
                                <td>${user.description}</td>
                                <td>
                                    <button
                                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                        class="edit-btn"
                                        onclick="editCategory(${user.cat_id}, '${user.cat_name}', '${user.description}')"
                                        >
                                        Edit
                                    </button>
                                    <button
                                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                        name="btnDeleteCategory"
                                        onclick="showModal('delete-modal'); deleteCategory(${user.cat_id});"
                                        >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty allCategories}">
                        <tr>
                            <td colspan="4">No users found</td>
                        </tr>
                    </c:if>
                </table>
            </div>
            <!-- MODAL -->
            <div id="myModal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Add Category</h1>
                    <span class="close-btn">&times;</span>
                    <form action="/Admin/Category" class="category-form" method="post">
                        <label>
                            Enter category name
                            <input
                                type="text"
                                name="cat-name"
                                id="category-name"
                                placeholder="Category name"
                                required
                                />
                        </label>
                        <label>
                            Enter category description
                            <textarea
                                id="category-description"
                                placeholder="Category description"
                                rows="20"
                                style="padding: 10px"
                                name="description"
                                required=""
                                ></textarea>
                        </label>
                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                >
                                Cancel
                            </button>
                            <button type="submit" style="background: linear-gradient(60deg, #66bb6a, #43a047)" class="accept-btn" name="btnAddCategory">
                                Add
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Edit Modal -->
            <div id="editModal" class="modal">
                <div class="modal-content">
                    <h1>Edit Category</h1>
                    <span class="close-btn">&times;</span>
                    <form action="/Admin/Category" class="cat-edit-form" method="post">
                        <input type="hidden" id="edit-cat-id" name="cat_id"/>
                        <label>
                            Edit brand name
                            <input type="text" id="edit-cat-name" name="cat_name" />
                        </label>
                        <label>
                            Edit brand description
                            <textarea id="edit-cat-description" rows="15" style="padding: 10px" name="description"></textarea>
                        </label>

                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                type="button"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="accept-btn"
                                name="btnEditCategory"
                                >
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>
           <!-- DELETE MODAL -->
            <div id="delete-modal" class="modal">
                <div class="modal-content">
                    <h1>Confirm delete?</h1>
                    <form action="/Admin/Category" method="post" class="delete-form">
                        <input type="hidden" id="delete-cat-id" name="cat_id"/>
                        <button
                            style="background:#ffffff; color:#000000"
                            class="cancel-delete-btn"
                            type="button"
                            onclick="closeModal('delete-modal')"
                            >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            style="background: linear-gradient(60deg, #ef5350, #e53935); color:#ffffff"
                            class="confirm-delete-btn"
                            name="btnDeleteCategory"
                            >
                            Delete
                        </button>
                    </form>
                </div>
            </div>



        </main>
        <script>
            function deleteCategory(catId) {

                // Set values in modal fields
                document.getElementById('delete-cat-id').value = catId;

                const editModal = document.getElementById("deleteModal");
                editModal.style.display = "block";
            }
            function editCategory(catId, catName, catDes) {

                // Set values in modal fields
                document.getElementById('edit-cat-id').value = catId;
                document.getElementById('edit-cat-name').value = catName;
                document.getElementById('edit-cat-description').value = catDes;

                // Open the modal
                const editModal = document.getElementById("editModal");
                editModal.style.display = "block";
            }
        </script>
    </body>
</html>

