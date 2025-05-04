package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.Comment;
import com.rio.Blogging.website.Modal.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommetRepo extends JpaRepository<Comment, Long> {

//   Page<Comment> findByPostId(Long postId, Pageable pageable);
   Page<Comment>findByPost(Post post, Pageable pageable);
   Optional<Comment> findByPost(Post post);
   void deleteByPost(Post post);
}
