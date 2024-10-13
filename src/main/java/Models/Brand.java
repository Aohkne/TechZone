/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author HP
 */
public class Brand {
    private int brand_id;
    private String brand_name;
    private String description;

    public Brand() {
    }

    public Brand(String brand_name, String description) {
        this.brand_name = brand_name;
        this.description = description;
    }

    public Brand(int brand_id, String brand_name, String description) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.description = description;
    }

    
    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }
    
}
