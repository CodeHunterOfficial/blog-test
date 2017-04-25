package com.rozikovp.blog.services.service;


import com.rozikovp.blog.services.dao.CommentDao;
import com.rozikovp.blog.services.dao.NewCommentDao;

import java.util.List;

public interface CommentService {

    /**
     * Returns a list of all comments for a blog post with passed id.
     * @param postId id of the post
     * @return list of comments sorted by creation date descending - most recent first
     * @throws IllegalArgumentException if there is no blog post for passed postId
     */
    List<CommentDao> getCommentsForPost(Long postId);

    /**
     * Creates a new comment
     * @param newCommentDao data of new comment
     * @return id of created comment
     * @throws IllegalArgumentException if there is no blog post for passed newCommentDao.postId
     */
    Long addComment(NewCommentDao newCommentDao);
}
