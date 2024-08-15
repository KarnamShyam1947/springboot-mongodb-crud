package com.shyam.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.documents.ProductDocument;
import com.shyam.dto.ProductDTO;
import com.shyam.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDocument> addProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(productService.addProduct(dto));
    }
    
    @GetMapping
    public ResponseEntity<List<ProductDocument>> allProducts() {
        return ResponseEntity.ok().body(productService.allProducts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDocument> getProduct(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductDocument> updateProduct(
        @PathVariable("id") int id,
        @RequestBody ProductDTO dto
    ) {
        return ResponseEntity.ok().body(productService.updateProduct(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
