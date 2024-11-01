/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import static DB.DBConnection.getConnection;
import Models.Product;
import Models.Product_Details;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class ProductDAO {

    //ProDuct    
    public ResultSet getAllProductByDetailId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Product where pro_id = " + id);

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductByIdSale(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Product where pro_id = " + id + " AND pro_sale IS NOT NULL");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllProducts() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public List<Product> getAllDefaultProducts() {
        Connection conn = DBConnection.getConnection();
        List<Product> products = new ArrayList<>();

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Product_Details WHERE color_name = 'default'");
                while (rs.next()) {
                    int id = Integer.parseInt(rs.getString("proDetail_id"));
                    String image = rs.getString("image");
                    String proId = rs.getString("pro_id");

                    ResultSet rsProduct = getProductByIdNotSale(proId);
                    while (rsProduct.next()) {
                        String name = rsProduct.getString("pro_name");
                        String price = rsProduct.getString("pro_price");
                        String formattedPrice = formatPrice(price);

                        Product product = new Product(id, name, formattedPrice, image, id);
                        products.add(product);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return products;
    }

    public String formatPrices(String price) {
        // Kiểm tra nếu price là null hoặc rỗng
        if (price == null || price.isEmpty()) {
            return ""; // Trả về giá trị mặc định nếu giá trị price không hợp lệ
        }

        // Tách phần nguyên và phần thập phân (nếu có)
        String[] parts = price.split("\\.");
        String integerPart = parts[0]; // Phần nguyên của giá

        StringBuilder result = new StringBuilder();
        int count = 0;

        // Định dạng phần nguyên: Duyệt từ cuối chuỗi tới đầu để thêm dấu "." mỗi ba chữ số
        for (int i = integerPart.length() - 1; i >= 0; i--) {
            if (count == 3) {
                result.append(".");
                count = 0; // Đặt lại count về 0 sau khi thêm dấu "."
            }
            result.append(integerPart.charAt(i));
            count++;
        }

        // Đảo ngược chuỗi để có kết quả cuối cùng
        return result.reverse().toString();
    }

    public List<Models.Product> getProductBySearch(String search) {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            try {

                String query = "SELECT p.*, pd.* FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id WHERE pd.color_name = 'default' AND LOWER(p.pro_name) LIKE ?";

                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, "%" + search.toLowerCase() + "%");

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {

                    Product product = new Product();
                    product.setPro_id(rs.getInt("pro_id"));
                    product.setPro_name(rs.getString("pro_name"));
                    product.setPro_price(formatPrices(rs.getString("pro_price")));
                    product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                    product.setPro_image(rs.getString("image"));
                    product.setCat_id(rs.getInt("cat_id"));
                    product.setProDetail_id(rs.getInt("proDetail_id"));
                    productList.add(product);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productList;
    }

    public List<Models.Product> getProductByBrandId(String id) {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT p.*, pd.* FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id WHERE (pd.color_name = 'default' and p.cat_id = ?)";
            try ( PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try ( ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        // Khởi tạo đối tượng Product từ dữ liệu trong ResultSet
                        Product product = new Product();
                        product.setPro_id(rs.getInt("pro_id"));
                        product.setPro_name(rs.getString("pro_name"));
                        product.setPro_price(formatPrices(rs.getString("pro_price")));
                        product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                        product.setPro_image(rs.getString("image"));
                        product.setCat_id(rs.getInt("cat_id"));
                        product.setProDetail_id(rs.getInt("proDetail_id"));

                        // Thêm product vào danh sách
                        productList.add(product);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    public List<Models.Product> getProductIncreaseByCatId(String id) {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT * FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id WHERE pd.color_name = 'default' and p.cat_id = ? ORDER BY COALESCE(p.pro_sale, p.pro_price) ASC";
            try ( PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try ( ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        // Khởi tạo đối tượng Product từ dữ liệu trong ResultSet
                        Product product = new Product();
                        product.setPro_id(rs.getInt("pro_id"));
                        product.setPro_name(rs.getString("pro_name"));
                        product.setPro_price(formatPrices(rs.getString("pro_price")));
                        product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                        product.setPro_image(rs.getString("image"));
                        product.setCat_id(rs.getInt("cat_id"));
                        product.setProDetail_id(rs.getInt("proDetail_id"));

                        // Thêm product vào danh sách
                        productList.add(product);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    public void updateProductQuantity(int proDetailId, int quantityToReduce) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            try {
                String selectSql = "SELECT quantity FROM Product_Details WHERE proDetail_id = ?";
                PreparedStatement selectPst = conn.prepareStatement(selectSql);
                selectPst.setInt(1, proDetailId);

                ResultSet rs = selectPst.executeQuery();
                if (rs.next()) {
                    int currentQuantity = rs.getInt("quantity");

                    if (currentQuantity > quantityToReduce) {
                        String updateSql = "UPDATE Product_Details SET quantity = quantity - ? WHERE proDetail_id = ?";
                        PreparedStatement updatePst = conn.prepareStatement(updateSql);
                        updatePst.setInt(1, quantityToReduce);
                        updatePst.setInt(2, proDetailId);
                        updatePst.executeUpdate();
                    } else if (currentQuantity <= quantityToReduce) {
                        System.out.println("Cannot reduce quantity. Current quantity is " + currentQuantity + ", requested reduction is " + quantityToReduce);
                    }
                } else {
                    System.out.println("No product detail found with proDetail_id: " + proDetailId);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Always close the connection and statements to avoid leaks
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
    }

    public List<Models.Product> getProductDecreaseByCatId(String id) {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT * FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id  WHERE pd.color_name = 'default' and cat_id = ? ORDER BY COALESCE(pro_sale, pro_price) DESC";
            try ( PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try ( ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        // Khởi tạo đối tượng Product từ dữ liệu trong ResultSet
                        Product product = new Product();
                        product.setPro_id(rs.getInt("pro_id"));
                        product.setPro_name(rs.getString("pro_name"));
                        product.setPro_price(formatPrices(rs.getString("pro_price")));
                        product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                        product.setPro_image(rs.getString("image"));
                        product.setCat_id(rs.getInt("cat_id"));
                        product.setProDetail_id(rs.getInt("proDetail_id"));

                        // Thêm product vào danh sách
                        productList.add(product);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    public List<Models.Product> getProductIncrease() {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT * FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id where pd.color_name = 'default' ORDER BY COALESCE(pro_sale, pro_price) ASC";
            try ( PreparedStatement pst = conn.prepareStatement(query)) {

                try ( ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        // Khởi tạo đối tượng Product từ dữ liệu trong ResultSet
                        Product product = new Product();
                        product.setPro_id(rs.getInt("pro_id"));
                        product.setPro_name(rs.getString("pro_name"));
                        product.setPro_price(formatPrices(rs.getString("pro_price")));
                        product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                        product.setPro_image(rs.getString("image"));
                        product.setCat_id(rs.getInt("cat_id"));
                        product.setProDetail_id(rs.getInt("proDetail_id"));

                        // Thêm product vào danh sách
                        productList.add(product);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    public List<Models.Product> getProductDecrease() {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT * FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id where pd.color_name = 'default' ORDER BY COALESCE(pro_sale, pro_price) DESC";
            try ( PreparedStatement pst = conn.prepareStatement(query)) {

                try ( ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        // Khởi tạo đối tượng Product từ dữ liệu trong ResultSet
                        Product product = new Product();
                        product.setPro_id(rs.getInt("pro_id"));
                        product.setPro_name(rs.getString("pro_name"));
                        product.setPro_price(formatPrices(rs.getString("pro_price")));
                        product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                        product.setPro_image(rs.getString("image"));
                        product.setCat_id(rs.getInt("cat_id"));
                        product.setProDetail_id(rs.getInt("proDetail_id"));

                        // Thêm product vào danh sách
                        productList.add(product);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return productList;
    }

    //Product Detail
    public ResultSet getAllDefaultProduct() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product_Details WHERE color_name = 'default'");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllProductDetailById(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product_Details WHERE proDetail_id = " + id);
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllDefaultProductlById(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product_Details Where proDetail_id =" + id + "AND color_name = 'default'");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllProductDetailByProId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product_Details WHERE pro_id = " + id);
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllDefaultProductDetailByProId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product_Details WHERE pro_id = " + id + "AND color_name = 'default'");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllNotDefaultProductlByProId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product_Details Where pro_id =" + id + "AND color_name != 'default'");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public String formatPrice(String price) {
        price = price.substring(0, price.length() - 3);
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = price.length() - 1; i >= 0; i--) {
            if (count == 3) {
                result.append("." + price.charAt(i));
                count = 1;
            } else {
                result.append(price.charAt(i));
                count++;
            }
        }
        return result.reverse().toString();
    }

    public ResultSet getProductByIdNotSale(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Product where pro_id = " + id + " AND pro_sale IS NULL");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public List<Product> getAllFlashSaleProducts() {
        Connection conn = DBConnection.getConnection();
        List<Product> products = new ArrayList<>();
        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                ResultSet rsProductDetails = st.executeQuery("SELECT * FROM Product_Details WHERE color_name = 'default'");
                while (rsProductDetails.next()) {
                    int id = Integer.parseInt(rsProductDetails.getString("proDetail_id"));
                    String pro_id = rsProductDetails.getString("pro_id");
                    String img = rsProductDetails.getString("image");

                    ResultSet rs = getProductByIdSale(pro_id);
                    while (rs.next()) {
                        String name = rs.getString("pro_name");
                        String price = rs.getString("pro_price");
                        String sale = rs.getString("pro_sale");
                        String formattedPrice = formatPrice(price);
                        String formattedSale = formatPrice(sale);

                        products.add(new Product(id, name, formattedPrice, formattedSale, img));

                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return products;
    }

    public int GetTotalProduct() {
        Connection conn = DBConnection.getConnection();
        int userCount = 0; // Change variable name to better reflect its purpose
        if (conn != null) {
            try {
                // Query to count users with role=2
                String sql = "SELECT Sum(quantity) FROM Product_Details";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    userCount = rs.getInt(1); // Get the count of users
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userCount;
    }

    public List<Models.Product> getAllProduct() {
        List<Models.Product> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                // Thực thi truy vấn SQL để lấy người dùng với role = 2
                rs = st.executeQuery("SELECT p.pro_id,p.pro_name, p.pro_price, p.pro_sale, p.madein, p.cat_id, p.brand_id, pd.proDetail_id, pd.color_name, pd.quantity, pd.image FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Models.Product user = new Product(); // Tạo một đối tượng Users mới
                    user.setPro_id(rs.getInt("pro_id"));//1
                    user.setPro_name(rs.getString("pro_name"));//2
                    user.setPro_price(formatPrices(rs.getString("pro_price"))); // Thiết lập username3
                    user.setPro_sale(formatPrices(rs.getString("pro_sale"))); // Thiết lập email4
                    user.setPro_quantity(rs.getInt("quantity")); // Thiết lập phone5
                    user.setMadein(rs.getString("madein")); // Thiết lập address6
                    user.setPro_image(rs.getString("image")); // Thiết lập ngày tạo7
                    user.setCat_id(rs.getInt("cat_id"));//8
                    user.setBrand_id(rs.getString("brand_id"));//9
                    user.setProDetail_id(rs.getInt("proDetail_id"));//10
                    user.setColor_name(rs.getString("color_name"));

                    // Thêm người dùng vào danh sách
                    userList.add(user);
                }
            } catch (SQLException ex) {
                // Log lỗi

            } finally {
                // Đóng ResultSet và Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    // Log lỗi khi đóng

                }
            }
        }
        return userList; // Trả về danh sách người dùng
    }

    public List<Product> searchProduct(String query) {
        List<Product> productList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                String sql;
                PreparedStatement pst;

                // Kiểm tra nếu query không trống
                if (query != null && !query.trim().isEmpty()) {
                    sql = "SELECT p.pro_id, p.pro_name, p.pro_price, p.pro_sale, p.madein, p.cat_id, p.brand_id, pd.color_name, pd.quantity, pd.image "
                            + "FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id "
                            + "WHERE (LOWER(p.pro_name) LIKE LOWER(?) OR LOWER(p.pro_price) LIKE LOWER(?) OR LOWER(p.madein) LIKE LOWER(?))";
                    pst = conn.prepareStatement(sql);
                    String searchQuery = "%" + query.toLowerCase() + "%"; // Thêm ký tự wildcard và chuyển query thành chữ thường
                    pst.setString(1, searchQuery);
                    pst.setString(2, searchQuery);
                    pst.setString(3, searchQuery);
                } else {
                    // Nếu không có từ khóa tìm kiếm, lấy tất cả sản phẩm
                    sql = "SELECT p.pro_id, p.pro_name, p.pro_price, p.pro_sale, p.madein, p.cat_id, p.brand_id, pd.color_name, pd.quantity, pd.image "
                            + "FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id";
                    pst = conn.prepareStatement(sql);
                }

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    Product product = new Product();
                    product.setPro_id(rs.getInt("pro_id"));
                    product.setPro_name(rs.getString("pro_name"));
                    product.setPro_price(formatPrices(rs.getString("pro_price")));
                    product.setPro_sale(formatPrices(rs.getString("pro_sale")));
                    product.setMadein(rs.getString("madein"));
                    product.setCat_id(rs.getInt("cat_id"));
                    product.setBrand_id(rs.getString("brand_id"));
                    product.setColor_name(rs.getString("color_name"));
                    product.setPro_quantity(rs.getInt("quantity"));
                    product.setPro_image(rs.getString("image"));
                    productList.add(product);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productList;
    }

    public List<Models.Product> getAllProductsSorted() {
        List<Models.Product> productList = new ArrayList<>(); // Tạo danh sách để lưu trữ sản phẩm
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                // Thực thi truy vấn SQL để lấy sản phẩm và sắp xếp theo ID giảm dần
                rs = st.executeQuery("SELECT pd.proDetail_id, p.pro_name, p.pro_price, p.pro_sale, p.madein, p.cat_id, p.brand_id, pd.color_name, pd.quantity, pd.image "
                        + "FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id "
                        + "ORDER BY p.pro_id DESC");

                // Duyệt qua ResultSet và tạo đối tượng Product
                while (rs.next()) {
                    Models.Product product = new Models.Product(); // Tạo một đối tượng Product mới
                    product.setProDetail_id(rs.getInt("proDetail_id")); // Thiết lập ID sản phẩm
                    product.setPro_name(rs.getString("pro_name")); // Thiết lập tên sản phẩm
                    product.setPro_price(formatPrices(rs.getString("pro_price"))); // Thiết lập giá sản phẩm
                    product.setPro_sale(formatPrices(rs.getString("pro_sale"))); // Thiết lập giá sale
                    product.setPro_quantity(rs.getInt("quantity")); // Thiết lập số lượng
                    product.setMadein(rs.getString("madein")); // Thiết lập nơi sản xuất
                    product.setCat_id(rs.getInt("cat_id")); // Thiết lập ID danh mục
                    product.setBrand_id(rs.getString("brand_id")); // Thiết lập ID thương hiệu
                    product.setColor_name(rs.getString("color_name")); // Thiết lập màu sắc
                    product.setPro_image(rs.getString("image")); // Thiết lập ảnh sản phẩm

                    // Thêm sản phẩm vào danh sách
                    productList.add(product);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log lỗi
            } finally {
                // Đóng ResultSet và Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Log lỗi khi đóng
                }
            }
        }
        return productList; // Trả về danh sách sản phẩm
    }

    public void addProduct(Product product, Product_Details productDetail) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                // Insert product into Product table
                String insertProductSQL = "INSERT INTO Product (pro_name, description, pro_price, pro_sale, madein, cat_id, brand_id) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS); // Return generated keys
                pst.setString(1, product.getPro_name());
                pst.setString(2, product.getDescription());
                pst.setString(3, product.getPro_price());
                pst.setString(4, product.getPro_sale());
                pst.setString(5, product.getMadein());
                pst.setInt(6, product.getCat_id());
                pst.setString(7, product.getBrand_id());

                pst.executeUpdate();

                // Retrieve generated product ID
                ResultSet rs = pst.getGeneratedKeys();
                int proId = 0;
                if (rs.next()) {
                    proId = rs.getInt(1); // Get the generated pro_id
                }

                // Insert product details into Product_Details table
                String insertProductDetailSQL = "INSERT INTO Product_Details (color_name, quantity, image, pro_id) "
                        + "VALUES (?, ?, ?, ?)";
                PreparedStatement pstDetail = conn.prepareStatement(insertProductDetailSQL);
                pstDetail.setString(1, productDetail.getColor_name());
                pstDetail.setInt(2, productDetail.getQuantity());
                pstDetail.setString(3, productDetail.getImage());
                pstDetail.setInt(4, proId); // Use the retrieved pro_id

                pstDetail.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection after the operation
                } catch (SQLException e) {
                }
            }
        }
    }

    public int getLastProductId() {
        Connection conn = DBConnection.getConnection();
        int lastProductId = 0; // Change variable name to better reflect its purpose
        if (conn != null) {
            try {
                // Query to get the most recently added product id
                String sql = "SELECT pro_id FROM Product ORDER BY pro_id DESC LIMIT 1";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    lastProductId = rs.getInt(1); // Get the last product ID
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection
                } catch (SQLException e) {
                }
            }
        }
        return lastProductId;
    }

    public void editProductDetails(Product_Details productDetails) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                // Update the product details in the Product_Details table
                String updateProductDetailSQL = "UPDATE Product_Details SET quantity = ? WHERE proDetail_id = ?";
                PreparedStatement pstDetail = conn.prepareStatement(updateProductDetailSQL);
                pstDetail.setInt(1, productDetails.getQuantity());
                pstDetail.setInt(2, productDetails.getProDetail_id()); // Use proDetail_id from productDetails

                pstDetail.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection after the operation
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getProIdFromDetails(int proDetail_id) {
        Connection conn = DBConnection.getConnection();
        int proId = 0; // Store the product ID related to the given proDetail_id

        if (conn != null) {
            try {
                // Query to get the pro_id from Product_Details where proDetail_id matches
                String sql = "SELECT pro_id FROM Product_Details WHERE proDetail_id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, proDetail_id); // Set the proDetail_id parameter

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    proId = rs.getInt("pro_id"); // Get the pro_id from the result set
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return proId; // Return the fetched pro_id
    }
    // Method to update Product table

    public Double parseFormattedStringToDouble(String value) {
        if (value == null || value.isEmpty()) {
            return null; // Handle null or empty input gracefully
        }

        try {
            // Remove all periods used as thousand separators
            String normalizedValue = value.replace(".", "");
            // Convert the cleaned-up value to double
            Double doubleValue = Double.parseDouble(normalizedValue);

            // Round to 2 decimal places using BigDecimal for precision
            BigDecimal bd = new BigDecimal(doubleValue);
            bd = bd.setScale(2, RoundingMode.HALF_UP);

            return bd.doubleValue();
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Print the stack trace in case of an error
            return null; // Return null if parsing fails
        }
    }

    public void editProduct(Product product, int proId) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                double a = parseFormattedStringToDouble(product.getPro_price());
                double b = parseFormattedStringToDouble(product.getPro_sale());
                // Update the product in the Product table
                String updateProductSQL = "UPDATE Product SET pro_name = ?, pro_price = ?, pro_sale = ?, madein = ? WHERE pro_id = ?";
                PreparedStatement pst = conn.prepareStatement(updateProductSQL);
                pst.setString(1, product.getPro_name());
                pst.setDouble(2, a);
                pst.setDouble(3, b);
                pst.setString(4, product.getMadein());
                pst.setInt(5, proId); // Assuming pro_id is stored in the product object

                pst.executeUpdate();

            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public int getCountProId(int proDetail_id) {
        Connection conn = DBConnection.getConnection();
        int count = 0; // Initialize the variable to store the count

        if (conn != null) {
            try {
                // Query to count the pro_id from Product_Details where proDetail_id matches
                String sql = "SELECT COUNT(pro_id) FROM Product_Details WHERE proDetail_id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, proDetail_id); // Set the proDetail_id parameter

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1); // Get the count from the result set (first column)
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count; // Return the count of pro_id
    }

    public void deleteProductDetails(int proDetail_id) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                // Delete the product details from the Product_Details table based on proDetail_id
                String deleteProductDetailSQL = "DELETE FROM Product_Details WHERE proDetail_id = ?";
                PreparedStatement pstDetail = conn.prepareStatement(deleteProductDetailSQL);
                pstDetail.setInt(1, proDetail_id); // Set the proDetail_id for the deletion

                pstDetail.executeUpdate(); // Execute the deletion query

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection after the operation
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteProduct(int proId) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                // Delete the product from the Product table based on pro_id
                String deleteProductSQL = "DELETE FROM Product WHERE pro_id = ?";
                PreparedStatement pst = conn.prepareStatement(deleteProductSQL);
                pst.setInt(1, proId); // Set the proId parameter for deletion

                pst.executeUpdate(); // Execute the delete query

            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); // Close the connection after the operation
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Models.Product> getAllProductDefault() {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> products = new ArrayList<>();

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT pro_id, pro_name FROM Product");

                while (rs.next()) {
                    int proId = rs.getInt("pro_id"); // Using rs.getInt for pro_id
                    String name = rs.getString("pro_name");

                    // Create Product object and add to list
                    Product product = new Product(proId, name);
                    products.add(product);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // Close the connection after the operation
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }

    public void addProductColor(Product_Details productDetail, int proId) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstDetail = null;

        if (conn != null) {
            try {
                System.out.println("ll" + proId);
                // Insert product details into Product_Details table
                String insertProductDetailSQL = "INSERT INTO Product_Details (color_name, quantity, image, pro_id) "
                        + "VALUES (?, ?, ?, ?)";
                pstDetail = conn.prepareStatement(insertProductDetailSQL);
                pstDetail.setString(1, productDetail.getColor_name());
                pstDetail.setInt(2, productDetail.getQuantity());
                pstDetail.setString(3, productDetail.getImage());
                pstDetail.setInt(4, proId); // Use the retrieved pro_id

                pstDetail.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstDetail != null) {
                        pstDetail.close(); // Close PreparedStatement
                    }
                    if (conn != null) {
                        conn.close(); // Close the connection after the operation
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
