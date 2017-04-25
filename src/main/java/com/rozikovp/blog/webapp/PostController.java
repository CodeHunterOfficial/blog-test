package com.rozikovp.blog.webapp;


import com.rozikovp.blog.services.dao.NewPostDao;
import com.rozikovp.blog.services.dao.PostDao;
import com.rozikovp.blog.services.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PostDao getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Long addPost(@RequestBody NewPostDao newPostDao) {
        return postService.addPost(newPostDao);
    }


}
