/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.BrandDAO;
import DAOs.CommentDAO;
import Models.Comment;
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
public class Comments extends HttpServlet {

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
            out.println("<title>Servlet Comments</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Comments at " + request.getContextPath() + "</h1>");
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
        if (path.equals("/Admin/Review")) {

            AccountDAO dao = new AccountDAO();
            CommentDAO daos = new CommentDAO();
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
            int counts = daos.GetTotalComment();

            List<Models.Comment> searchResults = (List<Models.Comment>) request.getAttribute("searchResults");
            List<Models.Comment> sortResults = (List<Models.Comment>) request.getAttribute("sortResults");
            List<Models.Comment> allComment = new ArrayList<>();

            if (searchResults != null) {
                allComment = searchResults;
            } else if (sortResults != null) {
                allComment = sortResults;
                if (allComment != null) {
                    for (Comment cmt : allComment) {
                        int user_id = cmt.getUser_id();
                        String username = dao.GetNameAdmin(user_id);

                        cmt.setUsername(username);

                        int pro_id = cmt.getPro_id();
                        String pro_name = daos.getProductName(pro_id);
                        cmt.setProduct_name(pro_name);
                    }
                }

            } else {
                allComment = daos.GetAllComments();
                if (allComment != null) {
                    for (Comment cmt : allComment) {
                        int user_id = cmt.getUser_id();
                        String username = dao.GetNameAdmin(user_id);

                        cmt.setUsername(username);

                        int pro_id = cmt.getPro_id();
                        String pro_name = daos.getProductName(pro_id);
                        cmt.setProduct_name(pro_name);
                    }
                }
            }

            request.setAttribute("countBrand", counts);
            request.setAttribute("name", name);
            request.setAttribute("allBrand", allComment);
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
        if (request.getParameter("btnsearch") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm comment
            CommentDAO dao = new CommentDAO();
            List<Models.Comment> searchResults = dao.searchComment(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách comment
            doGet(request, response);
        } else if (request.getParameter("btnSort") != null) {
            CommentDAO dao = new CommentDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Comment> sortResults = dao.getAllCommentsSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        } else if (request.getParameter("btnDeleteComment") != null) {
            try {
                // Lấy brand_id từ request và chuyển thành số nguyên
                int brand_id = Integer.parseInt(request.getParameter("comment_id"));

                // Tạo một instance của BrandDAO
                CommentDAO dao = new CommentDAO();

                // Gọi hàm deleteBrand để xóa brand
                boolean result = dao.deleteComment(brand_id);

                if (result) {
                    // Nếu xóa thành công, chuyển hướng về trang quản lý Brand
                    response.sendRedirect("/Admin/Review");
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
