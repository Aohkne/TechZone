/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class Voucher {

    private int voucherDetail_id;  // Thay đổi tên để giống với tên trong SQL
    private String voucher_name;    // Thay đổi tên để giống với tên trong SQL
    private int voucher_quantity;    // Thay đổi tên để giống với tên trong SQL
    private int voucher_discount;    // Thay đổi tên để giống với tên trong SQL
    private Date voucher_date;       // Thay đổi tên để giống với tên trong SQL
    private Date voucher_expire_date; // Thay đổi tên để giống với tên trong SQL
    private int user_id;             // Thay đổi tên để giống với tên trong SQL

    // Fields from Voucher table
    private int voucher_id;          // Thay đổi tên để giống với tên trong SQL
    private String voucher_type;      // Thay đổi tên để giống với tên trong SQL
    private String voucher_img;       // Thay đổi tên để giống với tên trong SQL
    private String voucher_description; // Thay đổi tên để giống với tên trong SQL

    // Getters and Setters
    public int getVoucherDetail_id() {
        return voucherDetail_id;
    }

    public void setVoucherDetail_id(int voucherDetail_id) {
        this.voucherDetail_id = voucherDetail_id;
    }

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }

    public int getVoucher_quantity() {
        return voucher_quantity;
    }

    public void setVoucher_quantity(int voucher_quantity) {
        this.voucher_quantity = voucher_quantity;
    }

    public int getVoucher_discount() {
        return voucher_discount;
    }

    public void setVoucher_discount(int voucher_discount) {
        this.voucher_discount = voucher_discount;
    }

    public Date getVoucher_date() {
        return voucher_date;
    }

    public void setVoucher_date(Date voucher_date) {
        this.voucher_date = voucher_date;
    }

    public Date getVoucher_expire_date() {
        return voucher_expire_date;
    }

    public void setVoucher_expire_date(Date voucher_expire_date) {
        this.voucher_expire_date = voucher_expire_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(int voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_type() {
        return voucher_type;
    }

    public void setVoucher_type(String voucher_type) {
        this.voucher_type = voucher_type;
    }

    public String getVoucher_img() {
        return voucher_img;
    }

    public void setVoucher_img(String voucher_img) {
        this.voucher_img = voucher_img;
    }

    public String getVoucher_description() {
        return voucher_description;
    }

    public void setVoucher_description(String voucher_description) {
        this.voucher_description = voucher_description;
    }

}
