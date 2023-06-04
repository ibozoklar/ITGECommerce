package com.ihsan.itgecommerce.repository;

import com.ihsan.itgecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {



}
