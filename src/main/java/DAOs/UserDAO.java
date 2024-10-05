/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
