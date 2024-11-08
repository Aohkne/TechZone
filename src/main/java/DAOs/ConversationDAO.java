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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
public class ConversationDAO {

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

    public boolean sendMessageAdmin(String messageText, int senderId, int conversationId) {
        String query = "INSERT INTO Message (message_text, timestamp, sender_id, conversation_id) "
                + "VALUES (?, GETDATE(), ?, ?)";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {

            // Thiết lập giá trị cho các tham số trong câu lệnh SQL
            pst.setString(1, messageText);
            pst.setInt(2, senderId);  // sender_id
            pst.setInt(3, conversationId);  // conversation_id

            // Thực thi câu lệnh insert
            int rowsAffected = pst.executeUpdate();

            // Nếu insert thành công, rowsAffected > 0
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Map<Integer, List<Object[]>> getUserConversationsByAdminId(int adminId) {
        Map<Integer, List<Object[]>> conversationMap = new HashMap<>();

        String query = "WITH LatestMessage AS ( "
                + "    SELECT conversation_id, sender_id, message_text, timestamp, "
                + "           ROW_NUMBER() OVER (PARTITION BY conversation_id ORDER BY timestamp DESC) AS row_num "
                + "    FROM Message "
                + ") "
                + "SELECT u.user_id, u.username, u.avatar, c.conversation_id, lm.message_text, lm.timestamp "
                + "FROM Conversation c "
                + "JOIN Users u ON c.user_id = u.user_id "
                + "LEFT JOIN LatestMessage lm ON c.conversation_id = lm.conversation_id "
                + "    AND c.user_id = lm.sender_id "
                + "    AND lm.row_num = 1 "
                + "WHERE c.admin_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, adminId);
            ResultSet resultSet = pst.executeQuery();

            List<Object[]> conversationList = new ArrayList<>();

            while (resultSet.next()) {
                Object[] conversationData = new Object[6];
                conversationData[0] = resultSet.getInt("user_id");
                conversationData[1] = resultSet.getString("username");
                conversationData[2] = resultSet.getString("avatar");
                conversationData[3] = resultSet.getInt("conversation_id");
                conversationData[4] = resultSet.getString("message_text"); // null if no message
                conversationData[5] = resultSet.getDate("timestamp"); // timestamp of the latest message

                conversationList.add(conversationData);
            }

            conversationMap.put(adminId, conversationList);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conversationMap;
    }

    public Map<Integer, List<Object[]>> getConversationsByAdminId(int adminId) {
        Map<Integer, List<Object[]>> conversationMap = new HashMap<>();

        String query = "SELECT u.user_id, u.username, u.avatar, c.conversation_id, m.message_text, m.sender_id, m.timestamp "
                + "FROM Conversation c "
                + "JOIN Users u ON c.user_id = u.user_id "
                + "LEFT JOIN Message m ON c.conversation_id = m.conversation_id "
                + "WHERE c.admin_id = ? "
                + "ORDER BY c.conversation_id, m.timestamp";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, adminId);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int conversationId = resultSet.getInt("conversation_id");

                // Kiểm tra xem conversationId đã tồn tại chưa, nếu chưa thì thêm mới
                List<Object[]> messageList = conversationMap.get(conversationId);
                if (messageList == null) {
                    messageList = new ArrayList<>();
                    conversationMap.put(conversationId, messageList);
                }

                // Tạo một mảng chứa các thông tin tin nhắn
                Object[] messageData = new Object[7];
                messageData[0] = resultSet.getInt("user_id");         // User ID
                messageData[1] = resultSet.getString("username");      // Username
                messageData[2] = resultSet.getString("avatar");        // Avatar
                messageData[3] = conversationId;                       // Conversation ID
                messageData[4] = resultSet.getString("message_text");  // Message Text
                messageData[5] = resultSet.getInt("sender_id");        // Sender ID
                messageData[6] = resultSet.getTimestamp("timestamp");  // Message Timestamp

                // Thêm thông tin tin nhắn vào danh sách tương ứng với conversationId
                messageList.add(messageData);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conversationMap;
    }

}
