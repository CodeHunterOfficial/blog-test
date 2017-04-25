package com.rozikovp.blog.services.service;


import com.rozikovp.blog.domain.model.Comment;
import com.rozikovp.blog.domain.repository.CommentRepository;
import com.rozikovp.blog.services.dao.CommentDao;
import com.rozikovp.blog.services.dao.NewCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ParvizR on 20/04/2017.
 *
 * @author ParvizR
 */

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentDao> getCommentsForPost(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        if (comments != null || !comments.isEmpty()) {
            return comments.stream()
                    .map(comment -> new CommentDao(comment.getId(), comment.getContent(), comment.getAuthor(), comment.getCreationDate()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Long addComment(NewCommentDao newCommentDao) {
        Comment comment = new Comment();

        comment.setAuthor(newCommentDao.getAuthor());
        comment.setContent(newCommentDao.getContent());
        comment.setPost(newCommentDao.getPostId());
        return commentRepository.save(comment).getId();
    }
}
