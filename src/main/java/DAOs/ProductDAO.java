/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class ProductDAO {

    public ResultSet getAllProduct() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("select * from Product");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllProductSale() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product WHERE pro_sale IS NOT NULL");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getAllProductNotSale() {
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM Product WHERE pro_sale IS NULL");

            } catch (SQLException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public ResultSet getProductById(String id) {
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
}
