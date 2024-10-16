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

}
