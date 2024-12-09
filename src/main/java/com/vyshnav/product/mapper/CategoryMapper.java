package com.vyshnav.product.mapper;

import com.vyshnav.product.dto.CategoryDTO;
import com.vyshnav.product.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryMapper {

// we use mapper for entity and dto to map


    // first this line of code going to be run here we are receiving the data from user in the form of dto it going to covert
    //  into entity then create entity  object and setting the value in entity then mapper return then entity will save in database
    // the value then it going to covert to dto the below line works

    // we made static here so we can call this method mapper.method name
    //dto to entity
        public static Category toCategoryEntity(CategoryDTO categoryDTO){
            Category category = new Category();
            category.setName(categoryDTO.getName());
            return category;
        }

    // dto can be any form mapper is to map in correct form
    //entity to category
    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProducts(category.getProducts().stream()
                .map(ProductMapper::toProductDTO).toList());//:: double reference converting entity to dto

        return categoryDTO;

    }

}
