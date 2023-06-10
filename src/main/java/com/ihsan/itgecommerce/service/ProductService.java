package com.ihsan.itgecommerce.service;

import com.ihsan.itgecommerce.dto.request.CreateNewProductRequestDto;
import com.ihsan.itgecommerce.entity.Product;
import com.ihsan.itgecommerce.entity.enums.ProductState;
import com.ihsan.itgecommerce.mapper.IProductMapper;
import com.ihsan.itgecommerce.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final IProductRepository productRepository;

    public boolean saveProduct(CreateNewProductRequestDto dto){

        Product product = IProductMapper.INSTANCE.toProduct(dto);
        productRepository.save(product);
        return true;
    }

    public boolean saveProduct(Product product){

        productRepository.save(product);
        return true;
    }
    public List<Product> findAllProducts() {
        return productRepository.findAllByProductState(ProductState.AVAILABLE);
    }
}
