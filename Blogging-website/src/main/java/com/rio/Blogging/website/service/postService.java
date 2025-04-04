package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
import org.springframework.http.ResponseEntity;

public interface postService {
    public ResponseEntity<?> createPost(PostDto postDto, Long userId, Long categoryId);
    public ResponseEntity<?> updatePost(Long id, PostDto postDto);
    public ResponseEntity<?> deletePost(Long id);
    public ResponseEntity<?> getAllPosts(Long pageSize, Long pageNumber, String sortBy);
    public ResponseEntity<?> getPostBYID(Long id);
    public ResponseEntity<?> getPostsByUser(Long userId,Long pageSize,Long pageNumber);
    public ResponseEntity<?>getPostsByCategory(Long categoryId);
    public ResponseEntity<?> DeleteAllPost();
    public ResponseEntity<?> getPostByTitle(String keyword);
    public ResponseEntity<?>getPostByKeyword(String keyword);
}
