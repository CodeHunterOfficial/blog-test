package com.rozikovp.blog.webapp;

import com.rozikovp.blog.Application;
import com.rozikovp.blog.MockedTestContext;
import com.rozikovp.blog.TestUtil;
import com.rozikovp.blog.config.RestContext;
import com.rozikovp.blog.services.dao.NewCommentDao;
import com.rozikovp.blog.services.dao.NewPostDao;
import com.rozikovp.blog.services.dao.PostDao;
import com.rozikovp.blog.services.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ParvizR on 25/04/2017.
 *
 * @author ParvizR
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestContext.class, MockedTestContext.class})
@WebAppConfiguration
public class PostControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(postService);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getPost() throws Exception {
        // given
        Date creationDate = new Date();
        PostDao post = new PostDao("Title", "content", creationDate);

        // when
        when(postService.getPost(1L)).thenReturn(post);

        // then
        mockMvc.perform(get("/post/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.content", is("content")))
                .andExpect(jsonPath("$.creationDate", is(creationDate.getTime())));

    }

    @Test
    public void addPost() throws Exception {
        // given
        NewPostDao newPostDao = createPost("Test content", "Test title");

        // when
        when(postService.addPost(newPostDao)).thenReturn(1L);

        // then
        mockMvc.perform(post("/post")
                .content(TestUtil.convertObjectToJsonBytes(newPostDao))
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private NewPostDao createPost(String content, String title) {
        NewPostDao newPostDao = new NewPostDao();
        //newPostDao.setPostId(1L);
        newPostDao.setContent(content);
        newPostDao.setTitle(title);
        return newPostDao;
    }
}