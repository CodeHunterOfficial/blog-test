package com.rozikovp.blog;

import com.rozikovp.blog.domain.repository.CommentRepository;
import com.rozikovp.blog.domain.repository.PostRepository;
import com.rozikovp.blog.services.service.CommentService;
import com.rozikovp.blog.services.service.PostService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class MockedTestContext {

    @Bean
    public PostService postService() {
        return Mockito.mock(PostService.class);
    }

    @Bean
    public CommentService commentService() {
        return Mockito.mock(CommentService.class);
    }

    @Bean
    public CommentRepository commentRepository () {
        return Mockito.mock(CommentRepository.class);
    }

    @Bean
    public PostRepository postRepository() {
        return Mockito.mock(PostRepository.class);
    }
}
