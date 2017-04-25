package com.rozikovp.blog.webapp;


import com.rozikovp.blog.services.dao.CommentDao;
import com.rozikovp.blog.services.dao.NewCommentDao;
import com.rozikovp.blog.services.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ParvizR on 20/04/2017.
 *
 * @author ParvizR
 */
@Controller
@RequestMapping("/post/{id}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Long addCommentForPost(@RequestBody NewCommentDao newCommentDao) {
        return commentService.addComment(newCommentDao);
    }


    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDao> getCommentForPost(@PathVariable Long id) {
        return commentService.getCommentsForPost(id);
    }

}
