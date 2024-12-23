package com.student.server.controller;

import com.student.server.config.exception.RestApiException;
import com.student.server.data.constant.MappingConstant;
import com.student.server.data.mappers.ProductMapper;
import com.student.server.data.request.ModifyProductRequest;
import com.student.server.data.request.ProductRequest;
import com.student.server.data.response.ProductResponse;
import com.student.server.data.response.ResponseObject;
import com.student.server.service.product.IProductService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MappingConstant.PRODUCT)
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
@Validated
public class ProductController {

    private final IProductService productService;

    @GetMapping("/page")
    public Single<ResponseObject<List<ProductResponse>>> getStudents(ProductRequest request) {
        return productService.getProducts(request)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(res,
                        HttpStatus.OK,
                        "Get products successfully"))
                .doOnError(e -> log.error("[INFO] [PAGE-PRODUCT] {}", e.getMessage()));
    }

    @GetMapping
    public Single<ResponseObject<List<ProductResponse>>> getStudents() {
        return productService.getProducts()
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(res,
                        HttpStatus.OK,
                        "Get products successfully"))
                .doOnError(e -> log.error("[INFO] [LIST-PRODUCT] {}", e.getMessage()));
    }

    @GetMapping("/{id}")
    public Single<ResponseObject<ProductResponse>> detail(@PathVariable("id") String id) {
        return productService.detail(id)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(ProductMapper.INSTANCE.productDTOToResponse(res),
                        HttpStatus.OK,
                        "Get products successfully"))
                .doOnError(e -> log.error("[INFO] [DETAIL-PRODUCT] {}", e.getMessage()));
    }

    @PostMapping
    public Single<ResponseObject<ProductResponse>> addProduct(@RequestBody @Valid ModifyProductRequest productRequest) {
        return productService.addProduct(productRequest)
                .subscribeOn(Schedulers.io())
                .map(res -> {
                    log.info("[INFO] [ADD-PRODUCT] add product successfully");
                    return new ResponseObject<>(ProductMapper.INSTANCE.productDTOToResponse(res),
                            HttpStatus.OK,
                            "Get products successfully");
                })
                .doOnError(err -> {
                    log.error("[INFO] [ADD-PRODUCT] add product failed");
                    throw new RestApiException("add product failed");
                });
    }

    @DeleteMapping("/{id}")
    public Single<ResponseObject<ProductResponse>> deleteProduct(@PathVariable("id") String id) {
        return productService.delete(id)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(
                        ProductMapper.INSTANCE.productDTOToResponse(res),
                        HttpStatus.OK,
                        "Delete products successfully"))
                .doOnError(e -> log.error("[INFO] [DELETE-PRODUCT] {}", e.getMessage()));
    }

    @PutMapping("/{id}")
    public Single<ResponseObject<ProductResponse>> modifyProduct
            (
                    @RequestBody @Valid ModifyProductRequest request,
                    @PathVariable String id
            ) {
        return productService.updateProduct(id, request).subscribeOn(Schedulers.io())
                .map(res -> {
                    log.info("[INFO] [UPDATE-PRODUCT] update product successfully");
                    return new ResponseObject<>(res, HttpStatus.OK, "update product successfully");
                })
                .doOnError(err -> log.error("[INFO] [UPDATE-PRODUCT] update product failed"));
    }

}
