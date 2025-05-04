package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.CommentDto;
import com.rio.Blogging.website.Modal.Comment;
import com.rio.Blogging.website.Modal.Post;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.Response.commentResponse;
import com.rio.Blogging.website.repo.CommetRepo;
import com.rio.Blogging.website.repo.postRepo;
import com.rio.Blogging.website.repo.userRepo;
import com.rio.Blogging.website.service.CommentService;
import com.rio.Blogging.website.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public  class CommentServiceImp implements CommentService {
    @Autowired
    private postRepo postRepo;
    @Autowired
    private userRepo userRepo;
    @Autowired
    private CommetRepo commetRepo;
    @Autowired
    private ModelMapper modalMapper;
    public ResponseEntity<?> AddComment(Long userId,Long postId, CommentDto commentDto) {
        Optional<User>user=userRepo.findById(Math.toIntExact(userId));
        Optional<Post>posts=postRepo.findById((postId));
        String comment=commentDto.getContent();
        if(user.isEmpty()){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if(posts.isEmpty()){
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }
        Comment comment1=new Comment();
        comment1.setContent(comment);
        comment1.setPost(posts.get());
        comment1.setUser(user.get());
        comment1.setAddedDate(LocalDateTime.now());
        Comment comment2=commetRepo.save(comment1);
        if(comment2!=null){
            return new ResponseEntity<>("Comment added", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Comment not added", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> DisplayAllComments(Long postId, Long pageSize, Long pageNumber) {
        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty()){
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }
        Pageable pageable=PageRequest.of(Math.toIntExact(pageNumber),Math.toIntExact(pageSize));
        Page<Comment>comments=commetRepo.findByPost(post.get(),pageable);
        if (comments.isEmpty()){
            return new ResponseEntity<>("No comments found", HttpStatus.NOT_FOUND);
        }
        List<Comment> commentList=comments.getContent();
        List<CommentDto>commentDtoList=new ArrayList<>();
        for(Comment comment:commentList){
            CommentDto commentDto=dtoToComment(comment);
            commentDtoList.add(commentDto);
        }
        commentResponse response=new commentResponse();
        response.setPageNumber(Math.toIntExact(pageNumber));
        response.setFirstPage(comments.isFirst());
        response.setLastPage(comments.isLast());
        response.setTotalElements(comments.getTotalElements());
        response.setTotalElements(comments.getTotalElements());
        response.setPageSize(Math.toIntExact(pageSize));
        response.setContent(commentDtoList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?>DeleteAllCommentsByPost(Long postId){
        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty()){
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }
        Optional<Comment>comment=commetRepo.findByPost(post.get());
        if(comment.isEmpty()){
            return new ResponseEntity<>("No comments found", HttpStatus.NOT_FOUND);
        }
        commetRepo.delete(comment.get());
        return new ResponseEntity<>("All comments deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> DeleteAllCommentsByCommentId(Long commentId) {
        Optional<Comment> comment = commetRepo.findById(commentId);
        if(comment.isPresent()){
            commetRepo.delete(comment.get());
            return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> updateCommentBYID(Long commentId, CommentDto commentDto) {
        Optional<Comment>comment=commetRepo.findById(commentId);
       if(comment.isEmpty()){
           return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
       }
        Comment comment1=comment.get();
       if(commentDto.getContent()!=null){
              comment1.setContent(commentDto.getContent());
           }
       comment1.setAddedDate(LocalDateTime.now());
         Comment comment2=commetRepo.save(comment1);
        if(comment2!=null){
            return new ResponseEntity<>("Comment updated", HttpStatus.OK);}

        return new ResponseEntity<>("Comment not updated", HttpStatus.BAD_REQUEST);
    }

    public CommentDto dtoToComment(Comment comment){
        CommentDto commentDto=modalMapper.map(comment, CommentDto.class);
        return commentDto;
    }
    public Comment dtoToComment(CommentDto commentDto){
        Comment comment=modalMapper.map(commentDto, Comment.class);
        return comment;
    }
}
