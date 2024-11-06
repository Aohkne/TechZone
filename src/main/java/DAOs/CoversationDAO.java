/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Conversation;
import Models.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class CoversationDAO {

    public List<Conversation> getConversationsByUserId(int userId) {
        List<Conversation> conversations = new ArrayList<>();
        String sql = "SELECT * FROM Conversation WHERE user_id = ?";
        try ( Connection conn = DBConnection.getConnection(); // Use your DBConnection
                  PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, userId);
            try ( ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Conversation conversation = new Conversation();
                    conversation.setConversationId(rs.getInt("conversation_id"));
                    conversation.setStartTime(rs.getDate("start_time"));
                    conversation.setUserId(rs.getInt("user_id"));
                    conversation.setAdminId(rs.getInt("admin_id"));
                    conversations.add(conversation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return conversations;
    }

    public List<Message> getMessagesByConversationId(int conversationId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message WHERE conversation_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, conversationId);
            try ( ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setMessageId(rs.getInt("message_id"));
                    message.setMessageText(rs.getString("message_text"));
                    message.setTimestamp(rs.getDate("timestamp"));
                    message.setSenderId(rs.getInt("sender_id"));
                    message.setConversationId(rs.getInt("conversation_id"));
                    messages.add(message);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return messages;
    }

    public void sendMessage(int userId, String messageText) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Check if a conversation with userId exists
            String findConversationSql = "SELECT conversation_id FROM Conversation WHERE user_id = ?";
            pst = conn.prepareStatement(findConversationSql);
            pst.setInt(1, userId);
            rs = pst.executeQuery();
            int conversationId = 0;

            if (rs.next()) {
                // Conversation exists, get the conversationId
                conversationId = rs.getInt("conversation_id");
            } else {
                // Conversation doesn't exist, create a new one

                // 1.a Find admin with role = 1
                String findAdminSql = "SELECT user_id FROM Users WHERE role = 1";
                pst = conn.prepareStatement(findAdminSql);
                rs = pst.executeQuery();
                int adminId = 0;
                if (rs.next()) {
                    adminId = rs.getInt("user_id");
                } else {
                    throw new SQLException("No admin found with role 1.");
                }

                // 1.b Insert Conversation
                String insertConversationSql = "INSERT INTO Conversation (user_id, admin_id) VALUES (?, ?)";
                pst = conn.prepareStatement(insertConversationSql, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, userId);
                pst.setInt(2, adminId);
                pst.executeUpdate();

                // 1.c Get Conversation ID
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    conversationId = rs.getInt(1);
                }
            }

            // 2. Insert Message 
            String insertMessageSql = "INSERT INTO Message (message_text, timestamp, sender_id, conversation_id) VALUES (?, GETDATE(), ?, ?)";
            pst = conn.prepareStatement(insertMessageSql);
            pst.setString(1, messageText);
            pst.setInt(2, userId);
            pst.setInt(3, conversationId);
            pst.executeUpdate();

            conn.commit(); // Commit transaction

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            // Close resources in reverse order
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
