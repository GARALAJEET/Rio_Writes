package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.CategoryDto;
import com.rio.Blogging.website.Modal.Category;
import com.rio.Blogging.website.repo.categoryRepo;
import com.rio.Blogging.website.service.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategorySeriveImp  implements categoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private categoryRepo categoryRepo;
    @Override
    public ResponseEntity<?> createCategory(CategoryDto categoryDto) {
        Category category=dtoToCategory(categoryDto);
        Category cat=categoryRepo.save(category);
        if(cat!=null){
          return new ResponseEntity<>("Category Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Category not Created",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getCategory(Long id) {
        Optional<Category>cat=categoryRepo.findById(id);
        if(cat.isPresent()){
            Category cat1=cat.get();
            CategoryDto categoryDto=categoryTdto(cat1);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not Found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category>category=categoryRepo.findById(id);
        if(category.isPresent()){
            Category cat=category.get();
            cat.setCategorytitle(categoryDto.getCategorytitle());
            cat.setCategotydescription(categoryDto.getCategotydescription());
            Category cat1= categoryRepo.save(cat);
            if(cat1!=null){
                return new ResponseEntity<>("Category Updated",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Category not Found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        Optional<Category>category=categoryRepo.findById(id);
        if(category.isPresent()){
            categoryRepo.deleteById(id);
            return new ResponseEntity<>("Category Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not Found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        List<Category> categoryList=categoryRepo.findAll();
        if(categoryList!=null){
            List<CategoryDto> categoryDtoList=new ArrayList<>();
            for(Category category:categoryList) {
                CategoryDto categoryDto = categoryTdto(category);
                categoryDtoList.add(categoryDto);
            }
            return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not Found",HttpStatus.NOT_FOUND);
    }
    public CategoryDto categoryTdto(Category category){
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
    public Category dtoToCategory(CategoryDto categoryDto){
        Category category=modelMapper.map(categoryDto,Category.class);
        return category;
    }
}
