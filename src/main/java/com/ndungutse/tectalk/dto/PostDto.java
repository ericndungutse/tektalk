package com.ndungutse.tectalk.dto;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String description;

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

    @Override
    public String toString() {
        return "PostDto [id=" + id + ", title=" + title + ", content=" + content + ", description=" + description + "]";
    }

}
