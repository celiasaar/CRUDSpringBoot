package com.example.demo_DSA.controller.API;

import com.example.demo_DSA.error.CategoryNotFoundException;
import com.example.demo_DSA.error.ProductNotFoundException;
import com.example.demo_DSA.model.Category;
import com.example.demo_DSA.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/category")
public class CategoryAPIController {

    private final CategoryRepository categoryRepo;

    @Autowired
    public CategoryAPIController(CategoryRepository categoryRepo) {

        this.categoryRepo = categoryRepo;
    }

    @GetMapping()
    private ResponseEntity<?> getAllCategories(){
        List<Category> response = categoryRepo.findAll();

        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();

        }else{
            return ResponseEntity.ok(response);
        }
    };

    @GetMapping("/{id}")
    private Category getById(@PathVariable Long id){

        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PostMapping
    private ResponseEntity<?> createCategory(@RequestBody Category newCategory){
        Category category = new Category();
        category.setId(newCategory.getId());
        category.setName(newCategory.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepo.save(category));
    }

    @PutMapping("/{id}")
    private Category updateProduct(@PathVariable Long id, @RequestBody Category updated){

        return categoryRepo.findById(id).map(c -> {
            c.setName(updated.getName());
            return categoryRepo.save(c);
        }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        categoryRepo.delete(category);
        return ResponseEntity.noContent().build();
    }


}
