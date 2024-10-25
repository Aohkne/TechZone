package Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAOs.AccountDAO;
import DAOs.CategoryDAO;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class Categories extends HttpServlet {

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
            out.println("<title>Servlet Categories</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Categories at " + request.getContextPath() + "</h1>");
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
        if (path.equals("/Admin/Category")) {
            CategoryDAO daos = new CategoryDAO();
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
            int counts = daos.GetTotalCategory();
            List<Models.Category> searchResults = (List<Models.Category>) request.getAttribute("searchResults");
            List<Models.Category> sortResults = (List<Models.Category>) request.getAttribute("sortResults");
            List<Models.Category> allUsers = new ArrayList<>();

            if (searchResults != null) {
                allUsers = searchResults;
            } else if (sortResults != null) {
                allUsers = sortResults;

            } else {

                allUsers = daos.GetAllCategory();
            }

            request.setAttribute("name", name);
            request.setAttribute("countCategory", counts);
            request.setAttribute("allCategories", allUsers);
            request.getRequestDispatcher("/admin_categories.jsp").forward(request, response);
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
        if (request.getParameter("btnsearchCategory") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            CategoryDAO dao = new CategoryDAO();
            List<Models.Category> searchResults = dao.searchCategory(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        } else if (request.getParameter("btnSort") != null) {
            CategoryDAO dao = new CategoryDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Models.Category> sortResults = dao.getAllCategoriesSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        } else if (request.getParameter("btnAddCategory") != null) {
            String name = request.getParameter("cat-name");
            String des = request.getParameter("description");

            Models.Category newInfo = new Models.Category(name, des);
            CategoryDAO dao = new CategoryDAO();
            dao.createCategory(newInfo);

            response.sendRedirect("/Admin/Category");
        } else if (request.getParameter("btnEditCategory") != null) {

            String name = request.getParameter("cat_name");
            String des = request.getParameter("description");
            int cat_id = Integer.parseInt(request.getParameter("cat_id"));

            Models.Category newInfo = new Models.Category(cat_id, name, des);
            CategoryDAO dao = new CategoryDAO();
            dao.editCategory(newInfo);

            response.sendRedirect("/Admin/Category");
        }else if (request.getParameter("btnDeleteCategory") != null) {
            try {
                // Lấy brand_id từ request và chuyển thành số nguyên
                int brand_id = Integer.parseInt(request.getParameter("cat_id"));

                // Tạo một instance của BrandDAO
                CategoryDAO dao = new CategoryDAO();

                // Gọi hàm deleteBrand để xóa brand
                boolean result = dao.deleteCategory(brand_id);

                if (result) {
                    // Nếu xóa thành công, chuyển hướng về trang quản lý Brand
                    response.sendRedirect("/Admin/Category");
                } else {
                    // Nếu không xóa được, hiện thông báo lỗi
                    request.setAttribute("errorMessage", "Cannot delete this brand because it still has products.");
                    doGet(request, response);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu brand_id không hợp lệ
                request.setAttribute("errorMessage", "Invalid Brand ID");
                response.sendRedirect("/Error");
            }
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
