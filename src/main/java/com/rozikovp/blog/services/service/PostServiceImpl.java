package com.rozikovp.blog.services.service;


import com.rozikovp.blog.domain.model.Post;
import com.rozikovp.blog.domain.repository.PostRepository;
import com.rozikovp.blog.services.dao.NewPostDao;
import com.rozikovp.blog.services.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDao getPost(Long id) {
        Post post = postRepository.findOne(id);
        if (post != null) {
            return new PostDao(post.getTitle(), post.getContent(), post.getCreationDate());
        } else {
            return null;
        }
    }

    @Override
    public Long addPost(NewPostDao newPostDao) {
        Post post = new Post();
        post.setContent(newPostDao.getContent());
        post.setTitle(newPostDao.getTitle());
        post.setCreationDate((newPostDao.getCreationDate() != null)
                ? newPostDao.getCreationDate() : new Date());
        return postRepository.save(post).getId();
    }
}
