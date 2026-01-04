package com.example.blogs.services;

import com.example.blogs.domain.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listCategories();

    Category createCategory(Category category);

}
