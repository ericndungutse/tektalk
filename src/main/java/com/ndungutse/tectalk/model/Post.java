package com.ndungutse.tectalk.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
@Table(name = "posts", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    // CASCADE>ALL: Any operation to parent is applied to children
    // Orphan Remove: Remove child if parent removed
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    // No List? It creates duplicate
    private Set<Comment> comments = new HashSet<>();

    public Post() {
    }

    public Post(Long id, String title, String content, String description) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
                + ", comments=" + comments + "]";
    }

}
