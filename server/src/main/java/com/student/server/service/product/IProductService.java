package com.student.server.service.product;

import com.student.server.data.dto.ProductDTO;
import com.student.server.data.request.ModifyProductRequest;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface IProductService {

    Single<List<ProductResponse>> getProducts(ProductRequest request);

    Single<List<ProductResponse>> getProducts();

    Single<ProductDTO> detail(String id);

    Single<ProductDTO> delete(String id);

    Single<ProductResponse> updateProduct(String id, ModifyProductRequest request);

    Single<ProductDTO> addProduct(ModifyProductRequest request);

}
