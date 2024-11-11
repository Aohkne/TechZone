/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import DAOs.VoucherDAO;
import Models.Order;
import Models.OrderDetail;
import Models.Pay;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class Payment extends HttpServlet {

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
            out.println("<title>Servlet Payment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Payment at " + request.getContextPath() + "</h1>");
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

        //Get User data
        Cookie[] cookies = request.getCookies();
        String idUser = "";
        boolean isId = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    idUser = cookie.getValue();
                    UserDAO userdao = new UserDAO();
                    AccountDAO dao = new AccountDAO();
                    int userId = Integer.parseInt(idUser);

                    try {
                        ResultSet rs = userdao.getUserById(idUser);
                        int userType = dao.getTypeById(userId);

                        if (userType == 1) {
                            response.sendRedirect("/Admin");
                            return;
                        }

                        if (rs != null && rs.next()) {
                            request.setAttribute("username", rs.getString("username"));
                            request.setAttribute("address", rs.getString("address"));
                            isId = true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        request.setAttribute("isId", isId);

        request.getRequestDispatcher("user_payment.jsp").forward(request, response);
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

        //Get User data
        Cookie[] cookies = request.getCookies();
        String idUser = "";
        boolean isId = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    idUser = cookie.getValue();
                    UserDAO userdao = new UserDAO();
                    AccountDAO dao = new AccountDAO();
                    int userId = Integer.parseInt(idUser);

                    try {
                        ResultSet rs = userdao.getUserById(idUser);
                        int userType = dao.getTypeById(userId);

                        if (userType == 1) {
                            response.sendRedirect("/Admin");
                            return;
                        }

                        if (rs != null && rs.next()) {
                            request.setAttribute("username", rs.getString("username"));
                            request.setAttribute("address", rs.getString("address"));
                            isId = true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        //Get order
        String[] productDetailIDList = request.getParameter("productDetailIDList").split(",");
        String[] quantityList = request.getParameter("quantityList").split(",");
        String[] voucherDetailIDList = request.getParameter("voucherDetailIDList").split(",");
        String[] priceList = request.getParameter("priceList").split(",");
        String paymentMethod = request.getParameter("payment");

        //Payment
        Pay payment = new Pay();
        payment.setPaymentMethod(paymentMethod);

        //Order
        Order order = new Order();
        order.setUserId(Integer.parseInt(idUser));
        order.setStatus("Not Yet");

        //OrderDetail
        List<OrderDetail> orderDetailsList = new ArrayList<>();

        for (int i = 0; i < productDetailIDList.length; i++) {
            OrderDetail orderdetail = new OrderDetail();
            orderdetail.setQuantity(Integer.parseInt(quantityList[i]));
            orderdetail.setPrice(BigDecimal.valueOf(Long.parseLong(priceList[i])));
            orderdetail.setProDetailId(Integer.parseInt(productDetailIDList[i]));
            orderdetail.setVoucherDetailId(Integer.parseInt(voucherDetailIDList[i]));
            orderdetail.setStatus("Not Yet");
            orderdetail.setCheck("false");

            orderDetailsList.add(orderdetail);

            //Update Voucher
            VoucherDAO voucherdao = new VoucherDAO();
            voucherdao.updateVoucherQuantity(Integer.parseInt(voucherDetailIDList[i]));

            //Update Product
//            ProductDAO productdao = new ProductDAO();
//            productdao.updateProductQuantity(Integer.parseInt(productDetailIDList[i]), Integer.parseInt(quantityList[i]));
        }

        //Add Order
        OrderDAO orderdao = new OrderDAO();
        orderdao.addProduct(payment, order, orderDetailsList);
        String message = "Your order has been placed!";

        System.out.println(message);

        request.setAttribute("message", message);
        request.getRequestDispatcher("user_payment.jsp").forward(request, response);
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
