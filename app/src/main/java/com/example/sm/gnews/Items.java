package com.example.sm.gnews;

public class Items {
    String author;
    String title;
    String description;
    private String urltoImage;
    private String PublishAt;
    private String url;
    String content;


    public Items(String author, String title, String description, String urltoImage, String publishAt, String content, String url) {
        this.setAuthor(author);
        this.setTitle(title);
        this.setDescription(description);
        this.setUrltoImage(urltoImage);
        this.setPublishAt(publishAt);
        this.setContent(content);
        this.setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getUrltoImage() {
        return urltoImage;
    }

    private void setUrltoImage(String urltoImage) {
        this.urltoImage = urltoImage;
    }

    public String getPublishAt() {
        return PublishAt;
    }

    public void setPublishAt(String publishAt) {
        PublishAt = publishAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
