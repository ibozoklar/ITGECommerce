package com.ihsan.itgecommerce.controller;

import com.ihsan.itgecommerce.dto.request.CreateAdminRequestDto;
import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/findAllPurchasedProducts/{userid}")
    public ResponseEntity<List<Product>> findAllPurchasedProducts(@PathVariable Long userid){
        return ResponseEntity.ok(userService.findAllPurchasedProducts(userid));
    }


}
