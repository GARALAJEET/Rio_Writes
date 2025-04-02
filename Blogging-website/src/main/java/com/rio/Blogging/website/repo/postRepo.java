package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.Category;
import com.rio.Blogging.website.Modal.Post;
import com.rio.Blogging.website.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepo extends JpaRepository<Post, Long> {
    User findByUserId(Long userId);
    Category findByCategoryId(Long categoryId);
}
