package com.ndungutse.tectalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndungutse.tectalk.dto.PostDto;
import com.ndungutse.tectalk.dto.PostsResponse;
import com.ndungutse.tectalk.service.PostService;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<PostsResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir) {

        return new ResponseEntity<>(service.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
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

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
            @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        service.deletePostById(id);

        return new ResponseEntity<>("Post Deleted Successfully~", HttpStatus.NO_CONTENT);
    }

}
