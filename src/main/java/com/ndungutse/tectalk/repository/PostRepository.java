package com.ndungutse.tectalk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndungutse.tectalk.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

}
