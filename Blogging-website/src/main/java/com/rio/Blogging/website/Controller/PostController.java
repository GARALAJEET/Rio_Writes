package com.rio.Blogging.website.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.ServiceImp.PostSericeImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostSericeImp postService;
    @PostMapping("/create/{userId}/{categoryId}")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto, @PathVariable Long userId, @PathVariable Long categoryId) {
        return postService.createPost(postDto, userId, categoryId);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        System.out.println( postService.getPostBYID(id));
        return postService.getPostBYID(id);
    }
}
