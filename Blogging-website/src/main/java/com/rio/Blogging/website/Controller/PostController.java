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

    private PostSericeImp postService;
    @Autowired
    public PostController(PostSericeImp postService){
        this.postService=postService;
    }
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
            , @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Long pageNumber,
                                        @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy){
        return postService.getAllPosts(pageSize, pageNumber, sortBy);
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
    public ResponseEntity<?> getPostsByUser(@PathVariable Long userId,
                                            @RequestParam (value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                            @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Long pageNumber){
        return postService.getPostsByUser(userId, pageSize, pageNumber);
    }
    @GetMapping("/getPostsByCategory/{categoryId}")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Long categoryId,
                                                @RequestParam (value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                                @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Long pageNumber){
        return postService.getPostsByCategory(categoryId,pageSize,pageNumber);
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
    @DeleteMapping("/deletePostByUserId/{userId}")
    public ResponseEntity<?> deletePostByUserId(@PathVariable Long userId) {
        return postService.deletePostByUserId(userId);
    }


}
