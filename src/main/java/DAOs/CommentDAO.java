/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CommentDAO {

    public boolean addComment(String contents, int pro_id, int user_id) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            String sql = "INSERT INTO Comment (contents, pro_id, user_id) VALUES (?, ?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, contents);
                ps.setInt(2, pro_id);
                ps.setInt(3, user_id);
                int rowsInserted = ps.executeUpdate();
                return rowsInserted > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteCommentById(String comment_id) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            String sql = "DELETE FROM Comment WHERE comment_id = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, comment_id);
                int rowsDeleted = ps.executeUpdate();
                return rowsDeleted > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public int countCommentsByProId(int pro_id) {
        Connection conn = DBConnection.getConnection();
        int count = 0; 
        if (conn != null) {
            String sql = "SELECT COUNT(*) FROM Comment WHERE pro_id = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, pro_id); 
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return count; 
    }

    public boolean updateCommentContentById(String comment_id, String contents) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            String sql = "UPDATE Comment SET contents = ? WHERE comment_id = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, contents); 
                ps.setString(2, comment_id);  
                int rowsUpdated = ps.executeUpdate();
                return rowsUpdated > 0; 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public Map<Integer, List<Object[]>> getUserCommentsByProductId(int proId) {
        Map<Integer, List<Object[]>> commentMap = new HashMap<>();

        String query = "SELECT  u.user_id, u.username, u.avatar, c.created_at, c.contents, c.comment_id "
                + "FROM Users u "
                + "JOIN Comment c ON u.user_id = c.user_id "
                + "WHERE c.pro_id = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, proId);
            ResultSet resultSet = pst.executeQuery();

            List<Object[]> commentList = new ArrayList<>();

            while (resultSet.next()) {
                Object[] commentData = new Object[5];
                commentData[0] = resultSet.getString("username");
                commentData[1] = resultSet.getString("avatar");
                commentData[2] = resultSet.getDate("created_at");
                commentData[3] = resultSet.getString("contents");
                commentData[4] = resultSet.getInt("comment_id");

                commentList.add(commentData);
            }

            commentMap.put(proId, commentList);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return commentMap;
    }


}
