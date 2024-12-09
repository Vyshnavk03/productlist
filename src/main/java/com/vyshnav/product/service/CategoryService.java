package com.vyshnav.product.service;

import com.vyshnav.product.dto.CategoryDTO;
import com.vyshnav.product.entity.Category;
import com.vyshnav.product.exception.CategoryAlreadyExistsException;
import com.vyshnav.product.mapper.CategoryMapper;
import com.vyshnav.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    // create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){

        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException(" Category "+ categoryDTO.getName() +" already exists!");
        }

        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);//while saving automatically id will be generated
        return CategoryMapper.toCategoryDTO(category);
    }

    //get all categories
    public List<CategoryDTO> getAllCategories(){//map transform entity to DTO
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }


    //get category by id
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
        return  CategoryMapper.toCategoryDTO(category);
    }

    //delete category

    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "category"+ id +"has been deleted!";
    }


}
