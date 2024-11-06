<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.chat.Message" %>
<html>
<head>
    <title>Chat Room</title>
    <script>
        function refreshChat() {
            document.getElementById("chatWindow").scrollTop = document.getElementById("chatWindow").scrollHeight;
        }
        setInterval(function() {
            document.getElementById("chatWindow").contentWindow.location.reload();
        }, 3000);
    </script>
</head>
<body onload="refreshChat()">
    <h2>Chat Room</h2>
    <div style="border: 1px solid black; height: 300px; overflow-y: scroll;" id="chatWindow">
        <%-- Load chat messages dynamically --%>
        <iframe src="ChatServlet?action=getMessages" style="width: 100%; height: 100%; border: none;"></iframe>
    </div>
    <form action="ChatServlet" method="post">
        <input type="hidden" name="username" value="<%= request.getParameter("username") %>">
        <textarea name="message" placeholder="Type your message here..." required></textarea>
        <button type="submit">Send</button>
    </form>
</body>
</html>
