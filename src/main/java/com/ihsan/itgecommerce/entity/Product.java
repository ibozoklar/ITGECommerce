package com.ihsan.itgecommerce.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreType;

import com.ihsan.itgecommerce.entity.enums.ProductState;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreType
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductState productState;

    private String title;

    private Double price;

    private String brand;

    private String image;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Basket basket;

}
