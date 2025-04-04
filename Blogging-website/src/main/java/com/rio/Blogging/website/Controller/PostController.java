package com.rio.Blogging.website.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
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
    public ResponseEntity<?> createNewPost(@RequestBody PostDto postDto, @PathVariable Long userId, @PathVariable Long categoryId) {
        return postService.createPost(postDto, userId, categoryId);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        System.out.println( postService.getPostBYID(id));
        return postService.getPostBYID(id);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPost(@RequestParam(value = "pageSize",defaultValue = "5",required = false) Long pageSize
            , @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Long pageNumber) {
        return postService.getAllPosts(pageSize, pageNumber);
    }
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return postService.updatePost(id, postDto);
    }
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
    @GetMapping("/getPostsByUser/{userId}")
    public ResponseEntity<?> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }
    @GetMapping("/getPostsByCategory/{categoryId}")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Long categoryId) {
        return postService.getPostsByCategory(categoryId);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllPosts() {
        return postService.DeleteAllPost();
    }
    @GetMapping("/getPostByTitle/{title}")
    public ResponseEntity<?> getPostByTitle(@PathVariable String title) {
        return postService.getPostByTitle(title);
    }
    @GetMapping("/getPostByKeyword/{keyword}")
    public ResponseEntity<?> getPostByKeyword(@PathVariable String keyword) {
        return postService.getPostByKeyword(keyword);
    }


}
