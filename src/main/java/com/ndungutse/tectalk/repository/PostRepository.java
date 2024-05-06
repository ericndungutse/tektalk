package com.ndungutse.tectalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndungutse.tectalk.model.Post;

// @Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
