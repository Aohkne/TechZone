<%-- 
    Document   : admin_brands
    Created on : Sep 17, 2024, 10:04:37 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.Brand"%>
<%@page import="java.util.List"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.BrandDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Categories</title>
        <!-- Style Admin Brands -->
        <link rel="stylesheet" href="/asset/css/style_admin_brands.css" />
        <!-- Style Sidebar -->
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <!-- Scripts Admin Brands -->
        <script src="/asset/js/js_admin_brands.js" defer></script>
        <!-- Scripts Delete button -->
        <script src="/asset/js/js_all/js_delete-button.js" defer></script>
        <!-- Fontawesome icons -->
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <!-- Script Account -->
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
                </ul>
            </div>
            <div class="account dropdown-button">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name">${name}</p>
                    <div class="dropdown-content">
                        <ul>
                            <li><a href="#">Profile</a></li>
                            <li><a href="/Logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT -->
        <main>
            <nav>
                <p class="title">Brands</p>
                <div class="search-bar">
                    <form method="POST" action="/Admin/Brand"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearchBrand" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Brands</p>
                    <p class="card-value">${countBrand}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-map"></i>
                    </div>
                    <hr />
                    <p class="card-graph">Graph Details</p>
                </div>
                <div class="buttons-container">
                    <button
                        class="add-btn"
                        style="background: linear-gradient(60deg, #66bb6a, #43a047)" 
                        >
                        Add
                    </button>
                    <form action="/Admin/Brand" method="POST"> 
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
            <!-- BRANDS TABLE -->
            <div class="brands-table">
                <h1 class="table-name">BRANDS LIST</h1>
                <table>
                    <tr>
                        <!--                        <th><input type="checkbox" /></th>-->
                        <th class="id">ID</th>
                        <th class="name">Name</th>
                        <th class="description">Description</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <c:if test="${not empty allBrand}">
                        <c:forEach var="user" items="${allBrand}">
                            <tr>
                                <td>${user.brand_id}</td>
                                <td>${user.brand_name}</td>
                                <td>${user.description}</td>
                                <td>
                                    <button
                                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                        class="edit-btn"
                                        onclick="editBrand(${user.brand_id}, '${user.brand_name}', '${user.description}')"
                                        >
                                        Edit
                                    </button>
                                    <button
                                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                        name="btnDeleteBrand"
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
            <!-- MODAL -->
            <div id="myModal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Add Brand</h1>
                    <span class="close-btn">&times;</span>
                    <form action="/Admin/Brand" method="POST" class="brand-form" >
                        <label>
                            Enter brand name
                            <input
                                type="text"
                                name="brand-name"
                                id="brand-name"
                                placeholder="Brand name"
                                required
                                />
                        </label>
                        <label>
                            Enter brand description
                            <textarea
                                placeholder="Brand description"
                                name="description"
                                id="brand-description"
                                rows="15"
                                style="padding: 10px"
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
                            <button type="submit" style="background: linear-gradient(60deg, #66bb6a, #43a047)" class="accept-btn" name="btnAddBrand">
                                Add
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Edit Modal -->
            <div id="editModal" class="modal">
                <div class="modal-content">
                    <h1>Edit Brand</h1>
                    <span class="close-btn">&times;</span>
                    <form action="/Admin/Brand" class="brand-edit-form" method="post">
                        <input type="hidden" id="edit-brand-id" name="brand_id"/>
                        <label>
                            Edit brand name
                            <input type="text" id="edit-brand-name" name="brand_name" required=""/>
                        </label>
                        <label>
                            Edit brand description
                            <textarea id="edit-brand-description" rows="15" style="padding: 10px" name="description" required=""></textarea>
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
                                name="btnEditBrand"
                                >
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>







        </main>
        <script>
            function editBrand(brandId, brandName, brandDes) {

                // Set values in modal fields
                document.getElementById('edit-brand-id').value = brandId;
                document.getElementById('edit-brand-name').value = brandName;
                document.getElementById('edit-brand-description').value = brandDes;
                // Show the modal
// Open the modal
                const editModal = document.getElementById("editModal");
                editModal.style.display = "block";
            }
        </script>
    </body>
</html>
