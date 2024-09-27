<%-- 
    Document   : admin_dashboard
    Created on : Sep 17, 2024, 10:15:48 PM
    Author     : Le Huu Khoa - CE181099
--%>

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
            <a href="/Admin/Brand"
              ><i class="fa-solid fa-map"></i>Brands</a
            >
          </li>
        </ul>
      </div>
      <div class="account dropdown-button">
        <div class="account-icon-name">
          <i class="fa-solid fa-user"></i>
          <p class="account-name">Nguyen Trong Quy</p>
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
        <p class="title">Dashboard</p>
        <div class="search-bar">
          <input type="text" placeholder="Search" /><i
            class="fa-solid fa-magnifying-glass"
          ></i>
        </div>
      </nav>

      <div class="card-container">
        <div class="card">
          <p class="card-name">Views</p>
          <p class="card-value">5,892</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
          >
            <i class="fa-solid fa-street-view"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
        <div class="card">
          <p class="card-name">Products</p>
          <p class="card-value">4</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #26c6da, #00acc1)"
          >
            <i class="fa-solid fa-box"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
        <div class="card">
          <p class="card-name">Categories</p>
          <p class="card-value">3</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #26c6da, #00acc1)"
          >
            <i class="fa-solid fa-layer-group"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
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
        <div class="card">
          <p class="card-name">Reviews</p>
          <p class="card-value">5</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #ef5350, #e53935)"
          >
            <i class="fa-solid fa-comment"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
        <div class="card">
          <p class="card-name">Brands</p>
          <p class="card-value">4</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #26c6da, #00acc1)"
          >
            <i class="fa-solid fa-map"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
        <div class="card">
          <p class="card-name">Earnings</p>
          <p class="card-value">$17,640</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #66bb6a, #43a047)"
          >
            <i class="fa-solid fa-dollar-sign"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
      </div>
    </main>
  </body>
</html>
