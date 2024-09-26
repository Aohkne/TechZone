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
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
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
        try (PrintWriter out = response.getWriter()) {
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
            if (path.equals("/Login/Login")) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (path.equals("/Login/ForgotPassword")) {
                request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
            } else if (path.equals("/Login/ForgotPassword")) {
                request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
            } else if (path.equals("/Login/EnterOtp_1")) {
                request.getRequestDispatcher("/verify_imformation/EnterOtp_1.jsp").forward(request, response);
            } else {
                if (path.equals("/Login/ForgotPassword/EnterOtp")) {
                    request.getRequestDispatcher("/verify_imformation/EnterOtp.jsp").forward(request, response);
                } else if (path.equals("/Login/ForgotPassword/NewPassword")) {
                    request.getRequestDispatcher("/verify_imformation/newPassword.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        AccountDAO dao = new AccountDAO();

        if (request.getParameter("btnLogin") != null) {
            String us = request.getParameter("email");
            String pwd = request.getParameter("password");
//            String rememberMe = request.getParameter("remember"); // Get the checkbox value

            Users acc = new Users(us, pwd);

            if (dao.login(acc)) {
//                String picture = acc.getPicture();
//                System.out.println("Login successful, picture: " + picture); // Debug statement
//                session.setAttribute("email", us);
//                session.setAttribute("picture", picture);
                boolean check = acc.isStatus_user();

                if (!check) {
                    session.setAttribute("loginError", "Your account has been blocked.");
                    response.sendRedirect("/Login"); // Redirect back to the login page
                    return;
                }
//                if ("true".equals(rememberMe)) {
                int id = dao.GetIdUser(us);
                Cookie userCookie = new Cookie("id", id+"");
                userCookie.setMaxAge(3 * 24 * 60 * 60); // 3 days
                userCookie.setHttpOnly(true); // Recommended for security
                response.addCookie(userCookie);
//                }
                if (dao.getTypeByEmail(us) == 1) {
                    response.sendRedirect("/Admin");
                } else {
                    response.sendRedirect("/Home");
                }

            } else {
                session.setAttribute("loginError", "Invalid email or password. Please try again.");
                response.sendRedirect("/Login"); // Redirect back to the login page
            }
        } else {
            if (request.getParameter("btnAddNew") != null) {
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                // Hash the password
                String hashedPassword = "";
                try {
                    hashedPassword = dao.md5Hash(password);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/Login");
                    return;
                }

                String avatar = "/asset/img/img_all/img_user/cat_stare_full.jpg"; // Default avatar
                java.sql.Date create_at = new java.sql.Date(System.currentTimeMillis());

                // Create the Users object with phone and address set to null
                Users obj = new Users(username, hashedPassword, email, null, null, 2, create_at, avatar, true);
                if (dao.checkEmail(email)) {
                    session.setAttribute("loginError", "Account was Exist.");
                    response.sendRedirect("/Login");
                    return;
                }
                // sending otp
                Random rand = new Random();
                int otpvalue = rand.nextInt(1255650);

                String to = email; // Recipient email

                // Email sending logic
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session sessions = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nguyengiachan.gr2020@gmail.com", "ujtdiivvwlcoviye");
                    }
                });

                try {
                    MimeMessage message = new MimeMessage(sessions);
                    message.setFrom(new InternetAddress("your-email@gmail.com")); // Correct email sender
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Hello");
                    message.setText("Your OTP is: " + otpvalue);
                    Transport.send(message);
                    System.out.println("Message sent successfully");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

                session.setAttribute("otp", otpvalue);
                session.setAttribute("email", email);
                session.setAttribute("userObject", obj); // Store the user object for later use
                response.sendRedirect("/Login/EnterOtp_1");

            } else if (request.getParameter("btnCheckEmail") != null) {
                String email = (String) session.getAttribute("email");
                int otpValue = Integer.parseInt(request.getParameter("otp"));
                Integer sessionOtp = (Integer) session.getAttribute("otp");

                if (sessionOtp != null && otpValue == sessionOtp) {
                    Users obj = (Users) session.getAttribute("userObject");
                    if (obj != null) {
                        int count = dao.addNew(obj);
                        if (count > 0) {
                            session.setAttribute("loginError", "Account created successfully.");
                            response.sendRedirect("/Login");
                        } else {
                            session.setAttribute("loginError", "Error occurred. Please try again.");
                            response.sendRedirect("/Login");
                        }
                    } else {
                        session.setAttribute("loginError", "User object is missing. Please try again.");
                        response.sendRedirect("/Login");
                    }
                } else {
                    session.setAttribute("messages", "Invalid OTP. Please try again.");
                    response.sendRedirect("/Login/EnterOtp_1");
                }
            } else if (request.getParameter("btnReturn") != null) {
                response.sendRedirect("/Login");
            } else if (request.getParameter("btnNewPassword") != null) {
                String email = request.getParameter("email");
                if (dao.checkEmail(email)) {
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
                } else {
                    session.setAttribute("ForgotPasswordError", "Error occurred. Please try again.");
                    response.sendRedirect("/Login/ForgotPassword");
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
                    session.setAttribute("message", "Wrong OTP, please try again.");
                    response.sendRedirect("/Login/ForgotPassword/EnterOtp");
                }

            } else if (request.getParameter("ResetPassword") != null) {
                String email = (String) session.getAttribute("email");
                System.out.println(email);
                String newPassword = request.getParameter("password");
                String confPassword = request.getParameter("confPassword");
                RequestDispatcher dispatcher = null;
                String pass = "";
                try {
                    pass = dao.md5Hash(newPassword);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(pass);
                Connection conn = DBConnection.getConnection();
                if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
                    if (dao.CheckNewPassword(email).equals(pass)) {
                        session.setAttribute("NewPasswordError", "Please enter a different password from the previous one");
                        response.sendRedirect("/Login/ForgotPassword/NewPassword");
                        return;
                    } else {
                        try {
                            PreparedStatement pst = conn.prepareStatement("update Users set password = ? where email = ? ");
                            pst.setString(1, dao.md5Hash(newPassword));
                            pst.setString(2, (String) session.getAttribute("email"));

                            int rowCount = pst.executeUpdate();
                            if (rowCount > 0) {
                                session.setAttribute("loginError", "resetSuccess");
                            } else {
                                session.setAttribute("loginError", "resetFailed");
                            }
                            response.sendRedirect("/Login");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    session.setAttribute("NewPasswordError", "Error occurred. Please try again.");
                    response.sendRedirect("/Login/ForgotPassword/NewPassword");
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

}
