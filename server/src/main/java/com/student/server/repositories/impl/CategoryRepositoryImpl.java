package com.student.server.repositories.impl;


import com.server.model.Tables;
import com.server.model.tables.pojos.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository {


    private final DSLContext dslContext;


    public Optional<Category> update(Category category) {
        int affectedRows = dslContext.update(Tables.CATEGORY)
                .set(dslContext.newRecord(Tables.CATEGORY, category))
                .where(Tables.CATEGORY.ID.eq(category.getId()))
                .execute();
        if (affectedRows == 0) {
            return Optional.empty();
        }
        return Optional.of(Objects.requireNonNull(dslContext.selectFrom(Tables.CATEGORY)
                        .where(Tables.CATEGORY.ID.eq(category.getId()))
                        .fetchOne())
                .into(Category.class));


    }

    @Override
    public List<Category> findAllCategories() {
        return dslContext.selectFrom(Tables.CATEGORY)
                .fetch()
                .into(Category.class);
    }

    @Override
    public Category findCategoryById(String id) {
        return Objects.requireNonNull(dslContext.selectFrom(Tables.CATEGORY)
                        .where(Tables.CATEGORY.ID.eq(String.valueOf(id)))
                        .fetchOne())
                .into(Category.class);
    }

    @Override
    public Category saveCategory(Category category) {
        return Objects.requireNonNull(dslContext.insertInto(Tables.CATEGORY)
                        .set(dslContext.newRecord(Tables.CATEGORY, category))
                        .returning()
                        .fetchOne())
                .into(Category.class);
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(String id) {
        dslContext.deleteFrom(Tables.CATEGORY)
                .where(Tables.CATEGORY.ID.eq(String.valueOf(id)))
                .execute();
    }
}