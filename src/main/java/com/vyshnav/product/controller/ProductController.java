package com.vyshnav.product.controller;

import com.vyshnav.product.dto.ProductDTO;
import com.vyshnav.product.service.ProductService;
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
        name = "Product REST API CRUD operation",
        description = "CREATE, READ, UPDATE and DELETE operations for Product REST API"
)

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //create product
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )

    @Operation(
            summary = "Create products",
            description = "REST API to create product."
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ResponseEntity <ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createdProduct = productService.crateProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    //get all Products
    @Operation(
            summary = "Fetch all products",
            description = "REST API to fetch all products."
    )
    @GetMapping
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProducts();
    }


    //get Product By Id
    @Operation(
            summary = "Fetch all products by ProductId",
            description = "REST API to fetch all products by productId."
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    //update Product
    @Operation(
            summary = "Update product by productId",
            description = "REST API to update products by productId."
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }


    // delete Product
    @Operation(
            summary = "Delete product by productId",
            description = "REST API to delete product by productId."
    )
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

}
