package com.dummy.vessel.service;

import com.dummy.vessel.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDas {

    List<Category> getAllCategories();

    void addCategory(Category category);

    void deleteCategoryById(int id);

    Optional<Category> getCategoryById(int id);
}
