/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.CategoryDAO;
import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import Models.OrderDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class Category extends HttpServlet {

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

        //User
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
                            request.setAttribute("avatar", rs.getString("avatar"));
                            request.setAttribute("email", rs.getString("email"));
                            isId = true;

                            // Get Notification 
                            OrderDAO orderdao = new OrderDAO();
                            List<OrderDetail> orderDetails = orderdao.getAllOrderDetailsByUserIdForNotification(userId);

                            // Set the order details in request scope
                            request.setAttribute("orderDetails", orderDetails);
                        }
                    } catch (SQLException e) {
                    }
                    break;
                }
            }
        }

        request.setAttribute("isId", isId);

        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productdao = new ProductDAO();
        ResultSet categories = categoryDAO.getAllCategory();
        request.setAttribute("categories", categories);

        String search = request.getParameter("search");
        String idCat = request.getParameter("id");
        String increase = request.getParameter("increase");
        String decrease = request.getParameter("decrease");
        ResultSet rs = null;
        List<Models.Product> productList = null;
        if (search != null) {
            search = search.toLowerCase();
            request.setAttribute("search", search);
            productList = productdao.getProductBySearch(search);
        } else if (idCat != null) {
            request.setAttribute("idCat", idCat);
            productList = productdao.getProductByBrandId(idCat);
        } else if (increase != null && increase.matches("-?\\d+")) {

            request.setAttribute("idCat", increase);
            productList = productdao.getProductIncreaseByCatId(increase);
        } else if (decrease != null && decrease.matches("-?\\d+")) {
            request.setAttribute("idCat", decrease);
            productList = productdao.getProductDecreaseByCatId(decrease);
        } else if (increase != null) {
            request.setAttribute("increase", increase);
            productList = productdao.getProductIncrease();
        } else if (decrease != null) {
            request.setAttribute("decrease", decrease);
            productList = productdao.getProductDecrease();
        }
        if (rs != null) {
            request.setAttribute("productDetails", rs);
        } else {
            System.out.println("rs empty");
        }
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("user_category.jsp").forward(request, response);

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
        doGet(request, response);

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
