/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Product;
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
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try (ResultSet rs = pst.executeQuery()) {
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
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try (ResultSet rs = pst.executeQuery()) {
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

    public List<Models.Product> getProductDecreaseByCatId(String id) {
        Connection conn = DBConnection.getConnection();
        List<Models.Product> productList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT * FROM Product p LEFT JOIN Product_Details pd ON p.pro_id = pd.pro_id  WHERE pd.color_name = 'default' and cat_id = ? ORDER BY COALESCE(pro_sale, pro_price) DESC";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try (ResultSet rs = pst.executeQuery()) {
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
            try (PreparedStatement pst = conn.prepareStatement(query)) {

                try (ResultSet rs = pst.executeQuery()) {
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
            try (PreparedStatement pst = conn.prepareStatement(query)) {

                try (ResultSet rs = pst.executeQuery()) {
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

}
