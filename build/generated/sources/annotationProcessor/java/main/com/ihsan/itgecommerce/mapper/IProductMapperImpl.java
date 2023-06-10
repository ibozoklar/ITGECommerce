package com.ihsan.itgecommerce.mapper;

import com.ihsan.itgecommerce.dto.request.CreateNewProductRequestDto;
import com.ihsan.itgecommerce.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-10T11:32:13+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IProductMapperImpl implements IProductMapper {

    @Override
    public Product toProduct(CreateNewProductRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.title( dto.getTitle() );
        product.price( dto.getPrice() );
        product.brand( dto.getBrand() );

        return product.build();
    }
}
