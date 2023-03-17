package com.blog.blogapp.repository;

import com.blog.blogapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Long> {
}
