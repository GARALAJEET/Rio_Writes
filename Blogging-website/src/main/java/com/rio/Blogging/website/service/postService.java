package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
import org.springframework.http.ResponseEntity;

public interface postService {
    public ResponseEntity<?> createPost(PostDto postDto);
    public ResponseEntity<?> updatePost(Long id, PostDto postDto);
    public ResponseEntity<?> deletePost(Long id);
    public ResponseEntity<?> getAllPosts();
    public ResponseEntity<?> getPost(Long id);
    public ResponseEntity<?> getPostsByUser(UserDto userDto);
    public ResponseEntity<?>getPostsByCategory(CategoryDto categoryDto);
}
