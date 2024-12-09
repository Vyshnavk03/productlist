package com.vyshnav.product.mapper;

import com.vyshnav.product.dto.ProductDTO;
import com.vyshnav.product.entity.Category;
import com.vyshnav.product.entity.Product;

public class ProductMapper {

    //DTO to Entity
    public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        return product;
    }

    //entity to DTO
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );
    }


}
