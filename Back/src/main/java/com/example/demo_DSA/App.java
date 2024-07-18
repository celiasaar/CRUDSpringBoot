package com.example.demo_DSA;

import com.example.demo_DSA.model.Category;
import com.example.demo_DSA.model.Product;
import com.example.demo_DSA.repository.CategoryRepository;
import com.example.demo_DSA.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

	public static void main(String[] args) {


		ApplicationContext context =  SpringApplication.run(App.class, args);

		ProductRepository productRepository = context.getBean(ProductRepository.class);
		CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);



		Category category1 = new Category(null, "Comida");
		Category category2 = new Category(null, "Bebida");
		Category category3 = new Category(null, "Hogar");

		List<Category> categoryList = new ArrayList<>();
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);

		categoryRepository.saveAll(categoryList);



		Product product1 = new Product(null, "Jamón", 5.60, category1);
		Product product2 = new Product(null, "Sprite", 1.8, category2);
		Product product3 = new Product(null, "Sanitol", 3.99, category3);

		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);


		productRepository.saveAll(productList);


		}
	}


