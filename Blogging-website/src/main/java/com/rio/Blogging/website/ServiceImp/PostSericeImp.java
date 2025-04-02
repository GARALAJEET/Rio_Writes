package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.Post;
import com.rio.Blogging.website.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class PostSericeImp implements postService {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ResponseEntity<?> createPost(PostDto postDto) {
        // Implementation here
        return null;
    }

    @Override
    public ResponseEntity<?> getPost(Long id) {
        // Implementation here
        return null;
    }

    @Override
    public ResponseEntity<?> getPostsByUser(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> getPostsByCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePost(Long id, PostDto postDto) {
        // Implementation here
        return null;
    }

    @Override
    public ResponseEntity<?> deletePost(Long id) {
        // Implementation here
        return null;
    }

    @Override
    public ResponseEntity<?> getAllPosts() {
        // Implementation here
        return null;
    }
    public  PostDto postToDTo(Post post){

        PostDto postDto=modelMapper.map(post,PostDto.class);
        return postDto;
    }
    public Post DtoToPost(PostDto postDto){
        Post post=modelMapper.map(postDto,Post.class);
        return post;
    }
}
