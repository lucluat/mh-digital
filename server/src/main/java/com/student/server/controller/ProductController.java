package com.student.server.controller;

import com.student.server.data.constant.MappingConstant;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import com.student.server.data.response.ResponseObject;
import com.student.server.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MappingConstant.PRODUCT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseObject<List<ProductResponse>> getStudents(ProductRequest request) {
        return new ResponseObject<>(productService.getProducts(request), HttpStatus.OK, "Get products successfully");
    }

}
