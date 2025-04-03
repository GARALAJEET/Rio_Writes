package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.Modal.Category;
import org.springframework.http.ResponseEntity;

public interface categoryService {
    public ResponseEntity<?> createCategory(CategoryDto categoryDto);
    public ResponseEntity<?> getCategory(Long id);
    public ResponseEntity<?> updateCategory(Long id, CategoryDto categoryDto);
    public ResponseEntity<?> deleteCategory(Long id);
    public ResponseEntity<?> getAllCategories();


}
