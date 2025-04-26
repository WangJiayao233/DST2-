package cn.edu.zju.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {
    /**  Primary Key */
    private int id;

    /** NickName */
    private String username;

    /** content */
    private String content;

    /** Time */
    private Timestamp createdAt;

    public Comment() { }

    public Comment(String username, String content) {
        this.username = username;
        this.content = content;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // -------- Getter & Setter --------

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // -------- Override toString, equals, hashCode --------

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment that = (Comment) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(content, that.content) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, content, createdAt);
    }
}