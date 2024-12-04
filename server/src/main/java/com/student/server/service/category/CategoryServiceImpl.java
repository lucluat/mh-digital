package com.student.server.service.category;

import com.server.model.tables.pojos.Category;
import com.student.server.data.dto.CategoryDTO;
import com.student.server.data.mappers.CategoryMapper;
import com.student.server.data.response.CategoryResponse;
import com.student.server.repositories.impl.CategoryRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllCategories()
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Single<CategoryDTO> findCategoryById(String categoryId) {
        return Single.fromCallable(() -> {
                    Category category = categoryRepository.findCategoryById(categoryId);
                    if (category == null) {
                        throw new RuntimeException("Category with ID " + categoryId + " not found");
                    }
                    return categoryMapper.toDTO(category);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .timeout(5, TimeUnit.SECONDS)
                .retry(3);
    }

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toPOJO(categoryDTO);
        category = categoryRepository.saveCategory(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional
    public Optional<CategoryDTO> updateCategory(String categoryId, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = Optional.ofNullable(categoryRepository.findCategoryById(categoryId));
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category with ID " + categoryId + " not found");
        }
        Category categoryToUpdate = categoryMapper.toPOJO(categoryDTO);
        categoryToUpdate.setId(String.valueOf(UUID.fromString(categoryId)));
        Category updatedCategoryOptional = categoryRepository.updateCategory(categoryToUpdate);
        if (updatedCategoryOptional == null) {
            throw new RuntimeException("Failed to update category with ID " + categoryId);
        }
        CategoryDTO updatedDTO = categoryMapper.toDTO(updatedCategoryOptional);
        return Optional.of(updatedDTO);
    }

    @Override
    @Transactional
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteCategory(categoryId);
    }
}
