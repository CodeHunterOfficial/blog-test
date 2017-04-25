package com.rozikovp.blog.services.dao;

import java.util.Date;

public class PostDao {

    private String title;

    private String content;

    private Date creationDate;

    public PostDao(String title, String content, Date creationDate) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
