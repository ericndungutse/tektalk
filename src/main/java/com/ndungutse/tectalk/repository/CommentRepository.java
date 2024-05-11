package com.ndungutse.tectalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndungutse.tectalk.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
