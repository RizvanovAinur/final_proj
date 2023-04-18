package com.example.final_proj.services;

import com.example.final_proj.models.Category;
import com.example.final_proj.models.Product;
import com.example.final_proj.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //Получаем все продукты
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProduct(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }
    @Transactional
    public void saveProduct(Product product, Category category){
        product.setCategory(category);
        productRepository.save(product);
    }
    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

}
