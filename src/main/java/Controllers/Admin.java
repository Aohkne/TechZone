/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.BrandDAO;
import DAOs.CategoryDAO;
import DAOs.ProductDAO;
import Models.Product_Details;
import Models.Users;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
@WebServlet("/Admin/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class Admin extends HttpServlet {

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
            out.println("<title>Servlet Admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
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

        // Nếu đường dẫn là "/Admin", hiển thị trang quản trị
        if (path.equals("/Admin") || path.equals("/Admin/Dashboard")) {
            AccountDAO dao = new AccountDAO();
            int userId = -1;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id")) {
                        userId = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            String name = dao.GetNameAdmin(userId);
            int counts = dao.GetTotalUser();

            request.setAttribute("countUser", counts);
            request.setAttribute("name", name);
            request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
        } else if (path.equals("/Admin/Users")) {
            AccountDAO dao = new AccountDAO();
            int userId = -1;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id")) {
                        userId = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            String name = dao.GetNameAdmin(userId);
            int counts = dao.GetTotalUser();

            request.setAttribute("countUser", counts);
            request.setAttribute("name", name);

            List<Users> searchResults = (List<Users>) request.getAttribute("searchResults");
            List<Users> sortResults = (List<Users>) request.getAttribute("sortResults");
            List<Users> allUsers = new ArrayList<>();

            if (searchResults != null) {
                allUsers = searchResults;
            } else if (sortResults != null) {
                allUsers = sortResults;

            } else {

                allUsers = dao.getAllUser();
            }

            Map<Integer, Boolean> verifiedEmails = new HashMap<>();
            for (Users user : allUsers) {
                verifiedEmails.put(user.getUser_id(), dao.getVerifyByEmail(user.getUser_id()));
            }

            request.setAttribute("allUsers", allUsers);
            request.setAttribute("verifiedEmails", verifiedEmails);
            request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
        } else if (path.equals("/Admin/Product")) {
            AccountDAO dao = new AccountDAO();
            ProductDAO daos = new ProductDAO();
            int userId = -1;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id")) {
                        userId = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            String name = dao.GetNameAdmin(userId);
            int countProduct = daos.GetTotalProduct();

            request.setAttribute("countProduct", countProduct);
            request.setAttribute("name", name);

            List<Models.Product> searchResults = (List<Models.Product>) request.getAttribute("searchResults");
            List<Models.Product> sortResults = (List<Models.Product>) request.getAttribute("sortResults");
            List<Models.Product> allProduct = new ArrayList<>();

            if (searchResults != null) {
                allProduct = searchResults;
            } else if (sortResults != null) {
                allProduct = sortResults;

            } else {

                allProduct = daos.getAllProduct();
            }

            CategoryDAO catDao = new CategoryDAO();
            List<Models.Category> catDaoName = catDao.GetAllCategory();
            request.setAttribute("nameCat", catDaoName);

            BrandDAO brandDao = new BrandDAO();
            List<Models.Brand> brandDaoName = brandDao.GetAllBrand();
            request.setAttribute("nameBrand", brandDaoName);

            List<Models.Product> productDaoName = daos.getAllProductDefault();
            request.setAttribute("productDaoName", productDaoName);

            request.setAttribute("allProduct", allProduct);
            request.getRequestDispatcher("/admin_products.jsp").forward(request, response);
        } else if (path.equals("/Admin/Review")) {
            request.getRequestDispatcher("/admin_reviews.jsp").forward(request, response);
        } else if (path.equals("/Admin/Profile")) {
            AccountDAO dao = new AccountDAO();
            int userId = -1;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id")) {
                        userId = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            String name = dao.GetNameAdmin(userId);
            // Gọi phương thức getAllInfo và lưu kết quả vào biến user
            List<Users> userInfo = dao.getAllInfo(userId);

            request.setAttribute("user", userInfo);
            request.setAttribute("id", userId);
            request.setAttribute("name", name);
            request.getRequestDispatcher("/admin_profile.jsp").forward(request, response);
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

        if (request.getParameter("btnsearchUser") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            AccountDAO dao = new AccountDAO();
            List<Users> searchResults = dao.searchUsers(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        } else if (request.getParameter("userId") != null && request.getParameter("verified") != null) {
            // Cập nhật trạng thái tài khoản
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean verified = Boolean.parseBoolean(request.getParameter("verified"));
            AccountDAO dao = new AccountDAO();
            // Cập nhật trạng thái trong DB
            dao.setVerifiedEmail(userId, verified);

            // Phản hồi JSON để JavaScript có thể xử lý
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"success\"}");
        } else if (request.getParameter("btnSort") != null) {
            AccountDAO dao = new AccountDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Users> sortResults = dao.getAllUsersSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        } else if (request.getParameter("btnsearchProduct") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            ProductDAO dao = new ProductDAO();
            List<Models.Product> searchResults = dao.searchProduct(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        } else if (request.getParameter("btnSortProduct") != null) {
            ProductDAO dao = new ProductDAO();
            // Logic hiển thị danh sách thương hiệu (brand) theo thứ tự ngược lại
            List<Models.Product> sortResults = dao.getAllProductsSorted(); // Gọi DAO để lấy danh sách thương hiệu đã sắp xếp

            // Đặt danh sách thương hiệu đã được sắp xếp vào request attribute
            request.setAttribute("sortResults", sortResults);

            doGet(request, response);
        } else if (request.getParameter("btnAddProduct") != null) {

            try {
                // Lấy dữ liệu từ request
                String pro_name = request.getParameter("pro_name");
                String description = request.getParameter("description");
                String pro_price = request.getParameter("pro_price");
                String pro_sale = request.getParameter("pro_sale");
                String madein = request.getParameter("madein");
                int cat_id = Integer.parseInt(request.getParameter("cat_id"));
                String brand_id = request.getParameter("brand_id");
                String color_name = request.getParameter("color_name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Lấy tệp tin ảnh từ form
                Part filePart = request.getPart("image"); // Lấy phần tệp tin từ form
                String fileName = extractFileName(filePart); // Hàm để lấy tên tệp tin
                // Định nghĩa đường dẫn lưu trữ ảnh
                String uploadPath = getServletContext().getRealPath("") + File.separator + "asset" + File.separator + "img" + File.separator + "img_all" + File.separator + "img_product";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                // Ghi tệp tin lên server
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                // Tạo đối tượng Product và Product_Details
                Models.Product product = new Models.Product();
                product.setPro_name(pro_name);
                product.setDescription(description);
                product.setPro_price(pro_price);
                product.setPro_sale(pro_sale);
                product.setMadein(madein);
                product.setCat_id(cat_id);
                product.setBrand_id(brand_id);

                Product_Details productDetail = new Product_Details();
                productDetail.setColor_name(color_name);
                productDetail.setQuantity(quantity);
                productDetail.setImage("./asset/img/img_all/img_product/" + fileName);

                // Thêm sản phẩm và chi tiết sản phẩm vào cơ sở dữ liệu
                ProductDAO productDAO = new ProductDAO();
                productDAO.addProduct(product, productDetail);

                // Redirect đến trang quản lý
                response.sendRedirect("/Admin/Product");
            } catch (Exception e) {
                // Xử lý lỗi, ví dụ redirect đến trang thông báo lỗi
                response.sendRedirect("/Error");
            }
        } else if (request.getParameter("btnEditProduct") != null) {
            // Get parameters for Product
            String pro_name = request.getParameter("pro_name");
            String pro_price = request.getParameter("pro_price");
            String pro_sale = request.getParameter("pro_sale");
            String madein = request.getParameter("madein");

            // Get parameters for Product_Details
            int proDetail_id = Integer.parseInt(request.getParameter("proDetail_id")); // Product_Details ID
            int quantity = Integer.parseInt(request.getParameter("quantity")); // Quantity for Product_Details

            // Create Product object for updating Product data
            Models.Product product = new Models.Product(pro_name, pro_price, pro_sale, madein);
            Models.Product_Details products = new Models.Product_Details(proDetail_id, quantity);
            // Update Product and Product_Details in the DAO
            ProductDAO dao = new ProductDAO();
            dao.editProductDetails(products);

            int proId = dao.getProIdFromDetails(proDetail_id);

            dao.editProduct(product, proId); // A method that will update both tables
            // Redirect after updating
            response.sendRedirect("/Admin/Product");
        } else if (request.getParameter("btnDeleteProduct") != null) {
            try {
                // Lấy brand_id từ request và chuyển thành số nguyên
                int proDetail_id = Integer.parseInt(request.getParameter("proDetail_id"));

                // Tạo một instance của BrandDAO
                ProductDAO dao = new ProductDAO();

                int count = dao.getCountProId(proDetail_id);

                if (count == 1) {
                    int proId = dao.getProIdFromDetails(proDetail_id);
                    dao.deleteProductDetails(proDetail_id);

                    dao.deleteProduct(proId);
                } else {
                    dao.deleteProductDetails(proDetail_id);
                }
                // Nếu xóa thành công, chuyển hướng về trang quản lý Brand
                response.sendRedirect("/Admin/Product");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu brand_id không hợp lệ
                request.setAttribute("errorMessage", "Invalid Brand ID");
                response.sendRedirect("/Error");
            }
        } else if (request.getParameter("btnAddColor") != null) {

            try {
                int pro_id = Integer.parseInt(request.getParameter("pro_id"));
                String color_name = request.getParameter("color_name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Lấy tệp tin ảnh từ form
                Part filePart = request.getPart("image"); // Lấy phần tệp tin từ form
                String fileName = extractFileName(filePart); // Hàm để lấy tên tệp tin
                // Định nghĩa đường dẫn lưu trữ ảnh
                String uploadPath = getServletContext().getRealPath("") + File.separator + "asset" + File.separator + "img" + File.separator + "img_all" + File.separator + "img_product";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                // Ghi tệp tin lên server
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                Product_Details productDetail = new Product_Details();
                productDetail.setColor_name(color_name);
                productDetail.setQuantity(quantity);
                productDetail.setImage("./asset/img/img_all/img_product/" + fileName);

                // Thêm sản phẩm và chi tiết sản phẩm vào cơ sở dữ liệu
                ProductDAO productDAO = new ProductDAO();
                productDAO.addProductColor(productDetail, pro_id);

                // Redirect đến trang quản lý
                response.sendRedirect("/Admin/Product");
            } catch (Exception e) {
                // Xử lý lỗi, ví dụ redirect đến trang thông báo lỗi
                response.sendRedirect("/Error");
            }
        } else if (request.getParameter("btnSaveInfo") != null) {
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            String username = request.getParameter("username");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            // Nếu số điện thoại hợp lệ, tiếp tục cập nhật
            AccountDAO dao = new AccountDAO();
            dao.updateUserInfo(user_id, username, phone, address);

            // Có thể thêm thông báo cập nhật thành công nếu muốn
            response.sendRedirect("/Admin/Profile");
        } else if (request.getParameter("btnNewPass") != null) {
    try {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String old = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");

        // AccountDAO instance to interact with database
        AccountDAO dao = new AccountDAO();
        String pd = dao.checkOldPassword(user_id);
        String hashedOldPassword = dao.md5Hash(old);
        String hashedNewPassword = dao.md5Hash(newPass);

        HttpSession session = request.getSession(); // Obtain session object to store messages

        System.out.println("Old password from DB: " + pd);
        System.out.println("New hashed password: " + hashedOldPassword);

        // Validate the old password and check the new password
        if (pd == null || !pd.equals(hashedOldPassword)) {
            // Old password is incorrect
            session.setAttribute("error", "Old password is incorrect");
        } else if (pd.equals(hashedNewPassword)) {
            // New password must be different from the old password
            session.setAttribute("error", "New password must be different from the old password");
        } else {
            // Update password in the database and set success message
            dao.updatePassword(user_id, hashedNewPassword);
            session.setAttribute("success", "Password updated successfully");
        }

        response.sendRedirect("/Admin/Profile"); // Redirect to Profile page

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    }
    // Hàm để lấy tên tệp tin từ phần tệp (Part)

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");

        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                return new File(fileName).getName(); // Trả về tên tệp không chứa đường dẫn
            }
        }
        return null; // Trả về null nếu không tìm thấy tên tệp
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
