package com.rio.Blogging.website.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.ServiceImp.PostSericeImp;
import com.rio.Blogging.website.ServiceImp.UserserviceImp;
import com.rio.Blogging.website.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostSericeImp postService;
    private final UserserviceImp userserviceImp ;
    private final JWTService jwtService;

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<?> createNewPost(
            @RequestBody PostDto postDto,
            @PathVariable("categoryId") Long categoryId
    ) {
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long userId= (Long) userserviceImp.getId(username).getBody();
        return postService.createPost(postDto, userId, categoryId);
    }
    @GetMapping("/get/{PostId}")
    public ResponseEntity<?> getPostById(@PathVariable("PostId") Long id) {
//        System.out.println( postService.getPostBYID(id));
        System.out.println(id);
        return postService.getPostBYID(id);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPost(@RequestParam(value = "pageSize",defaultValue = "5",required = false) Long pageSize
            , @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Long pageNumber,
                                        @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy){
        return postService.getAllPosts(pageSize, pageNumber, sortBy);
    }
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
        return postService.updatePost(id, postDto);
    }
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }
    @GetMapping("/getPostsByUser")
    public ResponseEntity<?> getPostsByUser(@RequestParam (value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                            @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Long pageNumber){
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long userId= (Long) userserviceImp.getId(username).getBody();
        return postService.getPostsByUser(userId, pageSize, pageNumber);
    }
    @GetMapping("/getPostsByCategory/{categoryId}")
    public ResponseEntity<?> getPostsByCategory(@PathVariable("categoryId") Long categoryId,
                                                @RequestParam (value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                                @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Long pageNumber){
        return postService.getPostsByCategory(categoryId,pageSize,pageNumber);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllPosts() {
        return postService.DeleteAllPost();
    }
    @GetMapping("/getPostByTitle/{title}")
    public ResponseEntity<?> getPostByTitle(@PathVariable("title") String title) {
        return postService.getPostByTitle(title);
    }
    @GetMapping("/getPostByKeyword/{keyword}")
    public ResponseEntity<?> getPostByKeyword(@PathVariable("keyword") String keyword) {
        return postService.getPostByKeyword(keyword);
    }
    @DeleteMapping("/deletePostByUserId")
    public ResponseEntity<?> deletePostByUserId() {
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long userId= (Long) userserviceImp.getId(username).getBody();
        return postService.deletePostByUserId(userId);
    }


}
