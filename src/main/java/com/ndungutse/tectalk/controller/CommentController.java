package com.ndungutse.tectalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndungutse.tectalk.dto.CommentDto;
import com.ndungutse.tectalk.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/")
public class CommentController {
    @Autowired
    CommentService service;

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable long postId) {
        List<CommentDto> commentsDto = service.getComments(postId);
        return new ResponseEntity<>(commentsDto, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable long postId,
            @Valid @RequestBody CommentDto commentDto) {

        System.out.println("*********" + "commentDto");

        CommentDto response = service.createComment(postId, commentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId) {
        CommentDto commentDto = service.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // Update Comment
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByIDEntityd(
            @PathVariable long postId,
            @PathVariable long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        CommentDto updaCommentDto = service.updateComment(postId, commentId, commentDto);

        return new ResponseEntity<>(updaCommentDto, HttpStatus.OK);
    }

    // Delete Comment
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable long postId, @PathVariable long commentId) {
        service.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }

}
