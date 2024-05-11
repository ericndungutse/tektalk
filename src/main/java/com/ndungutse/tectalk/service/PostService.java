package com.ndungutse.tectalk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ndungutse.tectalk.dto.PostDto;
import com.ndungutse.tectalk.dto.PostsResponse;
import com.ndungutse.tectalk.exception.ResourceNotFoundException;
import com.ndungutse.tectalk.model.Post;
import com.ndungutse.tectalk.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    // Get All Posts
    public PostsResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDIr) {

        Sort sort = sortDIr.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Page Content object
        Page<Post> posts = repository.findAll(pageable);

        // Get Content from page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> listOfPostsDto = listOfPosts.stream().map(post -> mapToDto(post)).toList();

        PostsResponse postsResponse = new PostsResponse();

        postsResponse.setPosts(listOfPostsDto);
        postsResponse.setPageNo(posts.getNumber());
        postsResponse.setPageSize(posts.getSize());
        postsResponse.setTotalElements(posts.getTotalElements());
        postsResponse.setTotalPages(posts.getTotalPages());
        postsResponse.setLast(posts.isLast());

        return postsResponse;

    }

    // Create Post
    public PostDto createPost(PostDto postDto) {
        // Convert DTO to Post
        Post post = mapToPost(postDto);

        // Persist into the db
        Post newPost = repository.save(post);

        // Convert Post to DTO
        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }

    // Get Post By Id
    public PostDto getPostById(Long id) {
        // Get Post from db
        Post post = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        // Map to DTO
        PostDto postDto = mapToDto(post);

        return postDto;
    }

    // Update Post
    public PostDto updatePost(PostDto postDto, Long id) {
        // Get Post from db
        Post post = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDesciption());
        post.setContent(postDto.getContent());

        Post updated_post = repository.save(post);

        // Convert to dto

        return mapToDto(updated_post);
    }

    // Delete Post
    public void deletePostById(Long id) {
        // Get Post from db
        Post post = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        repository.delete(post);
    }

    // Mappers
    private Post mapToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDesciption());
        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());

        return postDto;

    }

}
