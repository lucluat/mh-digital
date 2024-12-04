package com.student.server.repositories.product;

import com.server.model.tables.records.ProductRecord;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.mappers.ProductMapper;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import com.student.server.repositories.AbstractCRUDRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.server.model.tables.Product.PRODUCT;

@Repository
public class ProductRepository extends AbstractCRUDRepository<ProductRecord, ProductDTO> {

    public ProductRepository(DSLContext dsl) {
        super(dsl,PRODUCT,PRODUCT.ID,ProductDTO.class);
    }

    public List<ProductResponse>getProducts(ProductRequest request){
        return dsl.selectFrom(PRODUCT)
                .limit(request.getSize())
                .offset(request.getPage() * request.getSize())
                .fetch(ProductMapper.INSTANCE::productRecordToResponse);
    }

    public List<ProductResponse>getProducts(){
        return dsl.selectFrom(PRODUCT)
                .fetch(ProductMapper.INSTANCE::productRecordToResponse);
    }

}
