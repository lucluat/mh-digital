package com.student.server.service.category;


import com.student.server.data.dto.CategoryDTO;
import com.student.server.data.response.CategoryResponse;
import io.reactivex.rxjava3.core.Single;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    Single<CategoryDTO> findCategoryById(String categoryId);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    Optional<CategoryDTO> updateCategory(String categoryId, CategoryDTO categoryDTO);

    void deleteCategory(String categoryId);
}
