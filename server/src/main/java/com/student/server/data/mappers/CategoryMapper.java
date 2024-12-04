package com.student.server.data.mappers;

import com.server.model.tables.pojos.Category;
import com.student.server.data.dto.CategoryDTO;
import com.student.server.data.request.CategoryRequest;
import com.student.server.data.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CategoryMapper {
    Category toPOJO(CategoryDTO categoryDTO);
    CategoryDTO toDTO(Category category);
    CategoryResponse toResponse(Category category);
    CategoryRequest toRequest(Category category);
    Category toCategory(CategoryRequest categoryRequest);


}
