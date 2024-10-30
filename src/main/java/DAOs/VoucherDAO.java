/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Brand;
import Models.Category;
import Models.Voucher;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class VoucherDAO {

    public List<Voucher> getAllVoucher() {
        List<Voucher> voucherDetails = new ArrayList<>();

        // SQL query to join VoucherDetail and Voucher tables
        String sql = "SELECT vd.voucherDetail_id, vd.voucher_name, vd.voucher_quantity, vd.voucher_discount, "
                + "vd.voucher_date, vd.voucher_expire_date, vd.user_id, v.voucher_id, v.voucher_type, "
                + "v.voucher_img, v.voucher_description "
                + "FROM VoucherDetail vd "
                + "JOIN Voucher v ON vd.voucher_id = v.voucher_id";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Use the connection from your DBConnection class
            conn = DBConnection.getConnection();

            // Check if the connection is successful
            if (conn != null) {
                // Prepare the SQL statement
                stmt = conn.prepareStatement(sql);

                // Execute the query and get the result set
                rs = stmt.executeQuery();

                // Iterate through the result set and populate the list
                while (rs.next()) {
                    Voucher voucherDetail = new Voucher();

                    // Set values for VoucherDetail part
                    voucherDetail.setVoucherDetail_id(rs.getInt("voucherDetail_id"));
                    voucherDetail.setVoucher_name(rs.getString("voucher_name"));
                    voucherDetail.setVoucher_quantity(rs.getInt("voucher_quantity"));
                    voucherDetail.setVoucher_discount(rs.getInt("voucher_discount"));
                    voucherDetail.setVoucher_date(rs.getDate("voucher_date"));
                    voucherDetail.setVoucher_expire_date(rs.getDate("voucher_expire_date"));
                    voucherDetail.setUser_id(rs.getInt("user_id"));

                    // Set values for Voucher part
                    voucherDetail.setVoucher_id(rs.getInt("voucher_id"));
                    voucherDetail.setVoucher_type(rs.getString("voucher_type"));
                    voucherDetail.setVoucher_img(rs.getString("voucher_img"));
                    voucherDetail.setVoucher_description(rs.getString("voucher_description"));

                    // Add the voucherDetail to the list
                    voucherDetails.add(voucherDetail);
                }
            } else {
                System.out.println("Connection to database failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, Statement, and Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();  // Close the connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return voucherDetails;
    }

    public List<Voucher> getVouchersByUserId(String userId) {
        List<Voucher> voucherDetails = new ArrayList<>();

        // SQL query to join VoucherDetail and Voucher tables and filter by user_id
        String sql = "SELECT vd.voucherDetail_id, vd.voucher_name, vd.voucher_quantity, vd.voucher_discount, "
                + "vd.voucher_date, vd.voucher_expire_date, vd.user_id, v.voucher_id, v.voucher_type, "
                + "v.voucher_img, v.voucher_description "
                + "FROM VoucherDetail vd "
                + "JOIN Voucher v ON vd.voucher_id = v.voucher_id "
                + "WHERE vd.user_id = ?";  // Filter by user_id

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Use the connection from your DBConnection class
            conn = DBConnection.getConnection();

            // Check if the connection is successful
            if (conn != null) {
                // Prepare the SQL statement
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, userId);  // Set user_id parameter in the query

                // Execute the query and get the result set
                rs = stmt.executeQuery();

                // Iterate through the result set and populate the list
                while (rs.next()) {
                    Voucher voucherDetail = new Voucher();

                    // Set values for VoucherDetail part
                    voucherDetail.setVoucherDetail_id(rs.getInt("voucherDetail_id"));
                    voucherDetail.setVoucher_name(rs.getString("voucher_name"));
                    voucherDetail.setVoucher_quantity(rs.getInt("voucher_quantity"));
                    voucherDetail.setVoucher_discount(rs.getInt("voucher_discount"));
                    voucherDetail.setVoucher_date(rs.getDate("voucher_date"));
                    voucherDetail.setVoucher_expire_date(rs.getDate("voucher_expire_date"));
                    voucherDetail.setUser_id(rs.getInt("user_id"));

                    // Set values for Voucher part
                    voucherDetail.setVoucher_id(rs.getInt("voucher_id"));
                    voucherDetail.setVoucher_type(rs.getString("voucher_type"));
                    voucherDetail.setVoucher_img(rs.getString("voucher_img"));
                    voucherDetail.setVoucher_description(rs.getString("voucher_description"));

                    // Add the voucherDetail to the list
                    voucherDetails.add(voucherDetail);
                }
            } else {
                System.out.println("Connection to database failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, Statement, and Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();  // Close the connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return voucherDetails;
    }

    public int GetTotalVoucher() {
        Connection conn = DBConnection.getConnection();
        int totalVoucherQuantity = 0; // Changed variable name to better reflect its purpose
        if (conn != null) {
            try {
                // Query to sum the voucher_quantity column in VoucherDetail table
                String sql = "SELECT SUM(voucher_quantity) FROM VoucherDetail";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    totalVoucherQuantity = rs.getInt(1); // Get the total quantity of vouchers
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return totalVoucherQuantity;
    }

    public List<Models.Voucher> GetAllVoucher() {
        List<Models.Voucher> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery(
                        "SELECT vd.*, u.username, v.voucher_type "
                        + "FROM VoucherDetail vd "
                        + "JOIN Users u ON vd.user_id = u.user_id "
                        + "JOIN Voucher v ON v.voucher_id = vd.voucher_id"
                );

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Models.Voucher user = new Models.Voucher(); // Tạo một đối tượng Users mới
                    //set id
                    user.setVoucherDetail_id(rs.getInt("voucherDetail_id"));
                    user.setVoucher_name(rs.getString("voucher_name"));
                    user.setVoucher_quantity(rs.getInt("voucher_quantity")); // Thiết lập username
                    user.setVoucher_discount(rs.getInt("voucher_discount"));
                    user.setVoucher_expire_date(rs.getDate("voucher_expire_date"));
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setVoucher_id(rs.getInt("voucher_id"));
                    user.setVoucher_type(rs.getString("voucher_type"));

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

    public List<Models.Voucher> searchVoucher(String query) {
        List<Models.Voucher> userList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                String sql;
                PreparedStatement pst;

                // Kiểm tra nếu query không trống
                if (query != null && !query.trim().isEmpty()) {
                    sql = "SELECT * FROM VoucherDetail WHERE voucher_name LIKE ? OR voucher_expire_date LIKE ?";
                    pst = conn.prepareStatement(sql);
                    String searchQuery = "%" + query + "%"; // Thêm ký tự wildcard
                    pst.setString(1, searchQuery);
                    pst.setString(2, searchQuery);
                } else {
                    // Nếu không có từ khóa tìm kiếm, lấy tất cả người dùng
                    sql = "SELECT * FROM VoucherDetail";
                    pst = conn.prepareStatement(sql);
                }

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    Models.Voucher user = new Models.Voucher(); // Tạo một đối tượng Users mới
                    //set id
                    user.setVoucherDetail_id(rs.getInt("voucherDetail_id"));
                    user.setVoucher_name(rs.getString("voucher_name"));
                    user.setVoucher_quantity(rs.getInt("voucher_quantity")); // Thiết lập username
                    user.setVoucher_discount(rs.getInt("voucher_discount"));
                    user.setVoucher_expire_date(rs.getDate("voucher_expire_date"));
                    user.setUser_id(rs.getInt("user_id"));
                    user.setVoucher_id(rs.getInt("voucher_id"));

                    userList.add(user);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    public List<Models.Voucher> getAllVouchersSorted() {
        List<Models.Voucher> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM VoucherDetail ORDER BY VoucherDetail_id DESC");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Models.Voucher user = new Models.Voucher(); // Tạo một đối tượng Users mới
                    //set id
                    user.setVoucherDetail_id(rs.getInt("voucherDetail_id"));
                    user.setVoucher_name(rs.getString("voucher_name"));
                    user.setVoucher_quantity(rs.getInt("voucher_quantity")); // Thiết lập username
                    user.setVoucher_discount(rs.getInt("voucher_discount"));
                    user.setVoucher_expire_date(rs.getDate("voucher_expire_date"));
                    user.setUser_id(rs.getInt("user_id"));
                    user.setVoucher_id(rs.getInt("voucher_id"));

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

    // Phương thức để thêm voucher bằng stored procedure AddOrUpdateVoucher
    public boolean addVoucher(Voucher newVoucher) {
        Connection conn = DBConnection.getConnection();
        CallableStatement stmt = null;
        boolean isSuccess = false;

        try {
            // Chuẩn bị câu lệnh để gọi stored procedure
            String sql = "{CALL AddOrUpdateVoucher(?, ?, ?, ?, ?, ?)}";
            stmt = conn.prepareCall(sql);

            // Gán giá trị cho các tham số của stored procedure
            stmt.setString(1, newVoucher.getVoucher_name());
            stmt.setInt(2, newVoucher.getVoucher_quantity());
            stmt.setInt(3, newVoucher.getVoucher_discount());
            stmt.setDate(4, newVoucher.getVoucher_expire_date());
            stmt.setInt(5, newVoucher.getVoucher_id());
            stmt.setInt(6, newVoucher.getUser_id());

            // Thực thi stored procedure
            int rowsAffected = stmt.executeUpdate();
            isSuccess = rowsAffected > 0; // Nếu có hàng bị ảnh hưởng, tức là thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng CallableStatement và Connection
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public boolean deleteVoucher(int brandId) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Kiểm tra số lượng voucher còn lại cho brand_id này
            String checkSql = "SELECT voucher_quantity FROM VoucherDetail WHERE VoucherDetail_id = ?";
            ps = conn.prepareStatement(checkSql);
            ps.setInt(1, brandId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("voucher_quantity");

                if (quantity > 1) {
                    // Giảm số lượng voucher đi 1 nếu số lượng lớn hơn 1
                    String updateSql = "UPDATE VoucherDetail SET voucher_quantity = voucher_quantity - 1 WHERE VoucherDetail_id = ?";
                    ps = conn.prepareStatement(updateSql);
                    ps.setInt(1, brandId);
                    ps.executeUpdate();
                    return true;
                } else if (quantity == 1) {
                    // Xóa voucher nếu số lượng là 1
                    String deleteSql = "DELETE FROM VoucherDetail WHERE VoucherDetail_id = ?";
                    ps = conn.prepareStatement(deleteSql);
                    ps.setInt(1, brandId);
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng các tài nguyên
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
        return false; // Trả về false nếu không xóa được
    }
    public List<Models.Voucher> GetVoucher() {
        List<Models.Voucher> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT voucher_id, voucher_type FROM Voucher");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Models.Voucher user = new Models.Voucher(); // Tạo một đối tượng Users mới
                    //set id
                    user.setVoucher_id(rs.getInt("voucher_id"));
                    user.setVoucher_type(rs.getString("voucher_type"));
                 

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

}
