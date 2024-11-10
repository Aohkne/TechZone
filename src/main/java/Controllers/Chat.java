/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.ConversationDAO;
import DAOs.OrderDAO;
import DAOs.UserDAO;
import Models.Conversation;
import Models.Message;
import Models.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class Chat extends HttpServlet {

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
            out.println("<title>Servlet Chat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Chat at " + request.getContextPath() + "</h1>");
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
        if (path.equals("/Admin/Chat")) {

            //get admin id
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
                        String name = dao.GetNameAdmin(userId);
                        request.setAttribute("name", name);

                        try {
                            ResultSet rs = userdao.getUserById(idUser);
                            request.setAttribute("userId", userId);
                            int userType = dao.getTypeById(userId);

                            if (rs != null && rs.next()) {
                                request.setAttribute("username", rs.getString("username"));
                                request.setAttribute("userId", userId);
                                request.setAttribute("avatar", rs.getString("avatar"));
                                request.setAttribute("email", rs.getString("email"));
                                isId = true;

                                //Sent Message
                                ConversationDAO conversationdao = new ConversationDAO();
                                String adminChat = request.getParameter("adminChat");
                                System.out.println(adminChat);
                                if (adminChat != null) {
                                    int conversationID = Integer.parseInt(request.getParameter("conversationID"));
                                    conversationdao.sendMessageAdmin(adminChat, userId, conversationID);
                                }

                                //Get Conversation
                                Map<Integer, List<Object[]>> conversations = conversationdao.getUserConversationsByAdminId(userId);

                                if (!conversations.isEmpty()) {
                                    request.setAttribute("conversations", conversations);
                                }

                                //Get Chat Box
                                Map<Integer, List<Object[]>> conversationMap = conversationdao.getConversationsByAdminId(userId);
                                request.setAttribute("conversationMap", conversationMap);

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }

            request.getRequestDispatcher("/admin_chat.jsp").forward(request, response);
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
