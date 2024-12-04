package com.student.server.service.product;

import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getProducts(ProductRequest request);

    List<ProductResponse> getProducts();

    ProductResponse detail(String id);
}
