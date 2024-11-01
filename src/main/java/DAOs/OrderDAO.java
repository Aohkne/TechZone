/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Order;
import Models.OrderDetail;
import Models.Pay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class OrderDAO {

    //Add order and payment
    public void addProduct(Pay payment, Order order, List<OrderDetail> orderDetails) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            try {

                // Insert payment 
                String insertPayment = "INSERT INTO Payment (payment_method)"
                        + "VALUES (?)";
                PreparedStatement pst = conn.prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS); // Return generated keys
                pst.setString(1, payment.getPaymentMethod());
                pst.executeUpdate();

                //Get Pay ID
                ResultSet rs = pst.getGeneratedKeys();
                int paymentId = 0;
                if (rs.next()) {
                    paymentId = rs.getInt(1);
                }

                // Insert Order 
                String insertOrder = "INSERT INTO [Order] (status, user_id, payment_id) "
                        + "VALUES (?, ?, ?)";
                pst = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS); // Return generated keys
                pst.setString(1, order.getStatus());
                pst.setInt(2, order.getUserId());
                pst.setInt(3, paymentId);
                pst.executeUpdate();

                //Get Order ID
                rs = pst.getGeneratedKeys();
                int orderId = 0;
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                // Insert Order Detail
                for (OrderDetail orderDetail : orderDetails) {
//                    System.out.println(orderDetail.getQuantity());
//                    System.out.println(orderDetail.getPrice());
//                    System.out.println(orderId);
//                    System.out.println(orderDetail.getProDetailId());
//                    System.out.println(orderDetail.getVoucherDetailId());
//                    System.out.println("---------------");

                    if (orderDetail.getVoucherDetailId() > 0) {
                        String sql = "INSERT INTO Order_Details (quantity, price, order_id, proDetail_id, voucherDetail_id) "
                                + "VALUES (?, ?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);

                        pst.setInt(1, orderDetail.getQuantity());
                        pst.setBigDecimal(2, orderDetail.getPrice());
                        pst.setInt(3, orderId);
                        pst.setInt(4, orderDetail.getProDetailId());
                        pst.setInt(5, orderDetail.getVoucherDetailId());
                        pst.executeUpdate();
                    } else {
                        String sql = "INSERT INTO Order_Details (quantity, price, order_id, proDetail_id) "
                                + "VALUES (?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);
                        System.out.println("---+1231+");

                        pst.setInt(1, orderDetail.getQuantity());
                        pst.setBigDecimal(2, orderDetail.getPrice());
                        pst.setInt(3, orderId);
                        pst.setInt(4, orderDetail.getProDetailId());
                        pst.executeUpdate();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

}
