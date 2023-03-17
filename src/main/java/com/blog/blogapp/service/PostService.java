package com.blog.blogapp.service;

import com.blog.blogapp.Dto.PagePostResponse;
import com.blog.blogapp.Dto.PostDto;
import com.blog.blogapp.entities.Post;

import java.util.List;

public interface PostService {

    PagePostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto createPost(PostDto postDto);

    PostDto getbyId(long id);

    PostDto updatePost(PostDto postDto,long id);


    String deleteByID(long id);
}
