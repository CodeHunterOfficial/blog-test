package com.rozikovp.blog.services.service;

import com.rozikovp.blog.MockedTestContext;
import com.rozikovp.blog.config.ServicesContext;
import com.rozikovp.blog.domain.model.Post;
import com.rozikovp.blog.domain.repository.PostRepository;
import com.rozikovp.blog.services.dao.CommentDao;
import com.rozikovp.blog.services.dao.NewCommentDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by ParvizR on 24/04/2017.
 *
 * @author ParvizR
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockedTestContext.class, ServicesContext.class})
@Transactional
public class CommentServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentService commentService;

    @Before
    public void setUp() {
        Mockito.reset(postRepository);
    }

    @Test
    public void getCommentsForPost() throws Exception {
        Post post = createTestPost();

        NewCommentDao comment = new NewCommentDao();
        comment.setPostId(post.getId());
        comment.setAuthor("Author");
        comment.setContent("Content");

        commentService.addComment(comment);

        List<CommentDao> comments = commentService.getCommentsForPost(post.getId());

        assertThat("There should be one comment", comments, hasSize(1));
        assertThat(comments.get(0).getAuthor(), equalTo("Author"));
        assertThat(comments.get(0).getContent(), equalTo("Content"));
    }

    @Test
    public void addComment() throws Exception {
        Post post = createTestPost();

        NewCommentDao comment = new NewCommentDao();
        comment.setPostId(post.getId());
        comment.setAuthor("Author");
        comment.setContent("Content");
        Long commentId = commentService.addComment(comment);

        assertThat("Comment id shouldn't be null", commentId, notNullValue());

    }

    private Post createTestPost() {
        Post post = new Post();
        post.setTitle("Test title");
        post.setContent("Test content");
        Date creationDate = new Date();
        post.setCreationDate(creationDate);
        postRepository.save(post);
        return post;
    }

}