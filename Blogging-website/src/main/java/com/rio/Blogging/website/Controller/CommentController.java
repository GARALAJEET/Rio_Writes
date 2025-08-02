package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.CommentDto;
import com.rio.Blogging.website.ServiceImp.CommentServiceImp;
import com.rio.Blogging.website.ServiceImp.UserserviceImp;
import com.rio.Blogging.website.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentServiceImp commentServiceImp;
    private final UserserviceImp userserviceImp ;
    private final JWTService jwtService;


    @PostMapping("/addComment/{PostId}")
    public ResponseEntity<?>addCommnets(@PathVariable("PostId") Long PostId, @RequestBody CommentDto commentdto){
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long userId= (Long) userserviceImp.getId(username).getBody();
        return commentServiceImp.AddComment(userId, PostId, commentdto);
    }
    @GetMapping("/getAllComment/{PostId}")
    public ResponseEntity<?>DisplayAllComments(@PathVariable("PostId") Long PostId,
                                               @RequestParam(value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                               @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Long pageNumber){
        return commentServiceImp.DisplayAllComments(PostId,pageSize, pageNumber );
    }
    @DeleteMapping("/deleteAllCommentByPost/{PostId}")
    public ResponseEntity<?>DeteleAllComments(@PathVariable("PostId") Long PostId){
        return commentServiceImp.DeleteAllCommentsByPost(PostId);
    }
    @DeleteMapping("/deleteAllCommentByCommentID/{commentId}")
    public ResponseEntity<?>DeleteAllCommentsByCommentId(@PathVariable("commentId") Long commentId){
        return commentServiceImp.DeleteAllCommentsByCommentId(commentId);
    }
    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId,@RequestBody CommentDto commentDto){
        return commentServiceImp.updateCommentBYID(commentId,commentDto);
    }

}
