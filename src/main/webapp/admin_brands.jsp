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
            <%
                AccountDAO dao = new AccountDAO();
                BrandDAO daos = new BrandDAO();
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
                    <% int counts = daos.GetTotalBrand();%>
                    <p class="card-value"><%= counts%></p>
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
                    <%
                        List<Brand> searchResults = (List<Brand>) request.getAttribute("searchResults");
                        List<Brand> sortResults = (List<Brand>) request.getAttribute("sortResults");
                        List<Brand> allUsers = new ArrayList<>();
                        
                        int count = 0;

                        if (searchResults != null) {
                            allUsers = searchResults;
                        } else if (sortResults != null) {
                            allUsers = sortResults;
                           
                        } else {

                            allUsers = daos.GetAllBrand();
                        }

                        if (allUsers != null && !allUsers.isEmpty()) {
;
                            for (Brand user : allUsers) {
                            count++;
                            
                    %>
                    <tr>
                        <!--                        <td><input type="checkbox" /></td>-->
                        <td><%=count%></td>
                        <td><%= user.getBrand_name()%></td>
                        <td>
                            <%= user.getDescription()%>
                        </td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="edit-btn"
                                >
                                Edit</button
                            ><button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"

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
            <!-- EDIT BRAND MODAL -->
            <div id="editModal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Edit Brand</h1>
                    <span class="close-btn">&times;</span>
                    <form action="" class="brand-edit-form">
                        <label>
                            Edit brand ID
                            <input
                                type="text"
                                id="edit-brand-id"
                                placeholder="Brand ID"
                                required
                                />
                        </label>
                        <label>
                            Edit brand name
                            <input
                                type="text"
                                id="edit-brand-name"
                                placeholder="Brand name"
                                required
                                />
                        </label>
                        <label>
                            Edit brand description
                            <textarea
                                placeholder="Brand description"
                                id="edit-brand-description"
                                rows="15"
                                style="padding: 10px"
                                ></textarea>
                        </label>


                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="accept-btn"
                                >
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>








        </main>
    </body>
</html>

