package com.ndungutse.tectalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndungutse.tectalk.model.Post;
import com.ndungutse.tectalk.service.PostService;

@RestController
public class PostController {
    @Autowired
    private PostService service;

    @GetMapping("/api/v1/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        var posts = service.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
