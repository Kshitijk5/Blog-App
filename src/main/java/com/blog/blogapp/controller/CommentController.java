package com.blog.blogapp.controller;

import com.blog.blogapp.Dto.CommentDto;
import com.blog.blogapp.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts/{postId}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable("postId") long postId) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<Set<CommentDto>> getAllCommentForAPost(@PathVariable("postId") long postId) {
        return new ResponseEntity<>(commentService.getCommentsForAPost(postId), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getACommentForAPost(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId) {

        return new ResponseEntity<>(commentService.getACommentForAPost(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateACommentForAPost(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        return new ResponseEntity<>(commentService.updateACommentForAPost(postId, commentId, commentDto), HttpStatus.OK);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteAComment(@PathVariable("postId") long postId,
                                                 @PathVariable("commentId") long commentId) {
        return new ResponseEntity<>(commentService.deleteAComment(postId, commentId), HttpStatus.OK);
    }
}
