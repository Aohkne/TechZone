/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class BrandDAO {

    public ResultSet getAllBrand() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Brand");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getBrandById(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Brand where brand_id = " + id);

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public int GetTotalBrand() {
        Connection conn = DBConnection.getConnection();
        int userCount = 0; // Change variable name to better reflect its purpose
        if (conn != null) {
            try {
                // Query to count users with role=2
                String sql = "SELECT COUNT(*) FROM Brand";
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

    public List<Brand> GetAllBrand() {
        List<Brand> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Brand");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Brand user = new Brand(); // Tạo một đối tượng Users mới
                    //set id
                    user.setBrand_id(rs.getInt("brand_id"));
                    user.setBrand_name(rs.getString("brand_name"));
                    user.setDescription(rs.getString("description")); // Thiết lập username

                    // Thêm người dùng vào danh sách
                    userList.add(user);
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
        return userList; // Trả về danh sách người dùng
    }

    public List<Brand> searchBrand(String query) {
        List<Brand> userList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                String sql;
                PreparedStatement pst;

                // Kiểm tra nếu query không trống
                if (query != null && !query.trim().isEmpty()) {
                    sql = "SELECT * FROM Brand WHERE brand_name LIKE ? OR description LIKE ?";
                    pst = conn.prepareStatement(sql);
                    String searchQuery = "%" + query + "%"; // Thêm ký tự wildcard
                    pst.setString(1, searchQuery);
                    pst.setString(2, searchQuery);
                } else {
                    // Nếu không có từ khóa tìm kiếm, lấy tất cả người dùng
                    sql = "SELECT * FROM Brand";
                    pst = conn.prepareStatement(sql);
                }

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    Brand user = new Brand();
                    user.setBrand_id(rs.getInt("brand_id"));
                    user.setBrand_name(rs.getString("brand_name"));
                    user.setDescription(rs.getString("description"));
                    userList.add(user);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    public List<Brand> getAllBrandsSorted() {
        List<Brand> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Brand ORDER BY brand_id DESC");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Brand user = new Brand(); // Tạo một đối tượng Users mới
                    user.setBrand_id(rs.getInt("brand_id"));
                    user.setBrand_name(rs.getString("brand_name"));
                    user.setDescription(rs.getString("description")); // Thiết lập username

                    // Thêm người dùng vào danh sách
                    userList.add(user);
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
        return userList; // Trả về danh sách người dùng
    }

    public int createBrand(Brand newProduct) {
        Connection conn = DBConnection.getConnection();
        int count;
        try {
            String sql = "INSERT INTO Brand (brand_name, description) VALUES (?, ?);";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newProduct.getBrand_name());
            pst.setString(2, newProduct.getDescription());

            count = pst.executeUpdate();
        } catch (SQLException e) {
            count = 0;
        }
        return count;
    }

    public int editBrand(Brand newProduct) {
        Connection conn = DBConnection.getConnection();
        int count;
        try {
            // Corrected SQL query for updating brand information
            String sql = "UPDATE Brand SET brand_name = ?, description = ? WHERE brand_id = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newProduct.getBrand_name());   // Set brand_name parameter
            pst.setString(2, newProduct.getDescription());  // Set description parameter
            pst.setInt(3, newProduct.getBrand_id());        // Set brand_id parameter

            count = pst.executeUpdate(); // Execute the update and get the affected row count
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            count = 0; // Return 0 in case of an error
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Close the connection after the operation
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count; // Return the number of affected rows
    }

    public boolean deleteBrand(int brandId) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Kết nối tới database

            // Kiểm tra xem còn sản phẩm nào thuộc brand_id này không
            String checkSql = "SELECT COUNT(*) AS product_count FROM Product WHERE brand_id = ?";
            ps = conn.prepareStatement(checkSql);
            ps.setInt(1, brandId);
            rs = ps.executeQuery();

            if (rs.next() && rs.getInt("product_count") > 0) {
                // Nếu còn sản phẩm, trả về false và không thực hiện xóa
                return false;
            }

            // Nếu không còn sản phẩm, thực hiện xóa brand
            String deleteSql = "DELETE FROM Brand WHERE brand_id = ?";
            ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, brandId);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            // Đóng kết nối và PreparedStatement
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Brand> checkBrandName() {
        Connection conn = DBConnection.getConnection();
        List<Brand> brandList = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT brand_name FROM Brand");
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrand_name(rs.getString("brand_name"));
                brandList.add(brand);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log error
        }

        return brandList;
    }

}
