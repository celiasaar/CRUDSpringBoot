package com.example.demo_DSA.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "com.example.demo_DSA")
public class MyCongif {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
