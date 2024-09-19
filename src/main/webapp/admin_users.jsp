<%-- 
    Document   : admin_users
    Created on : Sep 17, 2024, 10:20:48 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.AccountDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Users</title>
        <link rel="stylesheet" href="/asset/css/style_admin_users.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <script src="/asset/js/js_admin_users.js" defer></script>
        <script src="/asset/js/js_all/js_delete-button.js"></script>
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
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
                        <a href="admin_dashboard.jsp"
                           ><i class="fa-solid fa-list"></i>Dashboard</a
                        >
                    </li>
                    <li>
                        <a href="admin_products.jsp"
                           ><i class="fa-solid fa-box"></i>Products</a
                        >
                    </li>
                    <li>
                        <a href="admin_categories.jsp"
                           ><i class="fa-solid fa-layer-group"></i>Categories</a
                        >
                    </li>
                    <li>
                        <a href="admin_users.jsp"
                           ><i class="fa-solid fa-users"></i>Users</a
                        >
                    </li>
                    <li>
                        <a href="admin_reviews.jsp"
                           ><i class="fa-solid fa-comment"></i>Reviews</a
                        >
                    </li>
                    <li>
                        <a href="admin_brands.jsp"
                           ><i class="fa-solid fa-map"></i>Brands</a
                        >
                    </li>
                </ul>
            </div>
            <div class="account">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name">Nguyen Trong Quy</p>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT -->
        <main>
            <nav>
                <p class="title">Users</p>
                <div class="search-bar">
                    <input type="text" placeholder="Search" /><i
                        class="fa-solid fa-magnifying-glass"
                        ></i>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Users</p>
                    <p class="card-value">5</p>
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
                    <button style="background: linear-gradient(60deg, #ef5350, #e53935)">
                        Block
                    </button>
                    <button style="background: linear-gradient(60deg, #ffa726, #fb8c00)">
                        Sort
                    </button>
                </div>
            </div>
            <!-- USERS TABLE -->
            <div class="users-table">
                <h1 class="table-name">USERS LIST</h1>
                <table>
                    <tr>
                        <th><input type="checkbox" /></th>
                        <th>ID</th>
                        <th>Avatar</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Created Date</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <%
                        AccountDAO dao = new AccountDAO();
                        ResultSet rs = dao.getAllUser();
                        int count = 0;
                        while (rs.next()) {
                        count++;
                            
                            
                    %>
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td><%= count%></td>
                        <td>
                            <img
                                src="<%=rs.getString("avatar") %>";
                                alt="User Avatar"
                                class="user-img"
                                />
                        </td>
                        <td><%= rs.getString("username")%></td>
                        <td><%= rs.getString("email")%></td>
                        <td><%= rs.getInt("phone")%></td>
                        <td><%= rs.getString("address")%></td>
                        <td><%= rs.getDate("create_at") %></td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                >
                                Block
                            </button>
                        </td>
                    </tr>



                    <%
                        }
                    %>
                </table>
            </div>
        </main>
    </body>
</html>


