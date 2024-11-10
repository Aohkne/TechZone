/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Comment;
import java.sql.Connection;
import java.sql.Date;
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

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

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

//    public boolean deleteBrand(int brandId) {
//        Connection conn = DBConnection.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            // Kết nối tới database
//
//            // Kiểm tra xem còn sản phẩm nào thuộc brand_id này không
//            String checkSql = "SELECT COUNT(*) AS product_count FROM Product WHERE brand_id = ?";
//            ps = conn.prepareStatement(checkSql);
//            ps.setInt(1, brandId);
//            rs = ps.executeQuery();
//
//            if (rs.next() && rs.getInt("product_count") > 0) {
//                // Nếu còn sản phẩm, trả về false và không thực hiện xóa
//                return false;
//            }
//
//            // Nếu không còn sản phẩm, thực hiện xóa brand
//            String deleteSql = "DELETE FROM Brand WHERE brand_id = ?";
//            ps = conn.prepareStatement(deleteSql);
//            ps.setInt(1, brandId);
//            ps.executeUpdate();
//
//            return true;
//        } catch (SQLException e) {
//            return false;
//        } finally {
//            // Đóng kết nối và PreparedStatement
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
    public List<Comment> searchComment(String query) {
        List<Comment> commentList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        ResultSet rs = null;

        if (conn != null) {
            try {
                String sql;
                PreparedStatement pst;

                // Kiểm tra nếu query không trống
                if (query != null && !query.trim().isEmpty()) {
                    sql = "SELECT * FROM [Comment] WHERE contents LIKE ?";
                    pst = conn.prepareStatement(sql);
                    String searchQuery = "%" + query + "%"; // Thêm ký tự wildcard để tìm kiếm gần đúng
                    pst.setString(1, searchQuery);
                } else {
                    // Nếu không có từ khóa tìm kiếm, lấy tất cả comment
                    sql = "SELECT * FROM [Comment]";
                    pst = conn.prepareStatement(sql);
                }

                rs = pst.executeQuery();

                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setComment_id(rs.getInt("comment_id"));
                    comment.setContents(rs.getString("contents"));

                    // Lấy giá trị `created_at` từ ResultSet và chuyển đổi
                    java.sql.Date sqlDate = rs.getDate("created_at");
                    if (sqlDate != null) {
                        comment.setCreate_at(new Date(sqlDate.getTime()));
                    }

                    comment.setPro_id(rs.getInt("pro_id"));
                    comment.setUser_id(rs.getInt("user_id"));

                    commentList.add(comment);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Đảm bảo đóng kết nối và tài nguyên
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return commentList;
    }

    public List<Comment> getAllCommentsSorted() {
        List<Comment> commentList = new ArrayList<>(); // Tạo danh sách để lưu trữ comment
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                // Truy vấn bảng Comment và sắp xếp theo created_at giảm dần
                rs = st.executeQuery("SELECT * FROM [Comment] ORDER BY comment_id DESC");

                // Duyệt qua ResultSet và tạo đối tượng Comment
                while (rs.next()) {
                    Comment comment = new Comment(); // Tạo một đối tượng Comment mới
                    // Thiết lập các giá trị từ ResultSet
                    comment.setComment_id(rs.getInt("comment_id"));
                    comment.setContents(rs.getString("contents"));

                    // Lấy giá trị `created_at` từ ResultSet và chuyển đổi
                    java.sql.Date sqlDate = rs.getDate("created_at");
                    if (sqlDate != null) {
                        comment.setCreate_at(new Date(sqlDate.getTime())); // Chuyển đổi sang java.util.Date
                    }

                    comment.setPro_id(rs.getInt("pro_id"));
                    comment.setUser_id(rs.getInt("user_id"));

                    // Thêm comment vào danh sách
                    commentList.add(comment);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log lỗi
            } finally {
                // Đóng ResultSet và Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Log lỗi khi đóng
                }
            }
        }
        return commentList; // Trả về danh sách comment
    }

    public List<Comment> GetAllComments() {
        List<Comment> commentList = new ArrayList<>(); // Tạo danh sách để lưu trữ comment
        Connection conn = DBConnection.getConnection(); // Kết nối đến cơ sở dữ liệu
        ResultSet rs = null;

        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT * FROM [Comment]");

                // Duyệt qua ResultSet và tạo đối tượng Comment
                while (rs.next()) {
                    Comment comment = new Comment(); // Tạo một đối tượng Comment mới
                    // Thiết lập các giá trị từ ResultSet
                    comment.setComment_id(rs.getInt("comment_id"));
                    comment.setContents(rs.getString("contents"));
                    // Lấy giá trị `created_at` từ ResultSet và chuyển đổi
                    java.sql.Date sqlDate = rs.getDate("created_at");
                    if (sqlDate != null) {
                        comment.setCreate_at(new Date(sqlDate.getTime())); // Chuyển đổi sang java.util.Date
                    }
                    comment.setPro_id(rs.getInt("pro_id"));
                    comment.setUser_id(rs.getInt("user_id"));

                    // Thêm comment vào danh sách
                    commentList.add(comment);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log lỗi
            } finally {
                // Đóng ResultSet và Connection
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Log lỗi khi đóng
                }
            }
        }
        return commentList; // Trả về danh sách comment
    }

    public int GetTotalComment() {
        Connection conn = DBConnection.getConnection();
        int userCount = 0; // Change variable name to better reflect its purpose
        if (conn != null) {
            try {
                // Query to count users with role=2
                String sql = "SELECT COUNT(*) FROM Comment";
                PreparedStatement pst = conn.prepareStatement(sql);

                // Execute the query and get the result
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    userCount = rs.getInt(1); // Get the count of users
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userCount;
    }

    public String getProductName(int id) {
        Connection conn = DBConnection.getConnection();
        String username = "";
        if (conn != null) {
            try {
                String sql = "SELECT pro_name FROM Product WHERE pro_id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);  // Sử dụng id thay vì email
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    username = rs.getString("pro_name");
                }
            } catch (SQLException ex) {
            }
        }
        return username;
    }
}
