<%-- 
    Document   : admin_chat
    Created on : Nov 7, 2024, 3:20:27 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Chat</title>
    <link rel="stylesheet" href="/asset/css/style_admin_chat.css" />
    <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
    <script src="/asset/js/js_admin_chat.js" defer></script>
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
            <a href="admin_dashboard.html"
              ><i class="fa-solid fa-list"></i>Dashboard</a
            >
          </li>
          <li>
            <a href="admin_products.html"
              ><i class="fa-solid fa-box"></i>Products</a
            >
          </li>
          <li>
            <a href="admin_categories.html"
              ><i class="fa-solid fa-layer-group"></i>Categories</a
            >
          </li>
          <li>
            <a href="admin_users.html"
              ><i class="fa-solid fa-users"></i>Users</a
            >
          </li>
          <li>
            <a href="admin_reviews.html"
              ><i class="fa-solid fa-comment"></i>Reviews</a
            >
          </li>
          <li>
            <a href="admin_brands.html"
              ><i class="fa-solid fa-map"></i>Brands</a
            >
          </li>
          <li>
            <a href="admin_vouchers.html"
              ><i class="fa-solid fa-ticket"></i>Vouchers</a
            >
          </li>
          <li>
            <a href="admin_orders.html"
              ><i class="fa-solid fa-table-list"></i>Orders</a
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
              <li><a href="admin_profile.html">Profile</a></li>
              <li><a href="#">Logout</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!-- MAIN CONTENT -->
    <main>
      <nav>
        <p class="title">Chat</p>
        <div class="search-bar">
          <input type="text" placeholder="Search" /><i
            class="fa-solid fa-magnifying-glass"
          ></i>
        </div>
      </nav>
      <section class="chat-section">
        <!-- Cái này là container bên trái chứa tất cả người dùng -->
        <!-- Khi nhấn vào nó sẽ hiện chatbox bên dưới -->
        <div class="chat-container">
          <ul class="people-container">
            <li class="person" onclick="openChatbox('chatbox1')">
              <img
                src="/asset/img/img_all/img_user/cat_stare_full.jpg"
                alt=""
                class="person-image"
              />
              <p class="person-name">Nguyen Trong Quy</p>
              <p class="person-message" style="color: black">
                12131232132132132121321312321213213213
              </p>
              <p class="message-date" style="color: black">1 minute ago</p>
            </li>
            <li class="person" onclick="openChatbox('chatbox2')">
              <img
                src="/asset/img/img_all/img_user/cat_stare_full.jpg"
                alt=""
                class="person-image"
              />
              <p class="person-name">Bao Bao</p>
              <p class="person-message">Hello, is this phone good?</p>
              <p class="message-date">1 minute ago</p>
            </li>
          </ul>
        </div>
        <!-- Đây là nơi chứa các cái chatbox -->
        <!-- Chatbox 1: Nguyen Trong Quy -->
        <div id="chatbox1" class="chatbox hidden">
          <div id="chatbox-content">
            <div
              style="display: flex; align-items: center; margin-bottom: 40px"
            >
              <img
                src="/asset/img/img_all/img_user/cat_stare_full.jpg"
                style="
                  width: 50px;
                  height: 50px;
                  border-radius: 50%;
                  margin-right: 10px;
                "
              />
              <p>Nguyen Trong Quy</p>
              <br />
            </div>
            <p style="background-color: #dce8ff">
              12131232132132132121321312321213213213
            </p>
            <div class="chatbox-input-container">
              <input
                type="text"
                placeholder="Type a message"
                class="chatbox-input"
              />
              <i class="fa-solid fa-paper-plane chatbox-send-icon"></i>
            </div>
          </div>
        </div>

        <!-- Chatbox 2: Bao Bao -->
        <div id="chatbox2" class="chatbox hidden">
          <div id="chatbox-content">
            <div
              style="display: flex; align-items: center; margin-bottom: 40px"
            >
              <img
                src="/asset/img/img_all/img_user/cat_stare_full.jpg"
                style="
                  width: 50px;
                  height: 50px;
                  border-radius: 50%;
                  margin-right: 10px;
                "
              />
              <p>Bao Bao</p>
              <br />
            </div>
            <p style="background-color: #dce8ff">Hello, is this phone good?</p>
            <div class="chatbox-input-container">
              <input
                type="text"
                placeholder="Type a message"
                class="chatbox-input"
              />
              <i class="fa-solid fa-paper-plane chatbox-send-icon"></i>
            </div>
          </div>
        </div>
      </section>
    </main>
  </body>
</html>
