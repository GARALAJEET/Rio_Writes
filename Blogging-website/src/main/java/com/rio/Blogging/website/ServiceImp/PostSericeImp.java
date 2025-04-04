package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.Category;
import com.rio.Blogging.website.Modal.Post;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.Response.postResponse;
import com.rio.Blogging.website.repo.categoryRepo;
import com.rio.Blogging.website.repo.postRepo;
import com.rio.Blogging.website.repo.userRepo;
import com.rio.Blogging.website.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Post post = DtoToPost(postDto);


        Optional<Category> category = c.findById(categoryId);
        if (category.isEmpty()) {
            return new ResponseEntity<>("Category not Found", HttpStatus.NOT_FOUND);
        }


        Optional<User> user = userRepo.findById(Math.toIntExact(userId));
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
        }

        post.setCategory(category.get());
        post.setUser(user.get());
        post.setAddedDate(LocalDateTime.now());
        Post savedPost = postRepo.save(post);
        if (savedPost != null) {
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
    public ResponseEntity<?> getPostsByUser( Long userId,Long pageSize,Long pageNumber) {
        postResponse postResponseDto = new postResponse();
        Pageable pageable = PageRequest.of(Math.toIntExact(pageNumber), Math.toIntExact(pageSize));
        Optional<User> userOpt = userRepo.findById(Math.toIntExact(userId));
        List<Post> posts = postRepo.findByUser(userOpt.get(), pageable);
        if(!posts.isEmpty()){
            List<PostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = postToDTo(post);
                postDtos.add(postDto);
            }
            return new ResponseEntity<>(postDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Posts Found for this User", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getPostsByCategory(Long categoryId) {
        Optional<Category>category=c.findById(categoryId);
        if(!category.isPresent()){
            return new ResponseEntity<>("Category not Found",HttpStatus.NOT_FOUND);
        }
        List<Post> posts = postRepo.findByCategory(category.get());
        if (posts!=null) {
            List<PostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = postToDTo(post);
                postDtos.add(postDto);
            }
            return new ResponseEntity<>(postDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Posts Found for this Category", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> DeleteAllPost() {
        List<Post> posts = postRepo.findAll();
        if (posts != null) {
            postRepo.deleteAll(posts);
            return new ResponseEntity<>("All Posts Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Posts Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getPostByTitle(String title) {
        List<Post>posts=postRepo.findAll();

        if(posts!=null){
            List<PostDto>postDtos=new ArrayList<>();
            for(int i=0;i<posts.size();i++){
                PostDto postDto=postToDTo(posts.get(i));
                String currentTitle=postDto.getTitle().toLowerCase().replace(" ","");
                String Title=title.toLowerCase().replace(" ","");
                if(currentTitle.contains(Title)){
                    postDtos.add(postDto);
                }
            }
            return new ResponseEntity<>(postDtos,HttpStatus.OK);

        }
        return new ResponseEntity<>("No Posts Found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getPostByKeyword(String keyword) {
        List<Post>posts=postRepo.findAll();

        if(posts!=null){
            List<PostDto>postDtos=new ArrayList<>();
            for(int i=0;i<posts.size();i++){
                PostDto postDto=postToDTo(posts.get(i));
                String currentContent=postDto.getContent().toLowerCase().replace(" ","");
                String Keyword=keyword.toLowerCase().replace(" ","");
                if(currentContent.contains(Keyword)){
                    postDtos.add(postDto);
                }
            }
            return new ResponseEntity<>(postDtos,HttpStatus.OK);

        }
        return new ResponseEntity<>("No Posts Found",HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<?> updatePost(Long id, PostDto postDto) {
        Optional<Post> postOpt = postRepo.findById(id);

        if(postOpt.isPresent()){
            Post existingPost = postOpt.get();
            if(postDto.getTitle() != null){
                existingPost.setTitle(postDto.getTitle());
            }
            if(postDto.getContent() != null){
                existingPost.setContent(postDto.getContent());
            }
            if(postDto.getImageUrl() != null){
                existingPost.setImageUrl(postDto.getImageUrl());
            }
            existingPost.setAddedDate(LocalDateTime.now());
            Post updatedPost = postRepo.save(existingPost);
            if(updatedPost != null){
                return new ResponseEntity<>("Post Updated", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Post not Found", HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<?> deletePost(Long id) {
        Optional<Post> post = postRepo.findById(id);
        if (post.isPresent()) {
            postRepo.delete(post.get());
            return new ResponseEntity<>("Post Deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("Post not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllPosts(Long pageSize, Long pageNumber, String sortBy) {
        List<String>validEnter= Arrays.asList("id","title","content","acceding","decending");
        postResponse postResponseDto=new postResponse();
        if(!validEnter.contains(sortBy)){
            return new ResponseEntity<>("Invalid Sort By",HttpStatus.BAD_REQUEST);
        }
        Pageable pageable = PageRequest.of(Math.toIntExact(pageNumber), Math.toIntExact(pageSize));
        List<Post> posts = postRepo.findAll(pageable).getContent();
        if(!posts.isEmpty()){
            List<PostDto>postDtos=new ArrayList<>();
           for(Post post:posts){
               PostDto postDto=postToDTo(post);
               postDtos.add(postDto);
           }
           postResponseDto.setContent(postDtos);
              postResponseDto.setPageNumber(Math.toIntExact(pageNumber));
                postResponseDto.setPageSize(Math.toIntExact(pageSize));
                postResponseDto.setTotalElements(postRepo.findAll(pageable).getTotalElements());
                postResponseDto.setTotalPages(postRepo.findAll(pageable).getTotalPages());
                postResponseDto.setLastPage(postRepo.findAll(pageable).isLast());
                postResponseDto.setFirstPage(postRepo.findAll(pageable).isFirst());
            return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Posts Found",HttpStatus.NOT_FOUND);
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
