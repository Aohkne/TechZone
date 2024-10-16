/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class Product_Details {
    private int proDetail_id;
    private String color_name;
    private int quantity;
    private String image;
    private int pro_id;

    public Product_Details() {
    }

    public Product_Details(int proDetail_id, String color_name, int quantity, String image, int pro_id) {
        this.proDetail_id = proDetail_id;
        this.color_name = color_name;
        this.quantity = quantity;
        this.image = image;
        this.pro_id = pro_id;
    }

    public Product_Details(int proDetail_id, String image) {
        this.proDetail_id = proDetail_id;
        this.image = image;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }
    
}
