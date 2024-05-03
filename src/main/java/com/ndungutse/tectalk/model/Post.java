package com.ndungutse.tectalk.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// The annotation @GeneratedValue(generator="UUID")is used
// in JPA(Java Persistence API)to instruct a persistence provider(like Hibernate)to
// automatically generate a Universally Unique Identifier(UUID)value for a specific
// field in your entity class.
// Here'sa breakdown of what the annotation does:
// @GeneratedValue itself is used to specify how
// a value for the annotated field is generated.
// generator = "UUID" defines the name of the generator to
// be used. In this case, it's set to "UUID", indicating
// that a UUID should be generated.

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(UUID id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
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
        return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt + "]";
    }

}
