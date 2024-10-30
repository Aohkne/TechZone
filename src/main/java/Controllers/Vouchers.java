/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Vouchers extends HttpServlet {

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
            out.println("<title>Servlet Vouchers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Vouchers at " + request.getContextPath() + "</h1>");
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
        if (path.equals("/Admin/Vouchers")) {
            AccountDAO dao = new AccountDAO();
            VoucherDAO daos = new VoucherDAO();
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
            int counts = daos.GetTotalVoucher();

            List<Models.Voucher> searchResults = (List<Models.Voucher>) request.getAttribute("searchResults");
            List<Models.Voucher> sortResults = (List<Models.Voucher>) request.getAttribute("sortResults");
            List<Models.Voucher> allUsers = new ArrayList<>();

            if (searchResults != null) {
                allUsers = searchResults;
            } else if (sortResults != null) {
                allUsers = sortResults;

            } else {
                allUsers = daos.GetAllVoucher();
            }

            List<Models.Users> catDaoName = dao.GetUser();
            request.setAttribute("username", catDaoName);

            List<Models.Voucher> brandDaoName = daos.GetVoucher();
            request.setAttribute("voucherType", brandDaoName);

            request.setAttribute("countVoucher", counts);
            request.setAttribute("name", name);
            request.setAttribute("allBrand", allUsers);

            request.getRequestDispatcher("/admin_vouchers.jsp").forward(request, response);
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
        if (request.getParameter("btnsearchVoucher") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            VoucherDAO dao = new VoucherDAO();
            List<Models.Voucher> searchResults = dao.searchVoucher(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        } else if (request.getParameter("btnSort") != null) {
            VoucherDAO dao = new VoucherDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Models.Voucher> sortResults = dao.getAllVouchersSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        } else if (request.getParameter("btnDeleteVoucher") != null) {
            try {
                // Lấy brand_id từ request và chuyển thành số nguyên
                int brand_id = Integer.parseInt(request.getParameter("voucherDetail_id"));

                // Tạo một instance của BrandDAO
                VoucherDAO dao = new VoucherDAO();

                // Gọi hàm deleteBrand để xóa brand
                boolean result = dao.deleteVoucher(brand_id);

                if (result) {
                    // Nếu xóa thành công, chuyển hướng về trang quản lý Brand
                    response.sendRedirect("/Admin/Vouchers");
                } else {
                    // Nếu không xóa được, hiện thông báo lỗi
                    request.setAttribute("errorMessage", "Cannot delete this brand because it still has products.");
                    doGet(request, response);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu brand_id không hợp lệ
                request.setAttribute("errorMessage", "Invalid Voucher ID");
                response.sendRedirect("/Error");
            }
        } else if (request.getParameter("btnAddVoucher") != null) {
            try {
                // Retrieve values from the request
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                int voucher_id = Integer.parseInt(request.getParameter("voucher_id"));
                int voucher_discount = Integer.parseInt(request.getParameter("voucher_discount"));

                String voucher_name = "Discount " + voucher_discount + "%";
                String voucher_expire_date = request.getParameter("voucher_expire_date");
                int voucher_quantity = Integer.parseInt(request.getParameter("voucher_quantity"));

                // Create a new Voucher object
                Models.Voucher newVoucher = new Models.Voucher();
                newVoucher.setUser_id(user_id);
                newVoucher.setVoucher_id(voucher_id);
                newVoucher.setVoucher_name(voucher_name);
                newVoucher.setVoucher_discount(voucher_discount);
                newVoucher.setVoucher_expire_date(Date.valueOf(voucher_expire_date)); // Convert String to Date
                newVoucher.setVoucher_quantity(voucher_quantity);

                // Get the session object for storing error/success messages
                HttpSession session = request.getSession();

                // Validate voucher discount based on voucher type
                if (voucher_id == 1 && voucher_discount > 10) {
                    session.setAttribute("errorMessage", "Failed to add the voucher, Failed Voucher Type.");
                    response.sendRedirect("/Admin/Vouchers");
                } else if (voucher_id == 2 && (voucher_discount > 25 || voucher_discount < 15)) {
                    session.setAttribute("errorMessage", "Failed to add the voucher, Failed Voucher Type.");
                    response.sendRedirect("/Admin/Vouchers");
                } else if (voucher_id == 3 && (voucher_discount > 50 || voucher_discount < 30)) {
                    session.setAttribute("errorMessage", "Failed to add the voucher, Failed Voucher Type.");
                    response.sendRedirect("/Admin/Vouchers");
                } else {
                    // Add voucher if validation passes
                    VoucherDAO dao = new VoucherDAO();
                    boolean result = dao.addVoucher(newVoucher);
                    if (result) {
                        // If addition is successful, set success message and redirect
                        session.setAttribute("success", "Successfully added the voucher.");
                        response.sendRedirect("/Admin/Vouchers");
                    } else {
                        // If addition fails, set error message and redirect
                        session.setAttribute("errorMessage", "Failed to add the voucher.");
                        response.sendRedirect("/Admin/Vouchers");
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle invalid input data error
                request.setAttribute("errorMessage", "Invalid input data for Voucher.");
                request.getRequestDispatcher("/Error").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle other errors
                request.setAttribute("errorMessage", "An unexpected error occurred while adding the voucher.");
                request.getRequestDispatcher("/Error").forward(request, response);
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
