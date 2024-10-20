/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.BrandDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import Models.Product_Details;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet("/Product/*")
public class Product extends HttpServlet {

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
        
        
        if (path.startsWith("/Home/Product") || path.startsWith("/Product")) {
            String id = (String) request.getParameter("id");
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
                        }
                        break;
                    }
                }
            }

            request.setAttribute("isId", isId);

            ProductDAO productDAO = new ProductDAO();
            ResultSet productDetail = productDAO.getAllProductDetailById(id);
            String img = null;
            String proId = null;

            try {
                if (productDetail != null && productDetail.next()) {
                    img = productDetail.getString("image");
                    proId = productDetail.getString("pro_id");

                    ResultSet rs = productDAO.getAllProductByDetailId(proId);
                    try {
                        if (rs != null && rs.next()) {
                            Models.Product product = new Models.Product();
                            product.setPro_id(rs.getInt("pro_id"));
                            product.setPro_name(rs.getString("pro_name"));
                            product.setPro_price(productDAO.formatPrice(rs.getString("pro_price")));
                            product.setPro_sale(rs.getString("pro_sale") == null ? null : productDAO.formatPrice(rs.getString("pro_sale")));
                            product.setPro_image(img);
                            product.setMadein(rs.getString("madein"));
                            product.setDescription(rs.getString("description"));

                            BrandDAO brandDAO = new BrandDAO();
                            ResultSet brandList = brandDAO.getBrandById(rs.getString("brand_id"));
                            try {
                                if (brandList != null && brandList.next()) {
                                    request.setAttribute("brandName", brandList.getString("brand_name"));
                                }
                            } finally {
                                if (brandList != null) {
                                    brandList.close();
                                }
                            }

                            request.setAttribute("product", product);
                        }
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                }

                ResultSet proDetail = productDAO.getAllProductDetailByProId(proId);
                List<Product_Details> productDetails = new ArrayList<>();

                try {
                    int countImg = 0;
                    while (proDetail != null && proDetail.next() && countImg < 4) {
                        int proDetailId = Integer.parseInt(proDetail.getString("proDetail_id"));
                        String image = proDetail.getString("image");

                        // Thêm ProductDetail vào danh sách
                        Product_Details detail = new Product_Details(proDetailId, image);
                        productDetails.add(detail);

                        countImg++;
                    }
                    // Nếu chưa đủ 4 hình ảnh thì thêm ảnh mặc định
                    while (countImg < 4 && img != null) {
                        productDetails.add(new Product_Details(-1, img));
                        countImg++;
                    }

                    request.setAttribute("productDetails", productDetails);
                } finally {
                    if (proDetail != null) {
                        proDetail.close();
                    }
                }

                request.getRequestDispatcher("user_products.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("user_products.jsp").forward(request, response);
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
