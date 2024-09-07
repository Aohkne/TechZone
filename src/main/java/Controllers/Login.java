/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DB.DBConnection;
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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author HP
 */
@WebServlet("/Login/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class Login extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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

            if (path.equals("/Login")) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                if (path.equals("/Login/Signup")) {
                    request.getRequestDispatcher("/signup.jsp").forward(request, response);
                } else if (path.equals("/Login/ForgotPassword")) {
                    request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
                } else {
                    if (path.equals("/Login/ForgotPassword/EnterOtp")) {
                        request.getRequestDispatcher("/EnterOtp.jsp").forward(request, response);
                    } else if (path.equals("/Login/ForgotPassword/NewPassword")) {
                        request.getRequestDispatcher("/newPassword.jsp").forward(request, response);
                    } else {

                    }
                }
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
        HttpSession session = request.getSession();
        if (request.getParameter("btnLogin") != null) {
            String us = request.getParameter("email");
            String pwd = request.getParameter("password");
            String rememberMe = request.getParameter("remember_me"); // Get the checkbox value

            Users acc = new Users(us, pwd);
            AccountDAO dao = new AccountDAO();

            if (dao.login(acc)) {
//                String picture = acc.getPicture();
//                System.out.println("Login successful, picture: " + picture); // Debug statement
//                session.setAttribute("email", us);
//                session.setAttribute("picture", picture);
//
//
//                if ("true".equals(rememberMe)) {
//                    Cookie userCookie = new Cookie("email", us);
//                    userCookie.setMaxAge(3 * 24 * 60 * 60); // 3 days
//                    userCookie.setHttpOnly(true); // Recommended for security
//                    response.addCookie(userCookie);
//                }
                System.out.println("kkkkk");
                response.sendRedirect("/Home");
            } else {
                session.setAttribute("loginError", "Invalid email or password. Please try again.");
                response.sendRedirect("/Login"); // Redirect back to the login page
            }
        } else {
            if (request.getParameter("btnAddNew") != null) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String firstname = request.getParameter("firstName");
                String lastname = request.getParameter("lastName");
                Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));

                String fileName = "";
                String uploadPath = getServletContext().getRealPath("") + File.separator + "img";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                for (Part part : request.getParts()) {
                    if (part.getName().equals("picture")) {
                        fileName = (String) getFileName(part);
                        part.write(uploadPath + File.separator + fileName);
                    }
                }

                // Construct product object
//                Account obj = new Account(email, password, name, firstname, lastname, "img/" + fileName, true, false, birthday);
//                AccountDAO dao = new AccountDAO();
//                int count = dao.addNew(obj);
//                if (count > 0) {
//
//                    // Redirect to verification pending page or show a message
//                    response.sendRedirect("/Login");
//                } else {
//                    response.sendRedirect("/Login/Signup");
//                }

            } else if (request.getParameter("btnReturn") != null) {
                response.sendRedirect("/Login");
            } else if (request.getParameter("btnNewPassword") != null) {
                System.out.println("ForgotPassword servlet called");
                String email = request.getParameter("email");
                RequestDispatcher dispatcher = null;
                int otpvalue = 0;
                HttpSession mySession = request.getSession();

                if (email != null || !email.equals("")) {
                    // sending otp
                    Random rand = new Random();
                    otpvalue = rand.nextInt(1255650);

                    String to = email;// change accordingly
                    // Get the session object
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");
                    Session sessions = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("nguyengiachan.gr2020@gmail.com", "ujtdiivvwlcoviye");// Put your email
                            // id and
                            // password here
                        }
                    });
                    // compose message
                    try {
                        MimeMessage message = new MimeMessage(sessions);
                        message.setFrom(new InternetAddress(email));// change accordingly
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        message.setSubject("Hello");
                        message.setText("your OTP is: " + otpvalue);
                        // send message
                        Transport.send(message);
                        System.out.println("message sent successfully");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    request.setAttribute("message", "OTP is sent to your email id");
                    //request.setAttribute("connection", con);
                    mySession.setAttribute("otp", otpvalue);
                    mySession.setAttribute("email", email);
                    response.sendRedirect("/Login/ForgotPassword/EnterOtp");
                    //request.setAttribute("status", "success");
                }
            } else if (request.getParameter("btnResetPassword") != null) {
                int value = Integer.parseInt(request.getParameter("otp"));
                HttpSession sessionss = request.getSession();
                int otp = (int) session.getAttribute("otp");
                String email = (String) session.getAttribute("email");

                RequestDispatcher dispatcher = null;

                if (value == otp) {
                    request.setAttribute("email", email);
                    request.setAttribute("status", "success");
                    response.sendRedirect("/Login/ForgotPassword/NewPassword");
                } else {
                    request.setAttribute("message", "Wrong OTP, please try again.");
                    response.sendRedirect("/Login/ForgotPassword");
                }

            } else if (request.getParameter("ResetPassword") != null) {
                String newPassword = request.getParameter("password");
                String confPassword = request.getParameter("confPassword");
                RequestDispatcher dispatcher = null;
                Connection conn = DBConnection.getConnection();
                if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {

                    try {
                        PreparedStatement pst = conn.prepareStatement("update Account set password = ? where email = ? ");
                        pst.setString(1, newPassword);
                        pst.setString(2, (String) session.getAttribute("email"));

                        int rowCount = pst.executeUpdate();
                        if (rowCount > 0) {
                            request.setAttribute("status", "resetSuccess");
                        } else {
                            request.setAttribute("status", "resetFailed");
                        }
                        response.sendRedirect("/Login");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }

}
