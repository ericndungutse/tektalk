package com.ndungutse.tectalk.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndungutse.tectalk.dto.PostDto;
import com.ndungutse.tectalk.model.Post;
import com.ndungutse.tectalk.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public PostDto createPost(PostDto postDto) {
        Post newPost = new Post(postDto.getId(), postDto.getTitle(), postDto.getContent(), postDto.getCreatedAt());
        repository.save(newPost);
        return postDto;
    }
}
