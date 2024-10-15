/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class Voucher extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Voucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Voucher at " + request.getContextPath() + "</h1>");
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

        VoucherDAO voucherdao = new VoucherDAO();
        List<Models.Voucher> voucher = voucherdao.getAllVoucher();

        for (Models.Voucher voucherDetail : voucher) {
            System.out.println("Voucher ID: " + voucherDetail.getVoucher_id());
            System.out.println("Voucher Detail ID: " + voucherDetail.getVoucherDetail_id());
            System.out.println("Voucher Name: " + voucherDetail.getVoucher_name());
            System.out.println("Voucher Quantity: " + voucherDetail.getVoucher_quantity());
            System.out.println("Voucher Discount: " + voucherDetail.getVoucher_discount());
            System.out.println("Voucher Expire Date: " + voucherDetail.getVoucher_expire_date());
            System.out.println("Voucher Type: " + voucherDetail.getVoucher_type());
            System.out.println("Voucher Description: " + voucherDetail.getVoucher_description());
            System.out.println("------------");

        }
        request.setAttribute("voucher", voucher);

        request.getRequestDispatcher("user_cart.jsp").forward(request, response);
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
        processRequest(request, response);
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
