package com.vyshnav.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        name = "Category",
        description = "It holds category information along with their products"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {


    private Long id;
    private String name;
    private List<ProductDTO> products;
}
