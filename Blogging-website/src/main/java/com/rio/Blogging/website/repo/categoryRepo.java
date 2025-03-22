package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepo  extends JpaRepository<Category,Long> {
}
