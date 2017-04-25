package com.rozikovp.blog.services.dao;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by ParvizR on 25/04/2017.
 *
 * @author ParvizR
 */

public class NewPostDao {

    private String title;
    private String content;
    private Date   creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
}
