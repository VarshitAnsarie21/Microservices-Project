package com.microservice.product.service;

import com.microservice.product.dto.ProductDTO;
import com.microservice.product.model.Product;
import com.microservice.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        Product newProduct = productRepository.findById(product.getProductId()).orElse(new Product());
        if(newProduct.getProductId() != null) {
            throw new RuntimeException("User already exists");
        }
        newProduct.setProductName(product.getProductName());
        newProduct.setProductDescription(product.getProductDescription());
        newProduct.setProductPrice(product.getProductPrice());

        return productRepository.save(newProduct);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product existingPrduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not exists"));

        if (productDTO.getProductName() != null) {
            existingPrduct.setProductName(productDTO.getProductName());
        }
        if (productDTO.getProductDescription() != null) {
            existingPrduct.setProductDescription(productDTO.getProductDescription());
        }


        return productRepository.save(existingPrduct);
    }
}

