package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.Category;
import com.rio.Blogging.website.Modal.Post;
import com.rio.Blogging.website.Modal.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postRepo extends JpaRepository<Post, Long> {

    Page<Post> findByCategory(Category category,Pageable pageable);
    Page<Post> findByUser(User user, Pageable pageable);
}
