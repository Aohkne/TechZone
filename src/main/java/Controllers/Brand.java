/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.BrandDAO;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Brand extends HttpServlet {

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
            out.println("<title>Servlet Brand</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Brand at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        if (path.equals("/Admin/Brand")) {
            AccountDAO dao = new AccountDAO();
            BrandDAO daos = new BrandDAO();
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
            int counts = daos.GetTotalBrand();

            List<Models.Brand> searchResults = (List<Models.Brand>) request.getAttribute("searchResults");
            List<Models.Brand> sortResults = (List<Models.Brand>) request.getAttribute("sortResults");
            List<Models.Brand> allUsers = new ArrayList<>();

            if (searchResults != null) {
                allUsers = searchResults;
            } else if (sortResults != null) {
                allUsers = sortResults;

            } else {

                allUsers = daos.GetAllBrand();
            }

            request.setAttribute("countBrand", counts);
            request.setAttribute("name", name);
            request.setAttribute("allBrand", allUsers);
            request.getRequestDispatcher("/admin_brands.jsp").forward(request, response);
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
        if (request.getParameter("btnsearchBrand") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            BrandDAO dao = new BrandDAO();
            List<Models.Brand> searchResults = dao.searchBrand(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        } else if (request.getParameter("btnSort") != null) {
            BrandDAO dao = new BrandDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Models.Brand> sortResults = dao.getAllBrandsSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        } else if (request.getParameter("btnAddBrand") != null) {
            String name = request.getParameter("brand-name");
            String des = request.getParameter("description");

            Models.Brand newInfo = new Models.Brand(name, des);
            BrandDAO dao = new BrandDAO();
            int count = dao.createBrand(newInfo);

            response.sendRedirect("/Admin/Brand");
        } else if (request.getParameter("btnEditBrand") != null) {

            String name = request.getParameter("brand_name");
            String des = request.getParameter("description");
            int brand_id = Integer.parseInt(request.getParameter("brand_id"));

            Models.Brand newInfo = new Models.Brand(brand_id, name, des);
            BrandDAO dao = new BrandDAO();
            dao.editBrand(newInfo);

            response.sendRedirect("/Admin/Brand");

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
