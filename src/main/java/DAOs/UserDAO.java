/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Users;
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
public class UserDAO {

    public ResultSet getUserById(String id) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Users Where user_id = " + id);

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public Users getUsersById(String idUser) {
        Users user = null;
        String query = "SELECT * FROM Users WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, idUser);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Users(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("avatar")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUserDetail(String id, String username, String address) {
        Connection conn = DBConnection.getConnection();
        boolean isUpdated = false;

        if (conn != null) {
            try {
                String sql = "UPDATE Users SET username = ?, address = ? WHERE user_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, address);
                ps.setString(3, id);

                // Execute update and check if any row was updated
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true;
                }

                // Close the PreparedStatement
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }
        return isUpdated;
    }

    public boolean updateUserInfo(String id, String username, String email, String phone, String address) {
        Connection conn = DBConnection.getConnection();
        boolean isUpdated = false;

        if (conn != null) {
            try {
                String sql = "UPDATE Users SET username = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, address);
                ps.setString(5, id);

                // Execute update and check if any row was updated
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true;
                }

                // Close the PreparedStatement
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }
        return isUpdated;
    }

    public boolean updatePassword(String id, String password) {
        Connection conn = DBConnection.getConnection();
        boolean isUpdated = false;

        if (conn != null) {
            try {
                String sql = "UPDATE Users SET password = ? WHERE user_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, id);

                // Execute update and check if any row was updated
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true;
                }

                // Close the PreparedStatement
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }
        return isUpdated;
    }

    public List<Users> getUsersById(int userId) {
        List<Users> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, userId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setRole(rs.getInt("role"));
                    user.setCreate_at(rs.getDate("create_at"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setStatus_user(rs.getBoolean("status_user"));
                    users.add(user); // Thêm User vào danh sách
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu có
        }
        return users;
    }

    public List<Integer> getUsersPerMonth() {
        List<Integer> usersPerMonth = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            usersPerMonth.add(0);
        }

        String sql = "SELECT MONTH(create_at) AS month, COUNT(user_id) AS user_count "
                   + "FROM Users "
                   + "GROUP BY MONTH(create_at) "
                   + "ORDER BY month ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int month = rs.getInt("month");
                int userCount = rs.getInt("user_count");
                usersPerMonth.set(month - 1, userCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersPerMonth;
    }
}
