<%-- 
    Document   : admin_chat
    Created on : Nov 7, 2024, 3:20:27 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <!-- 
                    Admin ID - entry.key
                    User ID: conv[0]
                    Username conv[1]
                    Avatar: conv[2]
                    Conversation ID: conv[3]
                    Latest Message: conv[4]
                    Date: conv[5]
                -->



                <div class="chat-container">
                    <ul class="people-container">

                        <c:choose>
                            <c:when test="${empty conversations}">
                                <p>No conversations available.</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="entry" items="${conversations}">
                                    <c:forEach var="conv" items="${entry.value}">
                                        <li class="person" onclick="openChatbox('chatbox${conv[3]}')">
                                            <img src="${conv[2].substring(1)}" class="person-image">
                                            <p class="person-name">${conv[1]}</p>
                                            <p class="person-message" style="color: black">
                                                ${conv[4]}
                                            </p>
                                            <p class="message-date" style="color: black">${conv[5]}</p>
                                        </li>
                                    </c:forEach>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>

                <!-- 
                    Conversation ID - conversationId
                    User ID: info[0]
                    Username info[1]
                    Avatar: info[2]
                
                    Message: ID: message[4]
                    Sender ID: message[5]
                    Timestamp: message[6]
                -->


                <c:forEach var="entry" items="${conversationMap}">
                    <c:set var="conversationId" value="${entry.key}" />
                    <c:set var="messages" value="${entry.value}" />

                    <!-- Thông tin hiển thị một lần cho mỗi cuộc trò chuyện -->


                    <c:if test="${not empty messages}">
                        <c:set var="info" value="${messages[0]}" />
                        <div id="chatbox${conversationId}" class="chatbox hidden">
                            <div id="chatbox-content">
                                <div
                                    style="display: flex; align-items: center; margin-bottom: 40px"
                                    >
                                    <img
                                        src="${info[2].substring(1)}"
                                        style="
                                        width: 80px;
                                        height: 80px;
                                        border-radius: 50%;
                                        margin-right: 10px;
                                        "
                                        />
                                    <p>${info[1]}</p>
                                    <br />
                                </div>
                            </c:if>


                            <c:forEach var="message" items="${messages}">
                                <p class="chat-item ${message[5] == userId ? 'admin' : 'user'}">
                                    <span>${message[4]}</span>
                                </p>
                            </c:forEach>

                            <form action="/Admin/Chat" method="GET">
                                <div class="chatbox-input-container">
                                    <input type="hidden" value="${conversationId}" name="conversationID"/>
                                    <input
                                        type="text"
                                        placeholder="Type a message"
                                        class="chatbox-input"
                                        name="adminChat"
                                        />
                                    <button type="submit" class="chat__sent">
                                        <i class="fa-solid fa-paper-plane chatbox-send-icon"></i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>

            </section>
        </main>
    </body>
</html>
