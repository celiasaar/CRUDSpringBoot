package com.example.demo_DSA.controller.API;
import com.example.demo_DSA.DTO.CreateProductoDTO;
import com.example.demo_DSA.DTO.ProductDTO;
import com.example.demo_DSA.converter.ProductDTOConverter;
import com.example.demo_DSA.error.ProductNotFoundException;
import com.example.demo_DSA.model.Category;
import com.example.demo_DSA.model.Product;
import com.example.demo_DSA.repository.CategoryRepository;
import com.example.demo_DSA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/product")
public class ProductAPIController {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductDTOConverter productDTOConverter;

    @Autowired
    public ProductAPIController(ProductRepository productRepo, CategoryRepository categoryRepo, ProductDTOConverter productDTOConverter) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.productDTOConverter = productDTOConverter;
    }


    @GetMapping
    private ResponseEntity<?> getAllProducts(){
        List<Product> response = productRepo.findAll();

        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else{

            List<ProductDTO> dtoList = response.stream()
                    .map(productDTOConverter::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    };
    @GetMapping("/{id}")
    private Product getById(@PathVariable Long id){
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping
    private ResponseEntity<?> createProduct(@RequestBody CreateProductoDTO newProduct){
        Product product = new Product();
        product.setId(newProduct.getId());
        product.setName(newProduct.getName());
        Category category = categoryRepo.findById(newProduct.getCategoryId()).orElse(null);
        product.setCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepo.save(product));
    }

    @PutMapping("/{id}")
    private Product updateProduct(@PathVariable Long id, @RequestBody Product updated){

        return productRepo.findById(id).map(p -> {
            p.setName(updated.getName());
            p.setPrice(updated.getPrice());
            return productRepo.save(p);
        }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepo.delete(product);
        return ResponseEntity.noContent().build();
    }

}
