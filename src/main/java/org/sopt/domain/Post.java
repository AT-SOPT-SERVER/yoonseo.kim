package org.sopt.domain;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private LocalDateTime createdAt;

    public Post(int id, String title, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Post(String title) {
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
