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
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Categories</title>
        <link rel="stylesheet" href="/asset/css/style_admin_categories.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <script src="/asset/js/js_admin_categories.js" defer></script>
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
                </ul>
            </div>
            <%
                CategoryDAO daos = new CategoryDAO();
                AccountDAO dao = new AccountDAO();
                int userId = -1;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("id")) {
                            userId = Integer.parseInt(cookie.getValue());
                            break;
                        }
                    }
                }
                String name = dao.GetNameAdmin(userId);
                System.out.println(name);
                System.out.println(userId);
            %>
            <div class="account dropdown-button">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name"><%=name%></p>
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
                <p class="title">Categories</p>              
                <div class="search-bar">
                    
                    <form method="POST" action="/Admin/Brand"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearchCategory" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Categories</p>
                    <% int counts = daos.GetTotalCategory();%>
                    <p class="card-value"><%= counts %></p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-layer-group"></i>
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
                    <%
                        List<Category> searchResults = (List<Category>) request.getAttribute("searchResults");
                        List<Category> sortResults = (List<Category>) request.getAttribute("sortResults");
                        List<Category> allUsers = new ArrayList<>();

                        if (searchResults != null) {
                            allUsers = searchResults;
                        } else if (sortResults != null) {
                            allUsers = sortResults;

                        } else {

                            allUsers = daos.GetAllCategory();
                        }

                        if (allUsers != null && !allUsers.isEmpty()) {
                            for (Category user : allUsers) {

                    %>
                    <tr>
                        <td><%= user.getCat_id()%></td>
                        <td><%= user.getCat_name()%></td>
                        <td>
                            <%= user.getDescription()%>
                        </td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="edit-btn"
                                onclick="editCategory(<%= user.getCat_id()%>, '<%= user.getCat_name()%>', '<%= user.getDescription()%>')"
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
                   <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="9">No users found</td>
                    </tr>
                    <%
                        }
                    %>
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
                                rows="15"
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
             
             
             
             
        </main>
                <script>
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

