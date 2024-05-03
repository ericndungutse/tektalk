package com.ndungutse.tectalk.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostDto {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public PostDto(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PostDto [id=" + id + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt + "]";
    }

}
