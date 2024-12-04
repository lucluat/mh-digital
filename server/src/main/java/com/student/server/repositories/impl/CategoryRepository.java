package com.student.server.repositories.impl;

import java.util.List;

import com.server.model.tables.pojos.Category;

public interface CategoryRepository {
    List<Category> findAllCategories();

    Category findCategoryById(String id);

    Category saveCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(String id);
}
