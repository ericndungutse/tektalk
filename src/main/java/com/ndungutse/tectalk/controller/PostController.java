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

import com.ndungutse.tectalk.dto.PostDto;
import com.ndungutse.tectalk.service.PostService;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(service.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        System.out.println(postDto);
        PostDto postResponse = service.createPost(postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.getPostById(id), HttpStatus.OK);
    }

}
