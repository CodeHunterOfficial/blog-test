package com.rozikovp.blog.domain.repository;


import com.rozikovp.blog.domain.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
