package com.example.shop.Services;

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
}
