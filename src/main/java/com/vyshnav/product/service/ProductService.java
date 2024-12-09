package com.vyshnav.product.service;

import com.vyshnav.product.dto.ProductDTO;
import com.vyshnav.product.entity.Category;
import com.vyshnav.product.entity.Product;
import com.vyshnav.product.exception.CategoryNotFoundException;
import com.vyshnav.product.mapper.ProductMapper;
import com.vyshnav.product.repository.CategoryRepository;
import com.vyshnav.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO crateProduct(ProductDTO productDTO){

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new CategoryNotFoundException("Category id: "+ productDTO.getCategoryId() +" not found!"));

        //DTO ->entity

        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);

        //Entity ->DTO

        return ProductMapper.toProductDTO(product);

            }

            // get all Products
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    //get Products by id
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
    }

    //Update Product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product  product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        product .setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return  ProductMapper.toProductDTO(product);
    }

    // delete product

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product "+ id +" has been deleted!";
    }
}
