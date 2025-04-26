<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="true" %>
<%@ page import="java.util.List, cn.edu.zju.bean.Comment" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Comment Section</title>
    <!-- Bootstrap style -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-…"
            crossorigin="anonymous">
    <!-- Feather Icons -->
    <script src="https://unpkg.com/feather-icons"></script>
    <!-- Style -->
    <link
            rel="stylesheet"
            href="<%= request.getContextPath() %>/static/css/comments.css">
</head>
<body>
<div class="container my-5 comment-panel">
    <!-- Return to the home page button -->
    <p class="mb-4">
        <a
                href="<%= request.getContextPath() %>/"
                class="btn btn-outline-secondary btn-sm">
            <i data-feather="arrow-left"></i> Back Home
        </a>
    </p>

    <!-- Comment input area -->
    <section id="postComment" class="mb-5">
        <h5 class="mb-3">Add a Comment</h5>
        <!-- commentTitle: Return to the home page button -->
        <form
                action="<%= request.getContextPath() %>/comments"
                method="post"
                class="row g-3 needs-validation"
                novalidate>
            <!-- Nickname -->
            <div class="col-md-4">
                <label for="userName" class="form-label">Nickname</label>
                <input
                        type="text"
                        id="userName"
                        name="username"
                        class="form-control"
                        placeholder="Enter your nickname"
                        required>
                <div class="invalid-feedback">
                    The nickname cannot be empty
                </div>
            </div>
            <!-- Content -->
            <div class="col-md-8">
                <label for="commentBody" class="form-label">Content</label>
                <textarea
                        id="commentBody"
                        name="content"
                        class="form-control"
                        rows="4"
                        placeholder="Write down your thoughts…"
                        required></textarea>
                <div class="invalid-feedback">
                    Write down your thoughts
                </div>
            </div>
            <!-- submit button -->
            <div class="col-12">
                <button type="submit" class="btn btn-primary">
                    Submit
                </button>
            </div>
        </form>
    </section>

    <!-- Comment list display -->
    <section id="commentList">
        <h5 class="mb-3">Latest comments</h5>
        <%
            // commentsList: The List<Comment> passed from the Servlet
            List<Comment> commentsList =
                    (List<Comment>) request.getAttribute("comments");
            if (commentsList != null) {
                for (Comment entry : commentsList) {
        %>
        <div class="card comment-item mb-3">
            <div class="card-header py-2 px-3">
                <span class="fw-bold"><%= entry.getUsername() %></span>
                <small class="text-muted float-end">
                    <%= entry.getCreatedAt() %>
                </small>
            </div>
            <div class="card-body px-3 py-2">
                <p class="mb-0"><%= entry.getContent()
                        .replaceAll("\n","<br/>") %></p>
            </div>
        </div>
        <%   }
        } else { %>
        <p class="text-muted">No comments yet. Come and be the first to comment!</p>
        <% } %>
    </section>
</div>

<!-- client-side scripting -->
<script>
    (function() {
        'use strict';
        // Bootstrap form validation enabled
        var forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms).forEach(function(frm) {
            frm.addEventListener('submit', function(evt) {
                if (!frm.checkValidity()) {
                    evt.preventDefault();
                    evt.stopPropagation();
                }
                frm.classList.add('was-validated');
            }, false);
        });
        // Render Feather Icons
        feather.replace();
    })();
</script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-…"
        crossorigin="anonymous"></script>
</body>
</html>