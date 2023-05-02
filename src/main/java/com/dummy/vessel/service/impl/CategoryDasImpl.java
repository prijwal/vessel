package com.dummy.vessel.service.impl;

import com.dummy.vessel.repository.CategoryDao;
import com.dummy.vessel.entities.Category;
import com.dummy.vessel.service.CategoryDas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryDasImpl implements CategoryDas {

    @Autowired
    CategoryDao categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}
