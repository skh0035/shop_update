package com.example.shop.Controller;

import com.example.shop.Entity.Category;
import com.example.shop.Entity.Products;
import com.example.shop.Services.ClientService;
import com.example.shop.Services.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
   /* @GetMapping("/{categoryId}")
    public String  getProductsByCategoryId(Model model, @PathVariable Long categoryId){
       List<Products> products = clientService.getProductsByCategory(categoryId);
       model.addAttribute("products", products);
        return "/products_men";
    }*/
    @GetMapping("/single_product/{id}")
    public String prodDetails(@PathVariable Long id, Model model){
        Products product = clientService.getProductById(id);
        model.addAttribute("single_prod", product);
        return "single-product";
    }

    @GetMapping("/product_men/{categoryId}")
    public String ProductMen(@PathVariable Long categoryId, Model model){
        List<Products>  products = clientService.getProductsByCategory(categoryId);
        model.addAttribute("product_men", products);
        return "products_men";
    }
    @GetMapping("/product_women/{categoryId}")
    public String ProductWoman(@PathVariable Long categoryId, Model model){
        List<Products>  products = clientService.getProductsByCategory(categoryId);
        model.addAttribute("product_woman", products);
        return "products_women";
    }
    @GetMapping("/product_kids/{categoryId}")
    public String ProductKids(@PathVariable Long categoryId, Model model){
        List<Products>  products = clientService.getProductsByCategory(categoryId);
        model.addAttribute("product_kids", products);
        return "products_kids";
    }
}
