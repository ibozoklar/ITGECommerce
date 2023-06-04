package com.ihsan.itgecommerce.controller;




import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/findAllProducts")
    public ResponseEntity<List<Product>> findAllProducts(){

        return ResponseEntity.ok(productService.findAllProducts());
    }
}
