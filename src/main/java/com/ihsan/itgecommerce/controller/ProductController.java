package com.ihsan.itgecommerce.controller;




import com.ihsan.itgecommerce.dto.request.CreateNewProductRequestDto;
import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createNewProduct")
    public ResponseEntity<Boolean> createNewProduct(@RequestBody CreateNewProductRequestDto dto){
        return ResponseEntity.ok(productService.saveProduct(dto));
    }
}
