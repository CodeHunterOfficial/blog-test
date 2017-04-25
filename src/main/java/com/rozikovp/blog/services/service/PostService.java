package com.rozikovp.blog.services.service;


import com.rozikovp.blog.services.dao.NewPostDao;
import com.rozikovp.blog.services.dao.PostDao;

public interface PostService {

    /**
     * Returns a blog post for passed id.
     *
     * @param id id of the post
     * @return post data or null if no post is found for passed id
     */
    PostDao getPost(Long id);

    Long addPost(NewPostDao newPostDao);
}
