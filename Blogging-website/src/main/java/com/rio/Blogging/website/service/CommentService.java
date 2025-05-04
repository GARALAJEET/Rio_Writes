package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.CommentDto;
import com.rio.Blogging.website.Modal.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    public ResponseEntity<?>AddComment(Long postId, Long userId, CommentDto commentDto);
    public ResponseEntity<?>DisplayAllComments(Long postId, Long pageSize, Long pageNumber);
    public ResponseEntity<?>DeleteAllCommentsByPost(Long postId);
    public ResponseEntity<?>DeleteAllCommentsByCommentId(Long commentId);
    public ResponseEntity<?>updateCommentBYID(Long commentId,CommentDto commentDto);
}
