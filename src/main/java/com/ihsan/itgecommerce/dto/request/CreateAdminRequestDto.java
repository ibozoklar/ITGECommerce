package com.ihsan.itgecommerce.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
