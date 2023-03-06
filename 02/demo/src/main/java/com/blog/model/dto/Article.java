package com.blog.model.dto;

/**
 * DTO (Data transport object) - for communication between services
 */
public class Article {

    private Long id;

    private String title;

    private String description;

    private String text;

    public Article() {
    }

    public Article(Long id, String title, String description, String text) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.text = text;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
