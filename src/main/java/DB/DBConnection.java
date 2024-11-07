/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lhhuong
 */
public class DBConnection {

    public static Connection getConnection() {
        //DB: KHOA\\SQLEXPRESS
        Connection conn;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-V16KFQ7:1433;databaseName=SWP;user=sa;password=40022131;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(url);
        } catch (Exception ex) {
            conn = null;
        }
        return conn;
    }
}
