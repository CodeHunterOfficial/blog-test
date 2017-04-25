package com.rozikovp.blog.domain.repository;



import com.rozikovp.blog.domain.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
