package com.ihsan.itgecommerce.repository;

import com.ihsan.itgecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBasketRepository extends JpaRepository<Basket, Long> {

}
