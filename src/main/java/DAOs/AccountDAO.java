/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class AccountDAO {

    public boolean login(Users acc) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, acc.getEmail());
            pst.setString(2, md5Hash(acc.getPassword()));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                acc.setStatus_user(rs.getBoolean("status_user")); // Update status_user
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public String CheckNewPassword(String email) {
        Connection conn = DBConnection.getConnection();
        Users acc = null;
        String password = "";
        if (conn != null) {
            try {
                String sql = "SELECT password FROM Users WHERE email = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    password = rs.getString("password");
                    System.out.println(password);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return password;
    }

    public int GetIdUser(String email) {
        Connection conn = DBConnection.getConnection();
        Users acc = null;
        int password = 0;
        if (conn != null) {
            try {
                String sql = "SELECT user_id FROM Users WHERE email = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    password = rs.getInt("user_id");
                    System.out.println(password);
                }
            } catch (SQLException ex) {
            }
        }
        return password;
    }

    public String GetNameAdmin(int id) {
        Connection conn = DBConnection.getConnection();
        String username = "";
        if (conn != null) {
            try {
                String sql = "SELECT username FROM Users WHERE user_id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);  // Sử dụng id thay vì email
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    username = rs.getString("username");
                }
            } catch (SQLException ex) {
            }
        }
        return username;
    }

    public void setVerifiedEmail(int user_id, boolean status_user) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "UPDATE Users SET status_user = ? WHERE user_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, status_user);
            pst.setInt(2, user_id);
            pst.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public Users getAccountByEmail(String email) {
        Connection conn = DBConnection.getConnection();
        Users acc = null;

        if (conn != null) {
            try {
                String sql = "SELECT user_id, username, password, phone, address, role, create_at, avatar, status_user FROM Users WHERE email = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    acc = new Users();
                    acc.setEmail(rs.getString("email"));
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setPhone(rs.getString("phone"));
                    acc.setAddress(rs.getString("address"));
                    acc.setRole(rs.getInt("role"));
                    acc.setCreate_at(rs.getDate("create_at"));
                    acc.setAvatar(rs.getString("avatar"));
                    acc.setStatus_user(rs.getBoolean("status_user"));
                }
            } catch (SQLException ex) {
            }
        }
        return acc;
    }

    public int getAccountIdByEmail(String email) {
        Connection conn = DBConnection.getConnection();
        Users acc = null;
        int id = 0;
        if (conn != null) {
            try {
                String sql = "SELECT id FROM Users WHERE email = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("user_id");
                }
            } catch (SQLException ex) {
            }
        }
        return id;
    }

    public String getPictureByEmail(String email) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        String picture = null;

        if (conn != null) {
            try {
                String sql = "Select avatar from Users where email =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    picture = rs.getString("avartar");
                }
            } catch (SQLException ex) { // Debugging statement
                // Debugging statement
                rs = null;
            }
        }
        return picture;
    }

    public Boolean getVerifyByEmail(int user_id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        boolean verified_email = false;

        if (conn != null) {
            try {
                String sql = "Select status_user from Users where user_id =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, user_id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    verified_email = rs.getBoolean("status_user");
                }
            } catch (SQLException ex) { // Debugging statement
                // Debugging statement
                rs = null;
            }
        }
        return verified_email;
    }

    public int getTypeByEmail(String email) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        int type_id = 0;

        if (conn != null) {
            try {
                String sql = "Select role from Users where email =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    type_id = rs.getInt("role");
                }
            } catch (SQLException ex) { // Debugging statement
                // Debugging statement
                rs = null;
            }
        }
        return type_id;
    }

    public int getTypeById(int email) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        int type_id = 0;

        if (conn != null) {
            try {
                String sql = "Select role from Users where user_id =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    type_id = rs.getInt("role");
                }
            } catch (SQLException ex) { // Debugging statement
                // Debugging statement
                rs = null;
            }
        }
        return type_id;
    }

    public int addNew(Users obj) {
        Connection conn = DBConnection.getConnection();
        int count = 0;
        if (conn != null) {
            try {
                String sql = "INSERT INTO Users (username, password, email, phone, address, role, create_at, avatar, status_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Debug statements
                System.out.println("Debug: Preparing to insert user: " + obj.getUsername());

                pst.setString(1, obj.getUsername());
                pst.setString(2, obj.getPassword());
                pst.setString(3, obj.getEmail());

                if (obj.getPhone() != null) {
                    pst.setString(4, obj.getPhone());
                    System.out.println("Debug: Phone is set to: " + obj.getPhone());
                } else {
                    pst.setNull(4, java.sql.Types.INTEGER);
                    System.out.println("Debug: Phone is set to null");
                }

                if (obj.getAddress() != null) {
                    pst.setString(5, obj.getAddress());
                    System.out.println("Debug: Address is set to: " + obj.getAddress());
                } else {
                    pst.setNull(5, java.sql.Types.VARCHAR);
                    System.out.println("Debug: Address is set to null");
                }

                pst.setInt(6, obj.getRole());
                pst.setDate(7, new java.sql.Date(obj.getCreate_at().getTime()));
                pst.setString(8, obj.getAvatar());
                pst.setBoolean(9, obj.isStatus_user());

                System.out.println("Debug: SQL prepared statement is ready to execute");

                count = pst.executeUpdate();

                if (count > 0) {
                    System.out.println("Debug: Insert successful.");
                } else {
                    System.out.println("Debug: Insert failed.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // This will show you the SQL error details
                count = 0;
            }
        } else {
            System.out.println("Debug: Database connection failed.");
        }
        return count;
    }

    public ResultSet getAll() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Users");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public int GetTotalUser() {
        Connection conn = DBConnection.getConnection();
        int userCount = 0; // Change variable name to better reflect its purpose
        if (conn != null) {
            try {
                // Query to count users with role=2
                String sql = "SELECT COUNT(*) FROM Users WHERE role=2";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    userCount = rs.getInt(1); // Get the count of users
                    System.out.println(userCount);  // Print to check the result
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userCount;
    }

    public String md5Hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1).toUpperCase());
        }
        return sb.toString();
    }

    public List<Users> getAllUser() {
        List<Users> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                // Thực thi truy vấn SQL để lấy người dùng với role = 2
                rs = st.executeQuery("SELECT user_id, avatar, username, email, phone, address, create_at FROM Users WHERE role = 2");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Users user = new Users(); // Tạo một đối tượng Users mới
                    user.setUser_id(rs.getInt("user_id"));
                    user.setAvatar(rs.getString("avatar")); // Thiết lập avatar
                    user.setUsername(rs.getString("username")); // Thiết lập username
                    user.setEmail(rs.getString("email")); // Thiết lập email
                    user.setPhone(rs.getString("phone")); // Thiết lập phone
                    user.setAddress(rs.getString("address")); // Thiết lập address
                    user.setCreate_at(rs.getDate("create_at")); // Thiết lập ngày tạo

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

    public List<Users> searchUsers(String query) {
        List<Users> userList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {
                String sql;
                PreparedStatement pst;

                // Kiểm tra nếu query không trống
                if (query != null && !query.trim().isEmpty()) {
                    sql = "SELECT * FROM Users WHERE (username LIKE ? OR email LIKE ?) and role=2";
                    pst = conn.prepareStatement(sql);
                    String searchQuery = "%" + query + "%"; // Thêm ký tự wildcard
                    pst.setString(1, searchQuery);
                    pst.setString(2, searchQuery);
                } else {
                    // Nếu không có từ khóa tìm kiếm, lấy tất cả người dùng
                    sql = "SELECT * FROM Users";
                    pst = conn.prepareStatement(sql);
                }

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    Users user = new Users();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setCreate_at(rs.getDate("create_at"));
                    userList.add(user);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    public List<Users> getAllUsersSorted() {
        List<Users> userList = new ArrayList<>(); // Tạo danh sách để lưu trữ người dùng
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Users where role=2 ORDER BY user_id DESC");

                // Duyệt qua ResultSet và tạo đối tượng Users
                while (rs.next()) {
                    Users user = new Users(); // Tạo một đối tượng Users mới
                    user.setUser_id(rs.getInt("user_id"));
                    user.setAvatar(rs.getString("avatar")); // Thiết lập avatar
                    user.setUsername(rs.getString("username")); // Thiết lập username
                    user.setEmail(rs.getString("email")); // Thiết lập email
                    user.setPhone(rs.getString("phone")); // Thiết lập phone
                    user.setAddress(rs.getString("address")); // Thiết lập address
                    user.setCreate_at(rs.getDate("create_at")); // Thiết lập ngày tạo

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

    public List<Users> getAllInfo(int user_id) {
        List<Users> accounts = new ArrayList<>();
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        String query = "SELECT * FROM Users WHERE user_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Thiết lập tham số cho câu truy vấn
            statement.setInt(1, user_id);

            // Thực thi câu lệnh truy vấn và nhận kết quả
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Users account = new Users(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("address"),
                            resultSet.getString("avatar")
                    );
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public boolean updateUserInfo(int userId, String username, String phone, String address) {
        Connection conn = DBConnection.getConnection();
        String query = "UPDATE Users SET username = ?, phone = ?, address = ? WHERE user_id = ?";
        boolean isUpdated = false;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Thiết lập tham số cho câu truy vấn
            statement.setString(1, username);
            statement.setString(2, phone);
            statement.setString(3, address);
            statement.setInt(4, userId);

            // Thực thi câu lệnh cập nhật
            int rowsAffected = statement.executeUpdate();
            isUpdated = rowsAffected > 0; // Kiểm tra xem có bản ghi nào bị ảnh hưởng không
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

}
