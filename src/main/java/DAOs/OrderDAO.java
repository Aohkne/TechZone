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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                PreparedStatement pst = conn.prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, payment.getPaymentMethod());
                pst.executeUpdate();

                //Get Pay ID
                ResultSet rs = pst.getGeneratedKeys();
                int paymentId = 0;
                if (rs.next()) {
                    paymentId = rs.getInt(1);
                }

                // Insert Order 
                String insertOrder = "INSERT INTO [Order] (user_id, payment_id) "
                        + "VALUES (?, ?)";
                pst = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, order.getUserId());
                pst.setInt(2, paymentId);
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
                        String sql = "INSERT INTO Order_Details (quantity, price, order_id, proDetail_id, voucherDetail_id, status, check)"
                                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);

                        pst.setInt(1, orderDetail.getQuantity());
                        pst.setBigDecimal(2, orderDetail.getPrice());
                        pst.setInt(3, orderId);
                        pst.setInt(4, orderDetail.getProDetailId());
                        pst.setInt(5, orderDetail.getVoucherDetailId());
                        pst.setString(6, orderDetail.getStatus());
                        pst.setString(7, orderDetail.getCheck());
                        pst.executeUpdate();
                    } else {
                        String sql = "INSERT INTO Order_Details (quantity, price, order_id, proDetail_id, [status], [check]) "
                                + "VALUES (?, ?, ?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);
                        System.out.println("---+1231+");

                        pst.setInt(1, orderDetail.getQuantity());
                        pst.setBigDecimal(2, orderDetail.getPrice());
                        pst.setInt(3, orderId);
                        pst.setInt(4, orderDetail.getProDetailId());
                        pst.setString(5, orderDetail.getStatus());
                        pst.setString(6, orderDetail.getCheck());
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

    public List<OrderDetail> getAllOrderDetailsByUserId(int userId) {
        Connection conn = DBConnection.getConnection();
        List<OrderDetail> orderDetailsList = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT od.order_detail_id, od.quantity, od.price, od.order_id, "
                        + "od.proDetail_id, od.voucherDetail_id, od.[status], od.[check], "
                        + "p.pro_name, o.order_date "
                        + // Fetching order_date from Order table
                        "FROM Order_Details od "
                        + "JOIN [Order] o ON od.order_id = o.order_id "
                        + "JOIN Product_Details pd ON od.proDetail_id = pd.proDetail_id "
                        + "JOIN [Product] p ON pd.pro_id = p.pro_id "
                        + "WHERE o.user_id = ?";

                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, userId);
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(resultSet.getInt("order_detail_id"));
                    orderDetail.setQuantity(resultSet.getInt("quantity"));
                    orderDetail.setPrice(resultSet.getBigDecimal("price"));
                    orderDetail.setOrderId(resultSet.getInt("order_id"));
                    orderDetail.setProDetailId(resultSet.getInt("proDetail_id"));
                    orderDetail.setVoucherDetailId(resultSet.getInt("voucherDetail_id"));
                    orderDetail.setStatus(resultSet.getString("status"));
                    orderDetail.setCheck(resultSet.getString("check"));
                    orderDetail.setProName(resultSet.getString("pro_name"));
                    orderDetail.setOrderDate(resultSet.getDate("order_date")); // Setting order_date

                    orderDetailsList.add(orderDetail);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return orderDetailsList;
    }

}
