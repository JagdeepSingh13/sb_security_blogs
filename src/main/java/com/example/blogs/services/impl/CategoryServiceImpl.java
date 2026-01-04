package com.example.blogs.services.impl;

import com.example.blogs.domain.entities.Category;
import com.example.blogs.repos.CategoryRepo;
import com.example.blogs.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> listCategories() {
        return categoryRepo.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if(categoryRepo.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("category already exists");
        }

        return categoryRepo.save(category);
    }

}
