package com.example.shop.Services;

import com.example.shop.Entity.Category;
import com.example.shop.Entity.Products;
import com.example.shop.Entity.Purchase;
import com.example.shop.Repository.ClientRepository;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

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
    public Purchase addProductsToPurchase(Long purchaseId, Set<Long> productIds) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow();
        Set<Products> products = productRepository.findAllById(productIds).stream().collect(Collectors.toSet());
        purchase.getProducts().addAll(products);
        return purchaseRepository.save(purchase);
    }
}
