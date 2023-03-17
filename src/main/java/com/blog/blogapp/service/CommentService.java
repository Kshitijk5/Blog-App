package com.blog.blogapp.service;

import com.blog.blogapp.Dto.CommentDto;
import com.blog.blogapp.entities.Comment;

import java.util.Set;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    Set<CommentDto> getCommentsForAPost(long postId);

    CommentDto getACommentForAPost(long postId, long commentId);

    CommentDto updateACommentForAPost(long postId, long commentId, CommentDto commentDto);

    String deleteAComment(long postId, long commentId);
}
