/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.BrandDAO;
import DAOs.CategoryDAO;
import DAOs.UserDAO;
import Models.Brand;
import Models.Category;
import Models.Users;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

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
        } else if (path.equals("/Admin/Brand")) {
            request.getRequestDispatcher("/admin_brands.jsp").forward(request, response);
        } else if (path.equals("/Admin/Users")) {
            request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
        } else if (path.equals("/Admin/Category")) {
            request.getRequestDispatcher("/admin_categories.jsp").forward(request, response);
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
            request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
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

            // Chuyển tiếp đến trang hiển thị danh sách thương hiệu
            request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
            // Reset sortResults sau khi xử lý xong
            request.setAttribute("sortResults", null);
        } else if (request.getParameter("btnsearchBrand") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            BrandDAO dao = new BrandDAO();
            List<Brand> searchResults = dao.searchBrand(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            request.getRequestDispatcher("/admin_brands.jsp").forward(request, response);
        } else if (request.getParameter("btnSort") != null) {
            BrandDAO dao = new BrandDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Brand> sortResults = dao.getAllBrandsSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            // Chuyển tiếp đến trang hiển thị danh sách thương hiệu
            request.getRequestDispatcher("/admin_brands.jsp").forward(request, response);
            // Reset sortResults sau khi xử lý xong
            request.setAttribute("sortResults", null);
        } else if (request.getParameter("btnAddBrand") != null) {
            String name = request.getParameter("brand-name");
            String des = request.getParameter("description");

            Brand newInfo = new Brand(name, des);
            BrandDAO dao = new BrandDAO();
            int count = dao.createBrand(newInfo);

            response.sendRedirect("/Admin/Brand");
        } else if (request.getParameter("btnEditBrand") != null) {

            String name = request.getParameter("brand_name");
            String des = request.getParameter("description");
            int brand_id = Integer.parseInt(request.getParameter("brand_id"));

            Brand newInfo = new Brand(brand_id, name, des);
            BrandDAO dao = new BrandDAO();
            dao.editBrand(newInfo);

            response.sendRedirect("/Admin/Brand");

        } else if (request.getParameter("btnsearchCategory") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            CategoryDAO dao = new CategoryDAO();
            List<Models.Category> searchResults = dao.searchCategory(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            request.getRequestDispatcher("/admin_categories.jsp").forward(request, response);
        } else if (request.getParameter("btnSort") != null) {
            CategoryDAO dao = new CategoryDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Models.Category> sortResults = dao.getAllCategoriesSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            // Chuyển tiếp đến trang hiển thị danh sách thương hiệu
            request.getRequestDispatcher("/admin_categories.jsp").forward(request, response);
            // Reset sortResults sau khi xử lý xong
            request.setAttribute("sortResults", null);
        } else if (request.getParameter("btnAddCategory") != null) {
            String name = request.getParameter("cat-name");
            String des = request.getParameter("description");

            Category newInfo = new Category(name, des);
            CategoryDAO dao = new CategoryDAO();
            dao.createCategory(newInfo);

            response.sendRedirect("/Admin/Category");
        } else if (request.getParameter("btnEditCategory") != null) {

            String name = request.getParameter("cat_name");
            String des = request.getParameter("description");
            int cat_id = Integer.parseInt(request.getParameter("cat_id"));

            Category newInfo = new Category(cat_id, name, des);
            CategoryDAO dao = new CategoryDAO();
            dao.editCategory(newInfo);

            response.sendRedirect("/Admin/Category");

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
