package com.student.server.service.product;

import com.student.server.config.exception.RestApiException;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.mappers.ProductMapper;
import com.student.server.data.request.ModifyProductRequest;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import com.student.server.repositories.product.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public Single<List<ProductResponse>> getProducts(ProductRequest request) {
        return productRepository.getProducts(request);
    }

    @Override
    public Single<List<ProductResponse>> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Single<ProductDTO> detail(String id) {
        return Single.fromCallable(() -> productRepository.getProductById(id));

    }

    @Override
    public Single<ProductDTO> delete(String id) {
        return Single.fromCallable(() -> productRepository.deleteProductById(id));
    }

    @Override
    public Single<ProductResponse> updateProduct(String id, ModifyProductRequest request) {
        return productRepository.updateProductById(id, request);
    }

    @Override
    public Single<ProductDTO> addProduct(ModifyProductRequest request) {
        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(request);
        return productRepository.createWithSingle(productDTO);
    }

}
