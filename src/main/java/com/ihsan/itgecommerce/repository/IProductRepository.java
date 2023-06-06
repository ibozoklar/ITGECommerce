package com.ihsan.itgecommerce.repository;

import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.enums.ProductState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {



    List<Product> findAllByProductState(ProductState state);
}
