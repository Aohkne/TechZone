/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public ResultSet getProductBySearch(String search) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product WHERE LOWER(pro_name) LIKE '" + search + "%'");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductByBrandId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product WHERE cat_id = " + id);
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductIncreaseByCatId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product WHERE cat_id = " + id + " ORDER BY COALESCE(pro_sale, pro_price) ASC");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductDecreaseByCatId(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product WHERE cat_id = " + id + " ORDER BY COALESCE(pro_sale, pro_price) DESC");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductIncrease() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product ORDER BY COALESCE(pro_sale, pro_price) ASC");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductDecrease() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product ORDER BY COALESCE(pro_sale, pro_price) DESC");
            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
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
