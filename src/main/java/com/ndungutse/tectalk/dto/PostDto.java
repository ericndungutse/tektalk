package com.ndungutse.tectalk.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title ahould have atleast 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post description should atleast be 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments = new HashSet<>();

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

    public String getDesciption() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostDto [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
                + ", comments=" + comments + "]";
    }

}
