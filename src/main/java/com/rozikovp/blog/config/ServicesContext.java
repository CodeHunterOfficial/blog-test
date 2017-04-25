package com.rozikovp.blog.config;

import com.rozikovp.blog.services.service.CommentService;
import com.rozikovp.blog.services.service.CommentServiceImpl;
import com.rozikovp.blog.services.service.PostService;
import com.rozikovp.blog.services.service.PostServiceImpl;
import org.springframework.context.annotation.*;

@Configuration
//@Import(JpaConfiguration.class)
@ComponentScan("com.rozikovp.blog.services")
public class ServicesContext {

    @Bean
    public PostService postService() {
        return new PostServiceImpl();
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceImpl();
    }
}
