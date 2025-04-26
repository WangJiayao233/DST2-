package cn.edu.zju.dao;

import cn.edu.zju.bean.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDAO {
    private static final Logger log = Logger.getLogger(CommentDAO.class.getName());

    /** JDBC connection URL */
    private final String dbUrl ="jdbc:mysql://127.0.0.1:3306/biomed?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";

    /** Database username*/
    private final String dbUser = "root";

    /** Database password(You can change to your own password)*/
    private final String dbPassword = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                    "The MySQL JDBC driver cannot be loaded:" + e.getMessage());
        }
    }

    /**Save a comment to the database*/
    public void saveComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments(username, content) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, comment.getUsername());
            ps.setString(2, comment.getContent());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                log.log(Level.WARNING, "Inserting a comment does not affect any lines: {0}", comment);
            } else {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        comment.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to save the comment", e);
            throw e;
        }
    }

    public List<Comment> fetchAllComments() throws SQLException {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT id, username, content, created_at FROM comments ORDER BY created_at DESC";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setUsername(rs.getString("username"));
                c.setContent(rs.getString("content"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(c);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to query the comment list\n" +
                    "\n", e);
            throw e;
        }
        return list;
    }

}