package com.example.demo_DSA.converter;

import com.example.demo_DSA.DTO.ProductDTO;
import com.example.demo_DSA.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOConverter {

    private final ModelMapper modelMapper;


    public ProductDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);

    }
}
