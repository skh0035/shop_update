package com.example.shop.Services;

import com.example.shop.Entity.Category;
import com.example.shop.Entity.Products;
import com.example.shop.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Products> GetProd(){
        return  clientRepository.findAll();
    }


   public List<Products> getProductsByCategory(Long categoryId){
        return clientRepository.findAllByCategoryId(categoryId);
   }
    public Products getProductById(Long id) {
        return clientRepository.findById(id)
                .orElse(null);
    }
}
