package com.rozikovp.blog.webapp;

import com.rozikovp.blog.MockedTestContext;
import com.rozikovp.blog.TestUtil;
import com.rozikovp.blog.config.RestContext;
import com.rozikovp.blog.services.dao.CommentDao;
import com.rozikovp.blog.services.dao.NewCommentDao;
import com.rozikovp.blog.services.service.CommentService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class CommentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CommentService commentService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(commentService);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCommentForPost() throws Exception {
        // given
        List<CommentDao> comments = new ArrayList<CommentDao>();
        Date creationDate = new Date();
        comments.add(new CommentDao(2L, "comment content", "John Smith", creationDate));

        // when
        when(commentService.getCommentsForPost(1L)).thenReturn(comments);

        // then
        mockMvc.perform(get("/post/1/comments").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].content", is("comment content")))
                .andExpect(jsonPath("$[0].author", is("John Smith")))
                .andExpect(jsonPath("$[0].creationDate", is(creationDate.getTime())));
    }

    @Test
    public void createCommentForPost() throws Exception {
        // given
        NewCommentDao newComment = createComment("Test content", "John Doe");

        // when
        when(commentService.addComment(newComment)).thenReturn(1L);

        // then
        mockMvc.perform(post("/post/1/comment")
                .content(TestUtil.convertObjectToJsonBytes(newComment))
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private NewCommentDao createComment(String content, String author) {
        NewCommentDao newComment = new NewCommentDao();
        //newComment.setPostId(1L);
        newComment.setContent(content);
        newComment.setAuthor(author);
        return newComment;
    }


}