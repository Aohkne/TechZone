/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import Models.Users;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
@WebServlet("/Admin/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class Admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();  // Lấy URL hiện tại

        // Nếu đường dẫn là "/Admin", hiển thị trang quản trị
        if (path.equals("/Admin") || path.equals("/Admin/Dashboard")) {
            request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
        } else if (path.equals("/Admin/Users")) {
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
            int counts = dao.GetTotalUser();

            request.setAttribute("countUser", counts);
            request.setAttribute("name", name);
            

            List<Users> searchResults = (List<Users>) request.getAttribute("searchResults");
            List<Users> sortResults = (List<Users>) request.getAttribute("sortResults");
            List<Users> allUsers = new ArrayList<>();

            if (searchResults != null) {
                allUsers = searchResults;
            } else if (sortResults != null) {
                allUsers = sortResults;

            } else {

                allUsers = dao.getAllUser();
            }
            
            Map<Integer, Boolean> verifiedEmails = new HashMap<>();
            for (Users user : allUsers) {
                verifiedEmails.put(user.getUser_id(), dao.getVerifyByEmail(user.getUser_id()));
            }
            
            request.setAttribute("allUsers", allUsers);
            request.setAttribute("verifiedEmails", verifiedEmails);
            request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
        } else if (path.equals("/Admin/Product")) {
            request.getRequestDispatcher("/admin_products.jsp").forward(request, response);
        } else if (path.equals("/Admin/Review")) {
            request.getRequestDispatcher("/admin_reviews.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btnsearchUser") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            AccountDAO dao = new AccountDAO();
            List<Users> searchResults = dao.searchUsers(query);
            

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        } else if (request.getParameter("userId") != null && request.getParameter("verified") != null) {
            // Cập nhật trạng thái tài khoản
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean verified = Boolean.parseBoolean(request.getParameter("verified"));
            AccountDAO dao = new AccountDAO();
            // Cập nhật trạng thái trong DB
            dao.setVerifiedEmail(userId, verified);

            // Phản hồi JSON để JavaScript có thể xử lý
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"success\"}");
        } else if (request.getParameter("btnSort") != null) {
            AccountDAO dao = new AccountDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Users> sortResults = dao.getAllUsersSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
