package com.ihsan.itgecommerce.controller;


import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PutMapping("/addItemToBasket/{userid}/{productid}")
    public ResponseEntity<Boolean> addItemToBasket(@PathVariable Long userid, @PathVariable Long productid){
        return ResponseEntity.ok(basketService.addProductToBasket(userid, productid));
    }

    @PutMapping("/removeItemFromBasket/{userid}/{productid}")
    public ResponseEntity<Boolean> removeItemFromBasket(@PathVariable Long userid, @PathVariable Long productid){
        return ResponseEntity.ok(basketService.removeProductFromBasket(userid, productid));

    }

    @GetMapping("/listProducts/{basketid}")
    public ResponseEntity<List<Product>> listProducts(@PathVariable Long basketid){
        return ResponseEntity.ok(basketService.listProducts(basketid));
    }

    @GetMapping("/listProductsFromUserBasket/{userid}")
    public ResponseEntity<List<Product>> listProductsFromUser(@PathVariable Long userid){
        return ResponseEntity.ok(basketService.listProductsFromUserBasket(userid));
    }

    @PutMapping("/purchaseProductsInUserBasket/{userid}")
    public ResponseEntity<Boolean> purchaseProductsInUserBasket(@PathVariable Long userid){
        return ResponseEntity.ok(basketService.purchaseProductsInUserBasket(userid));
    }

    @PutMapping("/purchaseProducts/{basketid}")
    public ResponseEntity<Boolean> purchaseProducts(@PathVariable Long basketid){
        return ResponseEntity.ok(basketService.purchaseProducts(basketid));
    }


}
