package com.example.shop.Services;

import com.example.shop.Entity.Products;
import com.example.shop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdService {

    @Autowired
    private ProductRepository productRepository;



    public void saveAllProd(Products pr, MultipartFile image) throws IOException {
        byte [] images = image.getBytes();
        pr.setImage(images);
        pr.setImageType(image.getContentType());


        productRepository.save(pr)    ;
    }

    public List<Products> findAllPro(){

        return productRepository.findAll();
    }


    public Products getProdByid(Long id){
        return productRepository.findById(id).orElse(null);

    }

    public void deleteByProdId(Long id){
        productRepository.deleteById(id);
    }


    public Products getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void updateProduct(Long id, Products updatedProduct) {
        Products existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
//        existingProduct.setImage(updatedProduct.getImage());
        productRepository.save(existingProduct);
    }


}
