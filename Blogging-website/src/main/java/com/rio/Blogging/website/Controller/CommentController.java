package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.CommentDto;
import com.rio.Blogging.website.ServiceImp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentServiceImp commentServiceImp;
    @Autowired
    public CommentController(CommentServiceImp commentServiceImp){
        this.commentServiceImp=commentServiceImp;
    }
    @PostMapping("/addComment/{userId}/{PostId}")
    public ResponseEntity<?>addCommnets(@PathVariable("userId") Long userId,
                                         @PathVariable("PostId") Long PostId,
                                         @RequestBody CommentDto commentdto){
        return commentServiceImp.AddComment(userId, PostId, commentdto);
    }
    @GetMapping("/getAllComment/{PostId}")
    public ResponseEntity<?>DisplayAllComments(@PathVariable Long PostId,
                                               @RequestParam(value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                               @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Long pageNumber){
        return commentServiceImp.DisplayAllComments(PostId,pageSize, pageNumber );
    }
    @DeleteMapping("/deleteAllCommentByPost/{PostId}")
    public ResponseEntity<?>DeteleAllComments(@PathVariable Long PostId){
        return commentServiceImp.DeleteAllCommentsByPost(PostId);
    }
    @DeleteMapping("/deleteAllCommentByCommentID/{commentId}")
    public ResponseEntity<?>DeleteAllCommentsByCommentId(@PathVariable Long commentId){
        return commentServiceImp.DeleteAllCommentsByCommentId(commentId);
    }
    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,@RequestBody CommentDto commentDto){
        return commentServiceImp.updateCommentBYID(commentId,commentDto);
    }

}
