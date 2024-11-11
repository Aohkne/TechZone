/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

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

    private int pro_id;
    private String pro_name;
    private String pro_price;
    private String pro_sale;
    private int pro_quantity;
    private String madein;
    private String pro_image;
    private int cat_id;
    private String brand_id;
    private int proDetail_id;
    private String color_name;

    private String totalPrice;

    // thêm để lấy in ra notification cho dễ
    private String proName;
    private Date orderDate;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int quantity, BigDecimal price, int orderId, int proDetailId, int voucherDetailId, String status, String check, int pro_id, String pro_name, String pro_price, String pro_sale, int pro_quantity, String madein, String pro_image, int cat_id, String brand_id, int proDetail_id, String color_name) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.proDetailId = proDetailId;
        this.voucherDetailId = voucherDetailId;
        this.status = status;
        this.check = check;
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.pro_quantity = pro_quantity;
        this.madein = madein;
        this.pro_image = pro_image;
        this.cat_id = cat_id;
        this.brand_id = brand_id;
        this.proDetail_id = proDetail_id;
        this.color_name = color_name;
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

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_price() {
        return pro_price;
    }

    public void setPro_price(String pro_price) {
        this.pro_price = pro_price;
    }

    public String getPro_sale() {
        return pro_sale;
    }

    public void setPro_sale(String pro_sale) {
        this.pro_sale = pro_sale;
    }

    public int getPro_quantity() {
        return pro_quantity;
    }

    public void setPro_quantity(int pro_quantity) {
        this.pro_quantity = pro_quantity;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public int getProDetail_id() {
        return proDetail_id;
    }

    public void setProDetail_id(int proDetail_id) {
        this.proDetail_id = proDetail_id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
