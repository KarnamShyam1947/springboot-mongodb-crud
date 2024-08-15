package com.shyam.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shyam.documents.ProductDocument;
import com.shyam.dto.ProductDTO;
import com.shyam.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final SequenceGeneratorService sequenceGenerator;
    private final ProductRepository productRepository;

    public ProductDocument addProduct(ProductDTO dto) {
        ProductDocument product = new ProductDocument();
        BeanUtils.copyProperties(dto, product);

        product.setId(sequenceGenerator.getSequenceNumber(ProductDocument.SEQUENCE_NAME));

        return productRepository.save(product);
    }

    public List<ProductDocument> allProducts() {
        return productRepository.findAll();
    }
    
    public ProductDocument getProduct(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
   
    public void deleteProduct(int id) {
        ProductDocument product = getProduct(id);
        productRepository.delete(product);
    }

    public ProductDocument updateProduct(int id, ProductDTO dto) {
        ProductDocument product = getProduct(id);
        BeanUtils.copyProperties(dto, product);

        if (dto.getDescription() != null) 
            product.setDescription(dto.getDescription());

        if (dto.getName() != null)
            product.setName(dto.getName());
        
        if (dto.getPrice() != 0.0) 
            product.setPrice(dto.getPrice());
        
        return productRepository.save(product);
    }

}
