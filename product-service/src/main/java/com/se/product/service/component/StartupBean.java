package com.se.product.service.component;

import com.se.product.service.domain.Category;
import com.se.product.service.domain.Product;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StartupBean implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private  final ProductRepository productRepository;

    public StartupBean(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        categoryRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();

        Category category1= new Category();
        category1.setName("category1");
        category1.setCode("1111");


        Category category2= new Category();
        category2.setName("category2");
        category2.setCode("2222");


        Category category3= new Category();
        category3.setName("category3");
        category3.setCode("3333");

        categoryRepository.saveAll(Arrays.asList(category1,category2));


        Product product = new Product();
        product.setName("product name 1");
        product.getCategories().add(category1);
        product.getCategories().add(category2);

        productRepository.save(product);

        product.addCategory(category3);
        productRepository.save(product);

        List<Product> products = productRepository.findAll();

        products.stream().forEach(System.out::println);

    }
}
