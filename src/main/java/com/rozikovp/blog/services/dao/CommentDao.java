package com.rozikovp.blog.services.dao;

import java.util.Date;

public class CommentDao {

    private Long id;

    private String content;

    private String author;

    private Date creationDate;

    public CommentDao(Long id, String content, String author, Date creationDate) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
