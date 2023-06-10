package com.ihsan.itgecommerce.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewProductRequestDto {

    private String title;

    private Double price;

    private String brand;

}
