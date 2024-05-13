package com.ndungutse.tectalk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndungutse.tectalk.dto.CommentDto;
import com.ndungutse.tectalk.exception.ResourceNotFoundException;
import com.ndungutse.tectalk.model.Comment;
import com.ndungutse.tectalk.model.Post;
import com.ndungutse.tectalk.repository.CommentRepository;
import com.ndungutse.tectalk.repository.PostRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;

    // Get all comments
    public List<CommentDto> getComments(long postId) {
        // Find Post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Get all comments
        List<Comment> comments = repository.getCommentsByPostId(post.getId());

        // return comments.stream().map(this::mapToDto).collect(Collectors.toList());
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    // Create a comment
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToComment(commentDto);

        // Check if Post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Set post to comment entity
        comment.setPost(post);

        System.out.println("**********************");
        System.out.println(comment.getPost());
        System.out.println("**********************");

        // Comment entity to DB
        Comment newComment = repository.save(comment);

        // Return comment dto
        return mapToDto(newComment);
    }

    // Map Comment to CommentDto
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;

    }

    // Map COmmentDto to Comment
    private Comment mapToComment(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());

        return comment;

    }

}
