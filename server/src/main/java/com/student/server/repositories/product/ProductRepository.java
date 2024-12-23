package com.student.server.repositories.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.model.tables.records.ProductRecord;
import com.student.server.config.exception.RestApiException;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.mappers.ProductMapper;
import com.student.server.data.request.ModifyProductRequest;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import com.student.server.repositories.AbstractCRUDRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.server.model.tables.Product.PRODUCT;

@Repository
public class ProductRepository extends AbstractCRUDRepository<ProductRecord, ProductDTO> {

    public ProductRepository(DSLContext dsl) {
        super(dsl, PRODUCT, PRODUCT.ID, ProductDTO.class);
    }

    public Single<List<ProductResponse>> getProducts(ProductRequest request) {
        return Single.fromCallable(() -> dsl.selectFrom(PRODUCT)
                .where(PRODUCT.IS_DELETED.eq(false))
                .limit(request.getSize())
                .offset(request.getPage() * request.getSize())
                .fetch(ProductMapper.INSTANCE::productRecordToResponse)
        );
    }

    public Single<List<ProductResponse>> getProducts() {
        return Single.fromCallable(() -> dsl.selectFrom(PRODUCT)
                .where(PRODUCT.IS_DELETED.eq(false))
                .fetch(ProductMapper.INSTANCE::productRecordToResponse)
        );
    }

    public ProductDTO getProductById(String id) {
        ProductDTO dto = super.getById(id);
        if (dto == null) {
            throw new RestApiException("Product Not Found");
        }
        if (dto.getIsDeleted().equals(true)) {
            throw new RestApiException("Product Not Found");
        }
        return dto;
    }

    public ProductDTO deleteProductById(String id) {
        ProductDTO dto = super.getById(id);
        if (dto == null) {
            throw new RestApiException("Product Not Found");
        }
        dto.setIsDeleted(true);
        dsl.update(PRODUCT)
                .set(PRODUCT.IS_DELETED, true)
                .where(PRODUCT.ID.eq(id));
        return dto;
    }

    public Single<ProductResponse> updateProductById(String id, ModifyProductRequest request) {
        return Single.fromCallable(() -> {
            ProductDTO dto = super.getById(id);
            if (dto == null) {
                throw new RestApiException("Product Not Found");
            }
            String jsonString = new ObjectMapper().writeValueAsString(request.content());
            int rowChange = dsl.update(PRODUCT)
                    .set(PRODUCT.TITLE, request.title())
                    .set(PRODUCT.COVER_IMAGE, request.coverImage())
                    .set(PRODUCT.FOOTER_IMAGE, request.footerImage())
                    .set(PRODUCT.CONTENT, JSON.valueOf(jsonString))
                    .set(PRODUCT.UPDATED_AT, LocalDateTime.now())
                    .where(PRODUCT.ID.eq(id))
                    .execute();
            if (rowChange != 1) {
                throw new RestApiException("Update Product Failed");
            }
            return ProductMapper.INSTANCE.productDTOToResponse(super.getById(id));
        });
    }

}
