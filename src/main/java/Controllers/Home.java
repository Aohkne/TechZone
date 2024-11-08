/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.ConversationDAO;
import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import DB.DBConnection;
import Models.Conversation;
import Models.Message;
import Models.OrderDetail;
import Models.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet("/Home/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class Home extends HttpServlet {

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
            out.println("<title>Servlet Home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home at " + request.getContextPath() + "</h1>");
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

        if (path.equals("/Home") || path.equals("/") || path.equals("")) {

            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getAllDefaultProducts();
            List<Product> flashSaleProducts = productDAO.getAllFlashSaleProducts();

            // Debug thông tin sản phẩm flash sale
//            for (Product product : flashSaleProducts) {
//                System.out.println("Sale Price: " + product.getPro_sale());
//            }
            request.setAttribute("products", products);
            request.setAttribute("flashSaleProducts", flashSaleProducts);

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
                                request.setAttribute("userId", userId);
                                request.setAttribute("avatar", rs.getString("avatar"));
                                request.setAttribute("email", rs.getString("email"));
                                isId = true;

                                // Get Notification 
                                OrderDAO orderdao = new OrderDAO();
                                List<OrderDetail> orderDetails = orderdao.getAllOrderDetailsByUserIdForNotification(userId);

                                // Set the order details in request scope
                                request.setAttribute("orderDetails", orderDetails);

                                ConversationDAO coversationdao = new ConversationDAO();

                                //Sent Message
                                String inputMessage = request.getParameter("messageInput");
                                if (inputMessage != null) {
                                    coversationdao.sendMessage(userId, inputMessage);
                                    System.out.println(inputMessage);
                                }

                                //Get Message
                                List<Conversation> conversations = coversationdao.getConversationsByUserId(userId);

                                if (!conversations.isEmpty()) {
                                    int firstConversationId = conversations.get(0).getConversationId();
                                    List<Message> messages = coversationdao.getMessagesByConversationId(firstConversationId);

                                    request.setAttribute("messages", messages);
                                }

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }

            request.setAttribute("isId", isId);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
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
