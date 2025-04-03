package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.Modal.Category;
import com.rio.Blogging.website.ServiceImp.CategorySeriveImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategorySeriveImp categorySeriveImp;
    @PostMapping("/createCategory")
    public ResponseEntity<?>createCategory( @Valid  @RequestBody CategoryDto categoryDto){
        return categorySeriveImp.createCategory(categoryDto);
    }
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<?>getCategory( @PathVariable Long id){
        return categorySeriveImp.getCategory(id);
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<?>getAllCategories(){
        return categorySeriveImp.getAllCategories();
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?>updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        if(categoryDto==null){
            return new ResponseEntity<>("Enter data to update", HttpStatus.BAD_REQUEST);
        }
        return categorySeriveImp.updateCategory(id, categoryDto);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Long id){
        return categorySeriveImp.deleteCategory(id);
    }

}
