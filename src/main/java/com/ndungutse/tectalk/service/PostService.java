package com.ndungutse.tectalk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndungutse.tectalk.model.Post;
import com.ndungutse.tectalk.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

}
