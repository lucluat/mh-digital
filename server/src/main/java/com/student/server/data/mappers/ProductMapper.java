package com.student.server.data.mappers;

import com.server.model.tables.records.ProductRecord;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse productRecordToResponse(ProductRecord studentRecord);

    ProductResponse productDTOToResponse(ProductDTO studentRecord);

}
