package com.vyshnav.product.controller;

import com.vyshnav.product.dto.CategoryDTO;
import com.vyshnav.product.exception.CategoryAlreadyExistsException;
import com.vyshnav.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API CRUD operation",
        description = "CREATE, READ, UPDATE and DELETE operations for Category REST API"
)

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // in our old project we were storing directly into entity here for protection of data we send data to dto

    // inside response entity we made ? generic so it takes anything has return because in try we returing categoryDTO
    // and in catch we returing string default we need to return CategoryDTO but if exception occured need to return message
    // so generic

    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )

    @Operation(
            summary = "Create category by categoryId",
            description = "REST API to create category by categoryId."
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);


//        try{// we have done this in gobal exception handler rather creating for every ome try and catch
//            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
//        } catch (CategoryAlreadyExistsException ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//        }
        //return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }



    //get Mapping
    @Operation(
            summary = "Fetch all categories",
            description = "REST API to fetch all categories along with their products."
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }



    //get category by id
    @Operation(
            summary = "Fetch all categories by categoryId",
            description = "REST API to fetch all categories by CategoryId."
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }



    //delete category
    @Operation(
            summary = "Delete all categories",
            description = "REST API to Delete all categories."
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }


}
