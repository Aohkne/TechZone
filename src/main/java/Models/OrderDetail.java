/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class OrderDetail {

    private int orderDetailId;
    private int quantity;
    private BigDecimal price;
    private int orderId;
    private int proDetailId;
    private int voucherDetailId;
    private String status;
    private String check;

    // thêm để lấy in ra notification cho dễ
    private String proName;
    private Date orderDate;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int quantity, BigDecimal price, int orderId, int proDetailId, int voucherDetailId, String status, String check, String proName, Date orderDate) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.proDetailId = proDetailId;
        this.voucherDetailId = voucherDetailId;
        this.status = status;
        this.check = check;
        this.proName = proName;
        this.orderDate = orderDate;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProDetailId() {
        return proDetailId;
    }

    public void setProDetailId(int proDetailId) {
        this.proDetailId = proDetailId;
    }

    public int getVoucherDetailId() {
        return voucherDetailId;
    }

    public void setVoucherDetailId(int voucherDetailId) {
        this.voucherDetailId = voucherDetailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
