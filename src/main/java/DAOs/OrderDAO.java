/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Order;
import Models.OrderDetail;
import Models.Pay;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
                    System.out.println(orderDetail.getQuantity());
                    System.out.println(orderDetail.getPrice());
                    System.out.println(orderId);
                    System.out.println(orderDetail.getProDetailId());
                    System.out.println(orderDetail.getVoucherDetailId());
                    System.out.println("---------------");

                    if (orderDetail.getVoucherDetailId() > 0) {
                        String sql = "INSERT INTO Order_Details (quantity, price, order_id, proDetail_id, voucherDetail_id, [status], [check])"
                                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                        pst = conn.prepareStatement(sql);
                        System.out.println("123");
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

    public List<OrderDetail> getAllOrderDetailsByUserIdForNotification(int userId) {
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

                //Đảo ngược mảng để mới nhất hiện ra
                Collections.reverse(orderDetailsList);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return orderDetailsList;
    }

    public Map<Integer, List<Object[]>> getAllOrderDetailsByUserId(int userId) {
        Map<Integer, List<Object[]>> orderDetailsMap = new HashMap<>();

        // Câu truy vấn để lấy các trường từ các bảng liên quan
        String query = "SELECT od.order_detail_id, od.quantity AS order_quantity, od.price AS order_price, "
                + "od.status AS order_status, pd.image AS product_image, p.pro_name, p.description AS product_description, "
                + "v.voucher_type, v.voucher_img, vd.voucher_name "
                + "FROM Order_Details od "
                + "JOIN [Order] o ON od.order_id = o.order_id "
                + "JOIN Product_Details pd ON od.proDetail_id = pd.proDetail_id "
                + "JOIN [Product] p ON pd.pro_id = p.pro_id "
                + "LEFT JOIN VoucherDetail vd ON od.voucherDetail_id = vd.voucherDetail_id "
                + "LEFT JOIN Voucher v ON vd.voucher_id = v.voucher_id "
                + "WHERE o.user_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, userId);
            ResultSet resultSet = pst.executeQuery();

            List<Object[]> orderDetailsList = new ArrayList<>();

            while (resultSet.next()) {
                Object[] orderDetailData = new Object[10];
                orderDetailData[0] = resultSet.getInt("order_detail_id");
                orderDetailData[1] = resultSet.getInt("order_quantity");
                // Format money
                BigDecimal price = resultSet.getBigDecimal("order_price");
                String formattedPrice = NumberFormat.getInstance(Locale.GERMANY).format(price);
                orderDetailData[2] = formattedPrice;

                orderDetailData[3] = resultSet.getString("order_status");
                orderDetailData[4] = resultSet.getString("product_image");
                orderDetailData[5] = resultSet.getString("pro_name");
                orderDetailData[6] = resultSet.getString("product_description");
                orderDetailData[7] = resultSet.getString("voucher_type");
                orderDetailData[8] = resultSet.getString("voucher_img");
                orderDetailData[9] = resultSet.getString("voucher_name");

                orderDetailsList.add(orderDetailData);
            }

            orderDetailsMap.put(userId, orderDetailsList);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderDetailsMap;
    }

    public Map<Integer, List<Object[]>> getOrderDetailsByProductName(String proName) {
        Map<Integer, List<Object[]>> orderDetailsMap = new HashMap<>();

        // SQL query to fetch order details based on product name
        String query = "SELECT od.order_detail_id, od.quantity AS order_quantity, od.price AS order_price, "
                + "od.status AS order_status, pd.image AS product_image, p.pro_name, p.description AS product_description, "
                + "v.voucher_type, v.voucher_img, vd.voucher_name "
                + "FROM Order_Details od "
                + "JOIN [Order] o ON od.order_id = o.order_id "
                + "JOIN Product_Details pd ON od.proDetail_id = pd.proDetail_id "
                + "JOIN [Product] p ON pd.pro_id = p.pro_id "
                + "LEFT JOIN VoucherDetail vd ON od.voucherDetail_id = vd.voucherDetail_id "
                + "LEFT JOIN Voucher v ON vd.voucher_id = v.voucher_id "
                + "WHERE p.pro_name LIKE ?"; // Filter for product name

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, "%" + proName + "%");

            ResultSet resultSet = pst.executeQuery();

            int userId = 0;
            List<Object[]> orderDetailsList = new ArrayList<>();

            while (resultSet.next()) {
                Object[] orderDetailData = new Object[10];
                orderDetailData[0] = resultSet.getInt("order_detail_id");
                orderDetailData[1] = resultSet.getInt("order_quantity");
                // Format money
                BigDecimal price = resultSet.getBigDecimal("order_price");
                String formattedPrice = NumberFormat.getInstance(Locale.GERMANY).format(price);
                orderDetailData[2] = formattedPrice;

                orderDetailData[3] = resultSet.getString("order_status");
                orderDetailData[4] = resultSet.getString("product_image");
                orderDetailData[5] = resultSet.getString("pro_name");
                orderDetailData[6] = resultSet.getString("product_description");
                orderDetailData[7] = resultSet.getString("voucher_type");
                orderDetailData[8] = resultSet.getString("voucher_img");
                orderDetailData[9] = resultSet.getString("voucher_name");

                orderDetailsList.add(orderDetailData);
            }

            orderDetailsMap.put(userId, orderDetailsList);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderDetailsMap;
    }

    public boolean deleteOrderDetailByOrderDetailId(String id) {
        Connection conn = DBConnection.getConnection();
        boolean deleted = false;

        if (conn != null) {
            String query = "DELETE FROM Order_Details WHERE order_detail_id = ?";
            try ( PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);
                int affectedRows = pst.executeUpdate();

                deleted = (affectedRows > 0);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return deleted;
    }

    public void updateCheckStatusToTrue(String orderDetailId) {
        String query = "UPDATE Order_Details SET [check] = 'true' WHERE order_detail_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, orderDetailId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully updated check status to true for order_detail_id: " + orderDetailId);
            } else {
                System.out.println("No order detail found with order_detail_id: " + orderDetailId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
