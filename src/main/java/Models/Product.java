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
public class Product {

    private int pro_id;
    private String pro_name;
    private String description;
    private String pro_price;
    private String pro_sale;
    private String madein;
    private Date created_at;
    private Date updated_at;
    private int cat_id;
    private String brand_id;
    private int proDetail_id;
    private String color_name;
    private int pro_quantity;
    private String pro_image;

    public Product() {
    }

    public Product(String pro_name, String pro_price, String pro_sale, String madein) {
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.madein = madein;
    }

    public Product(int pro_id, String pro_name) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
    }

 

    
    public Product(String pro_name, String description, String pro_price, String pro_sale, String madein, int cat_id, String brand_id) {
        this.pro_name = pro_name;
        this.description = description;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.madein = madein;
        this.cat_id = cat_id;
        this.brand_id = brand_id;
    }

    public Product(int pro_id, String pro_name, String pro_price, String pro_sale, int pro_quantity, String madein, String pro_image, int cat_id, String brand_id, int proDetail_id, String color_name) {
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

    public Product(int pro_id, String pro_name, String pro_price, String pro_image, int proDetail_id) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_image = pro_image;
        this.proDetail_id = proDetail_id;
    }

    public Product(int pro_id, String pro_name, String pro_price, String pro_sale, String pro_image, int cat_id, int proDetail_id) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.pro_image = pro_image;
        this.cat_id = cat_id;
        this.proDetail_id = proDetail_id;
    }

    public Product(int pro_id, String pro_name, String pro_price, String pro_sale, String pro_image) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.pro_image = pro_image;

    }

    public Product(int pro_id, String pro_name, String description, String pro_price, String pro_sale, int pro_quantity, String madein, Date created_at, Date updated_at, String pro_image, int cat_id, String brand_id) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.description = description;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.pro_quantity = pro_quantity;
        this.madein = madein;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.pro_image = pro_image;
        this.cat_id = cat_id;
        this.brand_id = brand_id;
    }

    public Product(int pro_id, String pro_name, String description, String pro_price, String pro_sale, String madein, String pro_image, int cat_id, String brand_id) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.description = description;
        this.pro_price = pro_price;
        this.pro_sale = pro_sale;
        this.madein = madein;
        this.pro_image = pro_image;
        this.cat_id = cat_id;
        this.brand_id = brand_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getProDetail_id() {
        return proDetail_id;
    }

    public void setProDetail_id(int proDetail_id) {
        this.proDetail_id = proDetail_id;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
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

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

}
