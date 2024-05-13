package com.ndungutse.tectalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndungutse.tectalk.dto.CommentDto;
import com.ndungutse.tectalk.service.CommentService;

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
            @RequestBody CommentDto commentDto) {

        CommentDto response = service.createComment(postId, commentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
