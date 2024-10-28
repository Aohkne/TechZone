/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.UserDAO;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
@WebServlet("/User/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class User extends HttpServlet {

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
            out.println("<title>Servlet User</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet User at " + request.getContextPath() + "</h1>");
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
        String idUser = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    idUser = cookie.getValue();
                    break;
                }
            }
        }

        // Nếu đường dẫn là "/Admin", hiển thị trang quản trị
        if (path.equals("/User") || path.equals("/Home/User")) {
            UserDAO userdao = new UserDAO();
            Users user = userdao.getUsersById(idUser);

            if (user != null) {
                // Đặt đối tượng User vào request để hiển thị trong JSP
                request.setAttribute("user", user);
            } else {
                System.out.println("No data found for user with ID: " + idUser);
            }
            request.getRequestDispatcher("/user_profile.jsp").forward(request, response);
        } else if (path.equals("/Security") || path.equals("/Home/Security")) {

            request.getRequestDispatcher("/user_sercurity.jsp").forward(request, response);
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
        String idUser = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    idUser = cookie.getValue();
                    break;
                }
            }
        }
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        UserDAO userdao = new UserDAO();
        if (action != null) {
            if (action.equalsIgnoreCase("INFO")) {

                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                userdao.updateUserInfo(idUser, username, email, phone, address);
                response.sendRedirect("/User");
            } else {
                userdao.updateUserDetail(idUser, username, address);
                response.sendRedirect("/User");
            }
        } else {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassord = request.getParameter("confirmPassord");

            if (oldPassword == "" || newPassword == "" || confirmPassord == "") {
                request.setAttribute("errorMessage", "Please input password!");
                request.getRequestDispatcher("user_sercurity.jsp").forward(request, response);
            } else {
                ResultSet rs = userdao.getUserById(idUser);
                try {
                    // Convert to md5
                    oldPassword = md5Hash(oldPassword);

                    while (rs.next()) {
                        System.out.println(oldPassword);
                        System.out.println(rs.getString("password"));
//                        System.out.println(oldPassword.equals(rs.getString("password")));

                        //Check old password                        
                        if (oldPassword.equals(rs.getString("password"))) {
                            //Check new password
                            if (newPassword.equals(confirmPassord)) {
                                boolean check = userdao.updatePassword(idUser, md5Hash(newPassword));

                                if (check) {
                                    request.setAttribute("sucessMessage", "Update Password Successfully!");
                                    request.getRequestDispatcher("user_sercurity.jsp").forward(request, response);
                                } else {
                                    System.out.println("NO");
                                }
                            } else {
                                request.setAttribute("errorMessage", "New password and Confirm password do not match!");
                                request.getRequestDispatcher("user_sercurity.jsp").forward(request, response);
                            }
                        } else {
                            request.setAttribute("errorMessage", "Wrong password!");
                            request.getRequestDispatcher("user_sercurity.jsp").forward(request, response);
                        }
                    }
                } catch (SQLException | NoSuchAlgorithmException e) {
                    Logger.getLogger(e.getMessage());
                }

            }

        }
    }

    public String md5Hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
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
