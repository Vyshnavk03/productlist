package com.vyshnav.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
// cascade is used ex: when we delete the category, the child means the product under
// that category will also delete automatically if we did not use cascade and try to
// delete category directly internal error occur because of under that category products are present
// if not we need to delete child means products first then category to overcome those error while deleting
// directly the category we have written category

// fetch is used to fetch data from database

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}
