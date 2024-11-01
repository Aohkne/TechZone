/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;

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

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int quantity, BigDecimal price, int orderId, int proDetailId, int voucherDetailId) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.proDetailId = proDetailId;
        this.voucherDetailId = voucherDetailId;
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

}
