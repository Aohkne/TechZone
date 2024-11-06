/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.OrderDAO;
import DAOs.ProductDAO;
import Models.OrderDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Orders extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.equals("/Admin/Orders")) {
            AccountDAO dao = new AccountDAO();
            OrderDAO daos = new OrderDAO();
            int userId = -1;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id")) {
                        userId = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
            }
            String name = dao.GetNameAdmin(userId);
            int counts = daos.getTotalOrders();

            List<Models.Order> searchResults = (List<Models.Order>) request.getAttribute("searchResults");
            List<Models.Order> allOrder = new ArrayList<>();
            List<Models.OrderDetail> allDetail = new ArrayList<>();

            if (searchResults != null) {
                allOrder = searchResults;
                if (allOrder != null) {
                    // Tạo biến grandTotal để tính tổng giá trị của tất cả formattedTotalPrice

                    for (Models.Order order : allOrder) {
                        int orderId = order.getOrderId(); // Lấy order_id từ từng đơn hàng                       
                        List<Models.OrderDetail> orderDetails = daos.getAllOrderDetails(orderId); // Lấy danh sách chi tiết đơn hàng cho order hiện tại
                        BigDecimal grandTotal = BigDecimal.ZERO;

                        for (OrderDetail detail : orderDetails) {
                            // Lấy voucherDetail_id từ detail
                            int voucherDetailId = detail.getVoucherDetailId();

                            // Gọi phương thức discount để lấy giá trị giảm giá (dưới dạng chuỗi)
                            int discount = daos.discount(voucherDetailId);

                            // Tính giá trị totalPrices ban đầu (giá * số lượng)
                            BigDecimal totalPrices = detail.getPrice().multiply(new BigDecimal(detail.getQuantity()));

                            // Tính giá trị giảm giá (discount)
                            BigDecimal discountRate = new BigDecimal(discount).divide(new BigDecimal(100));
                            BigDecimal discountAmount = totalPrices.multiply(discountRate);

                            // Tính tổng giá trị sau khi đã trừ giảm giá
                            BigDecimal totalPrice = totalPrices.subtract(discountAmount);

                            // Cộng dồn giá trị totalPrice vào grandTotal
                            grandTotal = grandTotal.add(totalPrice);

                            // Định dạng totalPrice thành chuỗi để hiển thị
                            String formattedTotalPrice = formatPrices(totalPrice.setScale(2, RoundingMode.HALF_UP).toString());

                            // Gán giá trị totalPrice đã định dạng vào detail
                            detail.setTotalPrice(formattedTotalPrice);
                        }
                        // Sau khi tính toán xong, định dạng grandTotal để hiển thị
                        String formattedGrandTotal = formatPrices(grandTotal.setScale(2, RoundingMode.HALF_UP).toString());
                        order.setGrandTotal(formattedGrandTotal);
                        // Gán danh sách chi tiết đơn hàng vào đối tượng order hiện tại
                        order.setOrderDetails(orderDetails);
                        String method = daos.paymentMethod(orderId);
                        order.setPayment_method(method);
                    }

                }
                
            } else {

                allOrder = daos.getAllOrders();
                // Iterate over each order to get the order_id and fetch order details
                if (allOrder != null) {
                    // Tạo biến grandTotal để tính tổng giá trị của tất cả formattedTotalPrice

                    for (Models.Order order : allOrder) {
                        int orderId = order.getOrderId(); // Lấy order_id từ từng đơn hàng                       
                        List<Models.OrderDetail> orderDetails = daos.getAllOrderDetails(orderId); // Lấy danh sách chi tiết đơn hàng cho order hiện tại
                        BigDecimal grandTotal = BigDecimal.ZERO;

                        for (OrderDetail detail : orderDetails) {
                            // Lấy voucherDetail_id từ detail
                            int voucherDetailId = detail.getVoucherDetailId();

                            // Gọi phương thức discount để lấy giá trị giảm giá (dưới dạng chuỗi)
                            int discount = daos.discount(voucherDetailId);

                            // Tính giá trị totalPrices ban đầu (giá * số lượng)
                            BigDecimal totalPrices = detail.getPrice().multiply(new BigDecimal(detail.getQuantity()));

                            // Tính giá trị giảm giá (discount)
                            BigDecimal discountRate = new BigDecimal(discount).divide(new BigDecimal(100));
                            BigDecimal discountAmount = totalPrices.multiply(discountRate);

                            // Tính tổng giá trị sau khi đã trừ giảm giá
                            BigDecimal totalPrice = totalPrices.subtract(discountAmount);

                            // Cộng dồn giá trị totalPrice vào grandTotal
                            grandTotal = grandTotal.add(totalPrice);

                            // Định dạng totalPrice thành chuỗi để hiển thị
                            String formattedTotalPrice = formatPrices(totalPrice.setScale(2, RoundingMode.HALF_UP).toString());

                            // Gán giá trị totalPrice đã định dạng vào detail
                            detail.setTotalPrice(formattedTotalPrice);
                        }
                        // Sau khi tính toán xong, định dạng grandTotal để hiển thị
                        String formattedGrandTotal = formatPrices(grandTotal.setScale(2, RoundingMode.HALF_UP).toString());
                        order.setGrandTotal(formattedGrandTotal);
                        // Gán danh sách chi tiết đơn hàng vào đối tượng order hiện tại
                        order.setOrderDetails(orderDetails);
                        String method = daos.paymentMethod(orderId);
                        order.setPayment_method(method);
                    }

                }

            }
            request.setAttribute("allOrder", allOrder);
            request.setAttribute("countOrders", counts);
            request.setAttribute("name", name);
            request.getRequestDispatcher("/admin_orders.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btnAccept") != null) {

            String status = "Progress";

            OrderDAO dao = new OrderDAO();
            int order_id = Integer.parseInt(request.getParameter("order_id"));

            dao.updateStatusNew(order_id, status);
            dao.updateStatusDetail(order_id, status);

            response.sendRedirect("/Admin/Orders");

        } else if (request.getParameter("btnComplete") != null) {

            String status = "Delivered";

            OrderDAO dao = new OrderDAO();
            int order_id = Integer.parseInt(request.getParameter("order_id"));

            dao.updateStatusNew(order_id, status);
            dao.updateStatusDetail(order_id, status);

            response.sendRedirect("/Admin/Orders");

        } else if (request.getParameter("btnsearch") != null) {
            // Logic xử lý tìm kiếm khi nút submit được nhấn
            String query = request.getParameter("query");

            // Gọi DAO để tìm kiếm người dùng
            OrderDAO dao = new OrderDAO();
            List<Models.Order> searchResults = dao.searchOrders(query);

            // Đặt kết quả tìm kiếm vào request attribute để hiển thị ở JSP
            request.setAttribute("searchResults", searchResults);

            // Chuyển tiếp đến trang hiển thị danh sách người dùng
            doGet(request, response);
        }
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
