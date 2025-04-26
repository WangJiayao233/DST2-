package cn.edu.zju.servlet;

import cn.edu.zju.bean.Comment;
import cn.edu.zju.dao.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handle the display (GET /comments) and submission (POST /comments) in the comment section
 */
@WebServlet("/comments")
public class CommentServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CommentServlet.class.getName());
    private final CommentDAO commentDAO = new CommentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Comment> comments = commentDAO.fetchAllComments();
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/views/comments.jsp")
                    .forward(req, resp);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to load the comment list: ", e);
            // For debugging: Print the exception message directly
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().write("Error in loading comments: "+ e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String content  = req.getParameter("content");

        if (username == null || username.trim().isEmpty() ||
                content == null  || content.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Nickname and content are both required fields");
            return;
        }

        Comment newComment = new Comment(username, content);
        try {
            commentDAO.saveComment(newComment);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "An error occurred when saving the comment: ", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Failed to save the comment");
            return;
        }

        // After success, redirect to avoid duplicate submission of the form
        resp.sendRedirect(req.getContextPath() + "/comments?active=comments");
    }
}