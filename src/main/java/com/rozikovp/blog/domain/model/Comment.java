package com.rozikovp.blog.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ParvizR on 20/04/2017.
 *
 * @author ParvizR
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long postId;

    @Column(length = 4096)
    private String content;

    @Column
    private String author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost() {
        return postId;
    }

    public void setPost(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
