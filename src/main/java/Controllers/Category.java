/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class Category extends HttpServlet {

//    public Category(int cat_id, String name, String des) {
//    }
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
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");
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
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        request.setAttribute("isId", isId);

        String search = request.getParameter("search");
        String idCat = request.getParameter("id");
        String increase = request.getParameter("increase");
        String decrease = request.getParameter("decrease");

        if (search != null) {
            search = search.toLowerCase();
            request.setAttribute("search", search);
        } else if (idCat != null) {
            request.setAttribute("idCat", idCat);
        } else if (increase != null) {
            request.setAttribute("increase", increase);
        } else if (decrease != null) {
            request.setAttribute("decrease", decrease);
        }

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
