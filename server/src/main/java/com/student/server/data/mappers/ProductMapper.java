package com.student.server.data.mappers;

import com.server.model.tables.records.ProductRecord;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.response.ProductResponse;
import org.jooq.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//    @Mapping(source = "content", target = "content", qualifiedByName = "mapContent")
    ProductResponse productRecordToResponse(ProductRecord studentRecord);

//    @Mapping(source = "content", target = "content", qualifiedByName = "mapContent")
    ProductResponse productDTOToResponse(ProductDTO studentRecord);

//    @Named("mapContent")
    default String jsonToString(JSON json) {
        System.out.println(json.toString());
        return json.toString();
    }

}
