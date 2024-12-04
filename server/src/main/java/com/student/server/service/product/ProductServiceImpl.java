package com.student.server.service.product;

import com.student.server.config.exception.RestApiException;
import com.student.server.data.dto.ProductDTO;
import com.student.server.data.mappers.ProductMapper;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import com.student.server.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getProducts(ProductRequest request) {
        return productRepository.getProducts(request);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public ProductResponse detail(String id) {
        ProductDTO productDTO = productRepository.getById(id);
        if(productDTO == null){
            throw new RestApiException("Product Not Found");
        }
        return ProductMapper.INSTANCE.productDTOToResponse(productDTO);
    }

}
