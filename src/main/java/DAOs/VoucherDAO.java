/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
