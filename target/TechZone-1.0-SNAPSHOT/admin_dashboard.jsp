<%-- 
    Document   : admin_dashboard
    Created on : Sep 17, 2024, 10:15:48 PM
    Author     : Le Huu Khoa - CE181099
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="/asset/css/style_admin_dashboard.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <script src="/asset/js/js_admin_dashboard.js" defer></script>
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
                <p class="title">Dashboard</p>
                <div class="search-bar">
                    <input type="text" placeholder="Search" /><i
                        class="fa-solid fa-magnifying-glass"
                        ></i>
                </div>
            </nav>

            <div class="card-container">
                <div class="card">
                    <p class="card-name">Vouchers</p>
                    <p class="card-value">${count6}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                        >
                        <i class="fa-solid fa-map"></i>
                    </div>
                    <hr />
                </div>
                <div class="card">
                    <p class="card-name">Products</p>
                    <p class="card-value">${count1}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-box"></i>
                    </div>
                    <hr />
                </div>
                <div class="card">
                    <p class="card-name">Categories</p>
                    <p class="card-value">${count2}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-layer-group"></i>
                    </div>
                    <hr />
                </div>
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
                </div>
                <div class="card">
                    <p class="card-name">Comments</p>
                    <p class="card-value">${count3}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                        >
                        <i class="fa-solid fa-comment"></i>
                    </div>
                    <hr />
                </div>
                <div class="card">
                    <p class="card-name">Brands</p>
                    <p class="card-value">${count4}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-map"></i>
                    </div>
                    <hr />
                </div>
                <div class="card">
                    <p class="card-name">Earnings</p>
                    <p class="card-value">$${count5}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                        >
                        <i class="fa-solid fa-dollar-sign"></i>
                    </div>
                    <hr />
                </div>
            </div>
            <br>
            <!-- CHARTS -->
            <div class="charts-container">
                <div class="users-chart">
                    <canvas id="users-chart" aria-label="Users Chart" role="text"></canvas>
                </div>
            </div>
        </main>
    </body>
    <!-- Import chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <script>
        // Lấy dữ liệu biểu đồ từ backend
        let chartDataJson = '<%= request.getAttribute("chartData") %>';

        // Kiểm tra dữ liệu nhận được
        console.log("Chart Data JSON:", chartDataJson);

        if (!chartDataJson || chartDataJson.trim() === '') {
            console.warn("Chart data is empty or undefined.");
        } else {
            try {
                // Parse JSON và kiểm tra cấu trúc dữ liệu
                const chartData = JSON.parse(chartDataJson);

                // Nếu dữ liệu hợp lệ, tạo biểu đồ
                new Chart(document.getElementById("users-chart"), {
                    type: "bar",
                    data: {
                        labels: chartData.labels,
                        datasets: [
                            {
                                label: "Users per month",
                                backgroundColor: "#ffa726",
                                data: chartData.data,
                                borderWidth: 1,
                            },
                        ],
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                            },
                        },
                    },
                });
            } catch (e) {
                console.error("Error parsing chart data JSON:", e);
            }
        }
    </script>


</html>
