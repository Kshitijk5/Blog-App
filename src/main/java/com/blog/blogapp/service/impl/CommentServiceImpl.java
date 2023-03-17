package com.blog.blogapp.service.impl;

import com.blog.blogapp.Dto.CommentDto;
import com.blog.blogapp.entities.Comment;
import com.blog.blogapp.entities.Post;
import com.blog.blogapp.exception.BlogAPIException;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repository.CommentRepository;
import com.blog.blogapp.repository.PostRepository;
import com.blog.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId + ""));

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        comment = commentRepository.save(comment);

        return mapToDto(comment);
    }

    @Override
    public Set<CommentDto> getCommentsForAPost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId + ""));

        Set<Comment> comments = post.getComments();
        Set<CommentDto> commentDto = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toSet());

        return commentDto;
    }

    @Override
    public CommentDto getACommentForAPost(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId + ""));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId + ""));

        if (comment.getPost().getId() == post.getId()) {
            return mapToDto(comment);
        } else {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post");
        }


    }

    @Override
    public CommentDto updateACommentForAPost(long postId, long commentId, CommentDto commentDto) {
      /*  CommentDto commentDtoTemp = getACommentForAPost(postId,commentId);
        Comment comment = mapToEntity(commentDtoTemp);

        //setting new values
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        //update the comment
        comment = commentRepository.save()
        */
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId + ""));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId + ""));

        if (comment.getPost().getId() == post.getId()) {
            //setting new values
            comment.setBody(commentDto.getBody());
            comment.setName(commentDto.getName());
            comment.setEmail(commentDto.getEmail());

            //update the comment
            comment = commentRepository.save(comment);
            return mapToDto(comment);

        } else {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post");
        }
    }

    @Override
    public String deleteAComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId + ""));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId + ""));

        if (comment.getPost().getId() == post.getId()) {
            commentRepository.delete(comment);
            return String.format("Comment with ID- %s belonging to post with ID- %s deleted", commentId, postId);
        } else {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post");
        }
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto,Comment.class);
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());

//        comment.setPost(commentDto.get);
        return comment;
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setId(comment.getId());

        return commentDto;
    }
}
