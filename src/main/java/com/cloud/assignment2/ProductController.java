package com.cloud.assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/store-products")
    public ResponseEntity<ApiResponse> storeProducts(@RequestBody List<Product> products) {
        for (Product product : products) {
            productService.saveProduct(product);
        }
        ApiResponse response = new ApiResponse("Success.");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list-products")
    public ResponseEntity<ProductListResponse> listProducts() {
        List<Product> productList = productService.getAllProducts();
        ProductListResponse response = new ProductListResponse(productList);
        return ResponseEntity.ok(response);
    }

}
