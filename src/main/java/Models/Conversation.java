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
public class Conversation {

    private int conversationId;
    private Date startTime;
    private int userId;
    private int adminId;

    public Conversation() {
    }

    public Conversation(int conversationId, Date startTime, int userId, int adminId) {
        this.conversationId = conversationId;
        this.startTime = startTime;
        this.userId = userId;
        this.adminId = adminId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

}
