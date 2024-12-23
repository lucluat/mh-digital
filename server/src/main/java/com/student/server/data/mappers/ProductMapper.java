package com.student.server.data.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.model.tables.records.ProductRecord;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.request.ModifyProductRequest;
import com.student.server.data.response.ProductResponse;
import org.jooq.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(source = "content", target = "content", qualifiedByName = "mapContent")
    ProductResponse productRecordToResponse(ProductRecord studentRecord);

    @Mapping(source = "content", target = "content", qualifiedByName = "mapContent")
    ProductResponse productDTOToResponse(ProductDTO studentRecord);

    @Named("mapContent")
    default List<ProductResponse.Content> jsonToContentList(JSON json) throws Exception {
        if (json == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(json.toString(), new TypeReference<List<ProductResponse.Content>>() {
        });
    }

    @Mapping(source = "content", target = "content", qualifiedByName = "mapToJson")
    ProductDTO toDTO(ModifyProductRequest request);

    @Named("mapToJson")
    default JSON toJson(List<ModifyProductRequest.Content> contentList) throws Exception {
        String jsonString = objectMapper.writeValueAsString(contentList);
        return JSON.valueOf(jsonString);
    }

    default void createdAt(@MappingTarget ProductDTO dto) {
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedAt(LocalDateTime.now());
        dto.setIsDeleted(false);
    }

}
