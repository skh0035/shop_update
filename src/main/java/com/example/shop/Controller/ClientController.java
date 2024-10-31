package com.example.shop.Controller;

import com.example.shop.Entity.Products;
import com.example.shop.Services.ClientService;
import com.example.shop.Services.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProdService prodService;
    @GetMapping("/products")
    public String GetProds(Model model) {
        model.addAttribute("products", clientService.GetProd());
        return "/products";
    }
    @GetMapping("/products_men")
    public String  getProductsByCategory(@PathVariable Long categoryId, Model model) {
        model.addAttribute("categoryId", categoryId);
        prodService.getProductByCategoryId(categoryId);
        return "/products_men";
    }
}
