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

    public void setVerifiedEmail(int user_id, boolean status_user) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "UPDATE Users SET status_user = ? WHERE user_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, status_user);
            pst.setInt(2, user_id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Users getAccountByEmail(String email) {
        Connection conn = DBConnection.getConnection();
        Users acc = null;

        if (conn != null) {
            try {
                String sql = "SELECT user_id, username, password, phone, address, role, create_at, avartar, status_user FROM Users WHERE email = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    acc = new Users();
                    acc.setEmail(rs.getString("email"));
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setPhone(rs.getInt("phone"));
                    acc.setAddress(rs.getString("address"));
                    acc.setRole(rs.getInt("role"));
                    acc.setCreate_at(rs.getDate("create_at"));
                    acc.setAvartar(rs.getString("avartar"));
                    acc.setStatus_user(rs.getBoolean("status_user"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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
                ex.printStackTrace();
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
                String sql = "Select avartar from Users where email =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    picture = rs.getString("avartar");
                    System.out.println("Picture retrieved: " + picture); // Debug statement
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Debugging statement
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
                String sql = "Select status_user from Account where user_id =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, user_id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    verified_email = rs.getBoolean("status_user");
                    System.out.println("Picture retrieved: " + verified_email); // Debug statement
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Debugging statement
                rs = null;
            }
        }
        return verified_email;
    }

    public Boolean getTypeByEmail(String email) {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;
        boolean type_id = false;

        if (conn != null) {
            try {
                String sql = "Select role from Users where email =?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    type_id = rs.getBoolean("role");
                    System.out.println("Picture retrieved: " + type_id); // Debug statement
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Debugging statement
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
                String sql = "insert into Users values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, obj.getUsername());
                pst.setString(2, obj.getPassword());
                pst.setString(3, obj.getEmail());
                pst.setInt(4, obj.getPhone());
                pst.setString(5, obj.getAddress());
                pst.setInt(6, obj.getRole());
                 pst.setDate(7, new java.sql.Date(obj.getCreate_at().getTime()));
                pst.setString(8, obj.getAvartar());
                pst.setBoolean(9, obj.isStatus_user());
               
                count = pst.executeUpdate();
            } catch (SQLException ex) {
                count = 0;
            }
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

    public ResultSet getAllUser() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT username, email, phone, address, create_at FROM Users WHERE role = 1");
            } catch (SQLException ex) {
                ex.printStackTrace();  // Log the exception
                rs = null;
            }
        }
        return rs;
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
}
