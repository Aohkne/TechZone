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
public class Comment {
    private int comment_id;
    private String contents;
    private Date create_at;
    
    private int pro_id;
    private int user_id;
    
    private String product_name;
    private String username;
    

    public Comment() {
    }

    public Comment(int comment_id, String contents, Date create_at, int pro_id, int user_id) {
        this.comment_id = comment_id;
        this.contents = contents;
        this.create_at = create_at;
        this.pro_id = pro_id;
        this.user_id = user_id;
    }

    public Comment(int comment_id, String contents, Date create_at, int pro_id, int user_id, String product_name, String username) {
        this.comment_id = comment_id;
        this.contents = contents;
        this.create_at = create_at;
        this.pro_id = pro_id;
        this.user_id = user_id;
        this.product_name = product_name;
        this.username = username;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
