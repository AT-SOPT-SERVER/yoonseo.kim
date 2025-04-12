package org.sopt.domain;

import org.sopt.util.TextValidationUtil;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private LocalDateTime createdAt;

    public Post(int id, String title, LocalDateTime createdAt) {
        validateTitle(title);
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

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 비어 있습니다.");
        }

        int titleLength = TextValidationUtil.countEmojiRecognizableCharacters(title);
        if (titleLength > 30) {
            throw new IllegalArgumentException("제목은 최대 30자까지 가능합니다.");
        }
    }
}
