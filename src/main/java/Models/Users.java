/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Users {
    private String username;
    private String password;
    private String email;
    private int phone;
    private String address;
    private int role;
    private Date create_at;
    private String avartar;
    private boolean status_user;

    public Users() {
    }

    public Users(String username, String password, String email, int role, Date create_at, String avartar, boolean status_user) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.create_at = create_at;
        this.avartar = avartar;
        this.status_user = status_user;
    }
    

    public Users(String username, String password, String email, int phone, String address, int role, Date create_at, String avartar, boolean status_user) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.create_at = create_at;
        this.avartar = avartar;
        this.status_user = status_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public boolean isStatus_user() {
        return status_user;
    }

    public void setStatus_user(boolean status_user) {
        this.status_user = status_user;
    }
    
}