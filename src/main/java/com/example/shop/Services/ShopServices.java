package com.example.shop.Services;

import com.example.shop.Entity.Products;
import com.example.shop.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

@Service
public class ShopServices {
    @Autowired
    private ShopRepository shopRepository;

/*    public void saveImage(MultipartFile file) throws IOException {
        Products image = new Products();
        image.setName(file.getOriginalFilename());
        image.setImage(file.getBytes());
        shopRepository.save(image);
    }

    public Products getImageById(Long id) {
        Optional<Products> image = shopRepository.findById(id);
        return image.orElse(null);
    }*/

    public void saveProds(Products products) {
        shopRepository.save(products);
    }
}
