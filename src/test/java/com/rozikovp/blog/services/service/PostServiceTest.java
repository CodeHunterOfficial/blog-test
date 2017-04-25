package com.rozikovp.blog.services.service;

import com.rozikovp.blog.Application;
import com.rozikovp.blog.domain.model.Post;
import com.rozikovp.blog.domain.repository.PostRepository;
import com.rozikovp.blog.config.ServicesContext;
import com.rozikovp.blog.services.dao.PostDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by ParvizR on 24/04/2017.
 *
 * @author ParvizR
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, ServicesContext.class})
@Transactional
public class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    public void shouldReturnCreatedPost() throws Exception {
        Post post = new Post();
        post.setTitle("Test title");
        post.setContent("Test content");
        Date creationDate = new Date();
        post.setCreationDate(creationDate);
        postRepository.save(post);

        PostDao postDao = postService.getPost(post.getId());

        assertNotNull("Post shouldn't be null", postDao);
        assertThat(postDao.getContent(), equalTo("Test content"));
        assertThat(postDao.getTitle(), equalTo("Test title"));
        assertThat(postDao.getCreationDate(), equalTo(creationDate));
    }

    @Test
    public void shouldReturnNullForNotExistingPost() {
        PostDao postDao = postService.getPost(123L);

        assertNull(postDao);
    }


}