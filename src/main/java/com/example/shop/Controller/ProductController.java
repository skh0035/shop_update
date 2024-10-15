package com.example.shop.Controller;



import com.example.shop.Entity.Products;
import com.example.shop.Repository.ShopRepository;
import com.example.shop.Services.ShopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("/product")
public class ProductController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopServices shopServices;


    @GetMapping("/AddProducts")
    public String addProducts(Model model) {
        model.addAttribute("product", new Products());
        return "product_add";
    }

    @PostMapping("/SAP")
    public String addProduct(@ModelAttribute("product") Products product, Model model/*,@RequestParam("file") MultipartFile file*/) {
        shopServices.saveProds(product);
        //   shopServices.saveImage(file);
        return "redirect:AddProducts";
    }
    @GetMapping("/admin")
    public String showProducts( Model model) {
        List<Products> products = shopServices.getAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }



}

