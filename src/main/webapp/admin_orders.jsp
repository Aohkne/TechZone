<%-- 
    Document   : admin_orders
    Created on : Nov 3, 2024, 1:51:29 PM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Orders</title>
        <!-- Style admin orders -->
        <link rel="stylesheet" href="/asset/css/style_admin_orders.css" />
        <!-- Style sidebar -->
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <!-- Script Fontawesome -->
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <!-- Script account -->
        <script src="/asset/js/js_all/js_account.js" defer></script>
        <script src="/asset/js/js_admin_orders.js" defer></script>
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
                           ><i class="fa-solid fa-ticket"></i>Vouchers</a
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
                <p class="title">Products</p>
                <div class="search-bar">
                    <form method="POST" action="/Admin/Orders"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearch" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Orders</p>
                    <p class="card-value">${countOrders}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-table-list"></i>
                    </div>
                    <hr />
                </div>
                <!-- CHANGE STATUS -->
                <div class="change-status-container">
                    <button
                        class="change-new-status-btn"
                        onclick="showOrdersByStatus('New')"
                        >
                        New
                    </button>
                    <button
                        class="change-on-going-status-btn"
                        onclick="showOrdersByStatus('On Going')"
                        >
                        On Going
                    </button>
                    <button
                        class="change-completed-status-btn"
                        onclick="showOrdersByStatus('Completed')"
                        >
                        Completed
                    </button>
                </div>
            </div>
            <!-- ORDERS CONTAINER -->
            <div class="orders-container">
                <c:forEach var="order" items="${allOrder}">

                    <div class="order-card">                       
                        <!-- The Orders status -->                   
                        <div class="order-card-status">New</div>
                        <!-- When this button is clicked, view the details of the order -->
                        <button
                            class="order-card-view-detail-btn"
                            onclick="expandOrderDetail()"
                            >
                            <i class="fa-solid fa-cart-shopping"></i>
                            <span class="order-detail">
                                <p class="order-detail-total-items">Total Items: 2</p>

                                <ul class="order-detail-item-list">
                                    <c:forEach var="product" items="${order.orderDetails}">
                                        <li class="order-detail-item-container">

                                            <div class="order-detail-item">
                                                <p class="order-detail-item-id">
                                                    <strong>Product ID: </strong>${product.proDetail_id}
                                                </p>
                                                <hr />
                                                <p class="order-detail-item-quantity">
                                                    <strong>Quantity: </strong>${product.pro_name}
                                                </p>
                                                <p class="order-detail-item-quantity">
                                                    <strong>Quantity: </strong>${product.quantity}
                                                </p>
                                                <p class="order-detail-item-color">
                                                    <strong>Color: </strong>${product.color_name}
                                                </p>
                                                <p class="order-detail-item-price">
                                                    <strong>Total: </strong>${product.totalPrice}
                                                </p>


                                                <img
                                                    class="order-detail-item-image"
                                                    src=".${product.pro_image}"
                                                    />
                                            </div>

                                        </li>
                                    </c:forEach> 
                                </ul

                                ></span>  
                        </button>


                        <!-- Display Order Information -->
                        <p><strong>Order ID:</strong> ${order.orderId}</p>
                        <hr />
                        <p><strong>User ID:</strong> ${order.userId}</p>
                        <p><strong>Payment Method:</strong> ${order.payment_method}</p>
                        <p><strong>Date:</strong> <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy"/></p>
                        <hr />
                        <p><strong>Total Price: </strong>${order.grandTotal} </p>

                        <button class="order-card-accept-btn" onclick="acceptOrder(this)">
                            Accept
                        </button>
                    </div>

                </c:forEach>
            </div>
        </main>
    </body>
</html>
