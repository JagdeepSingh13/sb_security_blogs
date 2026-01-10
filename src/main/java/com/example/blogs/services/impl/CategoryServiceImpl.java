package com.example.blogs.services.impl;

import com.example.blogs.domain.entities.Category;
import com.example.blogs.repos.CategoryRepo;
import com.example.blogs.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

//    can only delete categories which do not have posts in them
    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()) {
            if(!category.get().getPosts().isEmpty()) {
                throw new IllegalStateException("category has posts");
            }
            categoryRepo.deleteById(id);
        }
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("category not found"));
    }

}
