/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Order;
import Models.OrderDetail;
import Models.Pay;
import Models.Product;
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
                String insertOrder = "INSERT INTO [Order] (user_id,[status] ,payment_id) "
                        + "VALUES (?, ?, ?)";
                pst = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, order.getUserId());
                pst.setString(2, order.getStatus());
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

                    if (orderDetail.getVoucherDetailId() > 0) {
                        String sql = "INSERT INTO Order_Details (quantity, price, order_id, proDetail_id, voucherDetail_id, [status], [check])"
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

        // Updated query to include pd.proDetail_id
        String query = "SELECT od.order_detail_id, od.quantity AS order_quantity, od.price AS order_price, "
                + "od.status AS order_status, pd.proDetail_id, pd.image AS product_image, p.pro_name, "
                + "p.description AS product_description, v.voucher_type, v.voucher_img, vd.voucher_name "
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
                Object[] orderDetailData = new Object[11]; // Updated array size to 11
                orderDetailData[0] = resultSet.getInt("order_detail_id");
                orderDetailData[1] = resultSet.getInt("order_quantity");

                // Format price
                BigDecimal price = resultSet.getBigDecimal("order_price");
                String formattedPrice = NumberFormat.getInstance(Locale.GERMANY).format(price);
                orderDetailData[2] = formattedPrice;

                orderDetailData[3] = resultSet.getString("order_status");
                orderDetailData[4] = resultSet.getInt("proDetail_id");
                orderDetailData[5] = resultSet.getString("product_image");
                orderDetailData[6] = resultSet.getString("pro_name");
                orderDetailData[7] = resultSet.getString("product_description");
                orderDetailData[8] = resultSet.getString("voucher_type");
                orderDetailData[9] = resultSet.getString("voucher_img");
                orderDetailData[10] = resultSet.getString("voucher_name");

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

        // Updated query to include pd.proDetail_id
        String query = "SELECT od.order_detail_id, od.quantity AS order_quantity, od.price AS order_price, "
                + "od.status AS order_status, pd.proDetail_id, pd.image AS product_image, p.pro_name, "
                + "p.description AS product_description, v.voucher_type, v.voucher_img, vd.voucher_name "
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
                Object[] orderDetailData = new Object[11]; // Updated array size to 11
                orderDetailData[0] = resultSet.getInt("order_detail_id");
                orderDetailData[1] = resultSet.getInt("order_quantity");

                // Format price
                BigDecimal price = resultSet.getBigDecimal("order_price");
                String formattedPrice = NumberFormat.getInstance(Locale.GERMANY).format(price);
                orderDetailData[2] = formattedPrice;

                orderDetailData[3] = resultSet.getString("order_status");
                orderDetailData[4] = resultSet.getInt("proDetail_id");
                orderDetailData[5] = resultSet.getString("product_image");
                orderDetailData[6] = resultSet.getString("pro_name");
                orderDetailData[7] = resultSet.getString("product_description");
                orderDetailData[8] = resultSet.getString("voucher_type");
                orderDetailData[9] = resultSet.getString("voucher_img");
                orderDetailData[10] = resultSet.getString("voucher_name");

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

    public int getTotalOrders() {
        Connection conn = DBConnection.getConnection();
        int orderCount = 0; // Variable name updated to better reflect its purpose

        if (conn != null) {
            try {
                // Query to count total orders in the Order table
                String sql = "SELECT COUNT(*) FROM [Order]";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    orderCount = rs.getInt(1); // Get the count of orders
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Close the resources
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return orderCount;
    }

//    public List<Models.Order> searchBrand(String query) {
//        List<Models.Order> userList = new ArrayList<>();
//        Connection conn = DBConnection.getConnection();
//
//        if (conn != null) {
//            try {
//                String sql;
//                PreparedStatement pst;
//
//                // Kiểm tra nếu query không trống
//                if (query != null && !query.trim().isEmpty()) {
//                    sql = "SELECT * FROM Order WHERE brand_name LIKE ? OR description LIKE ?";
//                    pst = conn.prepareStatement(sql);
//                    String searchQuery = "%" + query + "%"; // Thêm ký tự wildcard
//                    pst.setString(1, searchQuery);
//                    pst.setString(2, searchQuery);
//                } else {
//                    // Nếu không có từ khóa tìm kiếm, lấy tất cả người dùng
//                    sql = "SELECT * FROM Brand";
//                    pst = conn.prepareStatement(sql);
//                }
//
//                ResultSet rs = pst.executeQuery();
//
//                while (rs.next()) {
//                    Models.Order user = new Models.Order();
//                    user.setBrand_id(rs.getInt("brand_id"));
//                    user.setBrand_name(rs.getString("brand_name"));
//                    user.setDescription(rs.getString("description"));
//                    userList.add(user);
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return userList;
//    }
    public List<Models.Order> getAllOrders() {
        List<Models.Order> orderList = new ArrayList<>(); // List to store orders
        List<Models.OrderDetail> orderDetailList = new ArrayList<>(); // List to store orders
        Connection conn = DBConnection.getConnection(); // Connect to the database
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM [Order]");

                // Iterate through ResultSet and create Order objects
                while (rs.next()) {
                    Models.Order order = new Models.Order(); // Create a new Order object

                    // Set the properties of the order object based on the result set
                    order.setOrderId(rs.getInt("order_id"));
                    order.setOrderDate(rs.getDate("order_date"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setPaymentId(rs.getInt("payment_id"));
                    order.setStatus(rs.getString("status"));

                    // Add the order to the list
                    orderList.add(order);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log any SQL exception
            } finally {
                // Close ResultSet and Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Log any exception when closing resources
                }
            }
        }
        return orderList; // Return the list of orders with their details
    }

    public List<Models.OrderDetail> getAllOrderDetails(int orderId) {
        List<Models.OrderDetail> orderDetailsList = new ArrayList<>(); // List to store order details
        Connection conn = DBConnection.getConnection(); // Connect to the database
        ResultSet rs = null;

        if (conn != null) {
            try {
                // Use PreparedStatement for queries with parameters
                String sql = "SELECT o.*, p.pro_id, p.pro_name, p.pro_price, p.pro_sale, p.madein, p.cat_id, p.brand_id, "
                        + "pd.proDetail_id, pd.color_name, pd.quantity, pd.image "
                        + "FROM Order_Details o "
                        + "LEFT JOIN Product_Details pd ON o.proDetail_id = pd.proDetail_id "
                        + "LEFT JOIN Product p ON pd.pro_id = p.pro_id "
                        + "WHERE o.order_id = ?";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, orderId);

                // Execute the query
                rs = pst.executeQuery();

                // Iterate through ResultSet and create OrderDetail objects
                while (rs.next()) {
                    Models.OrderDetail orderDetail = new Models.OrderDetail(); // Create a new OrderDetail object

                    // Set the properties of the orderDetail object based on the result set
                    orderDetail.setOrderDetailId(rs.getInt("order_detail_id"));
                    orderDetail.setQuantity(rs.getInt("quantity"));
                    orderDetail.setPrice(rs.getBigDecimal("price"));
                    orderDetail.setOrderId(rs.getInt("order_id"));
                    orderDetail.setProDetailId(rs.getInt("proDetail_id"));
                    orderDetail.setVoucherDetailId(rs.getInt("voucherDetail_id"));
                    orderDetail.setStatus(rs.getString("status"));
                    orderDetail.setCheck(rs.getString("check"));

                    orderDetail.setPro_id(rs.getInt("pro_id"));
                    orderDetail.setPro_name(rs.getString("pro_name"));
                    orderDetail.setPro_price(formatPrices(rs.getString("pro_price")));
                    orderDetail.setPro_sale(formatPrices(rs.getString("pro_sale")));
                    orderDetail.setPro_quantity(rs.getInt("quantity"));
                    orderDetail.setMadein(rs.getString("madein"));
                    orderDetail.setPro_image(rs.getString("image"));
                    orderDetail.setCat_id(rs.getInt("cat_id"));
                    orderDetail.setBrand_id(rs.getString("brand_id"));
                    orderDetail.setProDetail_id(rs.getInt("proDetail_id"));
                    orderDetail.setColor_name(rs.getString("color_name"));
                    // Add the orderDetail to the list
                    orderDetailsList.add(orderDetail);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log any SQL exception
            } finally {
                // Close ResultSet, PreparedStatement, and Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Log any exception when closing resources
                }
            }
        }
        return orderDetailsList; // Return the list of order details
    }

    public String formatPrices(String price) {
        // Kiểm tra nếu price là null hoặc rỗng
        if (price == null || price.isEmpty()) {
            return ""; // Trả về giá trị mặc định nếu giá trị price không hợp lệ
        }

        // Tách phần nguyên và phần thập phân (nếu có)
        String[] parts = price.split("\\.");
        String integerPart = parts[0]; // Phần nguyên của giá

        StringBuilder result = new StringBuilder();
        int count = 0;

        // Định dạng phần nguyên: Duyệt từ cuối chuỗi tới đầu để thêm dấu "." mỗi ba chữ số
        for (int i = integerPart.length() - 1; i >= 0; i--) {
            if (count == 3) {
                result.append(".");
                count = 0; // Đặt lại count về 0 sau khi thêm dấu "."
            }
            result.append(integerPart.charAt(i));
            count++;
        }

        // Đảo ngược chuỗi để có kết quả cuối cùng
        return result.reverse().toString();
    }

    public int discount(int voucherDetailId) {
        int discountValue = 0; // Giá trị mặc định nếu không tìm thấy hoặc có lỗi
        String sql = "SELECT voucher_discount FROM VoucherDetail WHERE voucherDetail_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            if (conn != null) {
                pst.setInt(1, voucherDetailId);
                try ( ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // Lấy giá trị voucher_discount từ ResultSet
                        discountValue = rs.getInt("voucher_discount");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return discountValue;
    }

    public String paymentMethod(int paymentId) {
        String paymentMethod = ""; // Giá trị mặc định nếu không tìm thấy hoặc có lỗi
        String sql = "SELECT p.payment_method FROM Payment p JOIN [Order] o ON p.payment_id = o.payment_id WHERE o.payment_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, paymentId);
            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Lấy giá trị payment_method từ ResultSet
                    paymentMethod = rs.getString("payment_method");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paymentMethod;
    }

    public void updateStatusNew(int order_id, String status) {
        Connection conn = DBConnection.getConnection();
        int count;
        try {
            // SQL query để cập nhật thông tin trạng thái đơn hàng
            String sql = "UPDATE [Order] SET [status] = ? WHERE order_id = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, status);  // Đặt tham số status vào chỗ ? thứ nhất
            pst.setInt(2, order_id);   // Đặt tham số order_id vào chỗ ? thứ hai

            count = pst.executeUpdate(); // Thực hiện cập nhật và lấy số dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại ngoại lệ để debug
            count = 0; // Gán giá trị 0 trong trường hợp lỗi
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Đóng kết nối sau khi thực hiện
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStatusDetail(int order_id, String status) {
        Connection conn = DBConnection.getConnection();
        int count;
        try {
            // SQL query để cập nhật thông tin trạng thái trong Order_Details
            String sql = "UPDATE Order_Details SET [status] = ? WHERE order_id = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, status);  // Đặt tham số status vào chỗ ? thứ nhất
            pst.setInt(2, order_id);   // Đặt tham số order_id vào chỗ ? thứ hai

            count = pst.executeUpdate(); // Thực hiện cập nhật và lấy số dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại ngoại lệ để debug
            count = 0; // Gán giá trị 0 trong trường hợp lỗi
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Đóng kết nối sau khi thực hiện
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Models.Order> searchOrders(String query) {
        List<Models.Order> orderList = new ArrayList<>(); // List to store orders
        Connection conn = DBConnection.getConnection(); // Connect to the database
        ResultSet rs = null;

        if (conn != null) {
            try {
                // Xây dựng câu truy vấn SQL cho tìm kiếm
                String sql = "SELECT * FROM [Order] WHERE "
                        + "CAST(order_id AS VARCHAR) LIKE ? OR "
                        + "CAST(user_id AS VARCHAR) LIKE ? OR "
                        + "status LIKE ? OR "
                        + "CAST(order_date AS VARCHAR) LIKE ?";

                PreparedStatement pst = conn.prepareStatement(sql);

                // Set the parameters with the search query
                String searchKeyword = "%" + query + "%";
                pst.setString(1, searchKeyword); // Search in order_id
                pst.setString(2, searchKeyword); // Search in user_id
                pst.setString(3, searchKeyword); // Search in status
                pst.setString(4, searchKeyword); // Search in order_date

                // Execute the query
                rs = pst.executeQuery();

                // Iterate through ResultSet and create Order objects
                while (rs.next()) {
                    Models.Order order = new Models.Order(); // Create a new Order object

                    // Set the properties of the order object based on the result set
                    order.setOrderId(rs.getInt("order_id"));
                    order.setOrderDate(rs.getDate("order_date"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setPaymentId(rs.getInt("payment_id"));
                    order.setStatus(rs.getString("status"));

                    // Add the order to the list
                    orderList.add(order);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log any SQL exception
            } finally {
                // Close ResultSet, PreparedStatement, and Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Log any exception when closing resources
                }
            }
        }
        return orderList; // Return the list of orders
    }

}
