<%-- 
    Document   : admin_users
    Created on : Sep 17, 2024, 10:20:48 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Models.Users"%>
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
                    <p class="account-name"> <%=name%></p>
                    <div class="dropdown-content">
                        <ul>
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Logout</a></li>
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
                    <% int counts = dao.GetTotalUser();%>
                    <p class="card-value"><%= counts%></p>
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
                        List<Users> searchResults = (List<Users>) request.getAttribute("searchResults");
                        List<Users> allUsers = new ArrayList<>();

                        if (searchResults != null) {
                            allUsers = searchResults;
                        } else {

                            allUsers = dao.getAllUser();
                        }

                        if (allUsers != null && !allUsers.isEmpty()) {
                            int count = 0;
                            for (Users user : allUsers) {
                                count++;
                    %>
                    <tr>
                        <td><%= count%></td>
                        <td>
                            <img src="<%= user.getAvatar()%>" alt="User Avatar" class="user-img" />
                        </td>
                        <td><%= user.getUsername()%></td>
                        <td><%= user.getEmail()%></td>
                        <td><%= user.getPhone()%></td>
                        <td><%= user.getAddress()%></td>
                        <td><%= user.getCreate_at()%></td>
                        <td>
                            <button style="background: linear-gradient(60deg, #ef5350, #e53935);" class="block-btn">
                                Block
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


        </main>
    </body>
</html>
