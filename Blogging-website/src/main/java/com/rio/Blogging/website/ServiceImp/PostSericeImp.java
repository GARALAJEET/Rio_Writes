package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.Category;
import com.rio.Blogging.website.Modal.Post;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.repo.categoryRepo;
import com.rio.Blogging.website.repo.postRepo;
import com.rio.Blogging.website.repo.userRepo;
import com.rio.Blogging.website.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostSericeImp implements postService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private userRepo userRepo;
    @Autowired
    private postRepo postRepo;
    @Autowired
    private categoryRepo c;
    @Override
    public ResponseEntity<?> createPost(PostDto postDto, Long userId, Long categoryId) {
        Post post=DtoToPost(postDto);
        Optional<Category> category=c.findById(userId);
        if(category.isPresent()){
            post.setCategory(category.get());
            Optional<User> user=userRepo.findById(Math.toIntExact(userId));
            if(user.isPresent()){
                post.setUser(user.get());
            }
            else {
                return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>("Category not Found", HttpStatus.NOT_FOUND);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        post.setAddedDate(localDateTime);

        Post post1=postRepo.save(post);
        if(post1!=null){
            return new ResponseEntity<>("Post Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Post not Created", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getPostBYID(Long id) {
        Optional<Post> post = postRepo.findById(id);
        if (post.isPresent()) {
            Post post1 = post.get();
            PostDto postDto = postToDTo(post1);
            return ResponseEntity.ok(postDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not Found");
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
