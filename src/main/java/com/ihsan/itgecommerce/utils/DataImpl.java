package com.ihsan.itgecommerce.utils;

import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.Role;
import com.ihsan.itgecommerce.entity.UserEntity;
import com.ihsan.itgecommerce.entity.enums.ProductState;
import com.ihsan.itgecommerce.entity.enums.State;
import com.ihsan.itgecommerce.service.ProductService;
import com.ihsan.itgecommerce.service.RoleService;
import com.ihsan.itgecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataImpl {

    private final RoleService roleService;
    private final ProductService productService;

    private final UserService userService;

    @PostConstruct
    public void loadData() {
        createRoles();
        createProducts();
    }

    private void createRoles() {

        Role admin = Role.builder().role("ADMIN").build();
        Role user = Role.builder().role("USER").build();
        roleService.saveRole(admin);
        roleService.saveRole(user);

        Set<Role> adminlist = new HashSet<>();
        adminlist.add(admin);
        UserEntity admin1 = UserEntity.builder()
                .state(State.ACTIVE)
                .email("ihsancanbozoklar@hotmail.com")
                .password("1234")
                .firstName("Ihsan Can")
                .lastName("Bozoklar")
                .build();

        //admin1.getRoles().add(roleService.findByid(1L));
        userService.save(admin1);

        UserEntity user1 = UserEntity.builder().state(State.ACTIVE)
                .email("user1@hotmail.com")
                .password("1234")
                .firstName("UserEntity")
                .lastName("UserEntity")
                .build();
        userService.save(user1);
    }

    private void createProducts(){
        Product product1 = Product.builder().title("TAVAN LAMBASI BEYAZ DOKUNMATIK").brand("SANEL").price(288.26).productState(ProductState.AVAILABLE).build();
        Product product2 = Product.builder().title("START STOP ROLESI 12V").brand("ELO").price(208.84).productState(ProductState.AVAILABLE).build();
        Product product3 = Product.builder().title("ON FREN BALATASI").brand("BOSCH").price(755.34).productState(ProductState.AVAILABLE).build();
        Product product4 = Product.builder().title("ATESLEME BUJISI").brand("EYQUEM").price(197.55).productState(ProductState.AVAILABLE).build();
        Product product5 = Product.builder().title("HAVA FILTRE HORTUMU").brand("IBRAS").price(304.52).productState(ProductState.AVAILABLE).build();
        Product product6 = Product.builder().title("DEBRIYAJ SETI").brand("LUK").price(16338.91).productState(ProductState.AVAILABLE).build();
        Product product7 = Product.builder().title("FREN DISKI E30").brand("HELLA").price(675.91).productState(ProductState.AVAILABLE).build();
        Product product8 = Product.builder().title("KALIPER TUTUCU").brand("TRW").price(760.10).productState(ProductState.AVAILABLE).build();
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        productService.saveProduct(product4);
        productService.saveProduct(product5);
        productService.saveProduct(product6);
        productService.saveProduct(product7);
        productService.saveProduct(product8);
    }



}
