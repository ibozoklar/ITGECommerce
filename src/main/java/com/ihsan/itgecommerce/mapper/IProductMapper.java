package com.ihsan.itgecommerce.mapper;


import com.ihsan.itgecommerce.dto.request.CreateNewProductRequestDto;
import com.ihsan.itgecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {

    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product toProduct(final CreateNewProductRequestDto dto);

}
