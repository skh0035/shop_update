package com.example.shop.Controller;

import com.example.shop.Configuration.UserDetailsConfiguration;
import com.example.shop.Entity.Products;

import com.example.shop.Entity.User;
import com.example.shop.Repository.UserRepository;
import com.example.shop.Services.ClientService;
import com.example.shop.Services.ProdService;
import com.example.shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/{userId}")
public class ClientController extends GlobalController{

    @Autowired
    private ClientService clientService;
    @Autowired
    private ProdService prodService;
    @GetMapping("/products")
    public String GetProds(@AuthenticationPrincipal UserDetailsConfiguration userDetails,Model model) {
        model.addAttribute("userId", userDetails.getUserId());
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
    public String prodDetails(@PathVariable Long id, Model model,@AuthenticationPrincipal UserDetailsConfiguration userDetails){
        Long userId = userDetails.getUserId();
        Products product = clientService.getProductById(id);
        model.addAttribute("single_prod", product);
        model.addAttribute("userId", userId);
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


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable long id){
        Products product = prodService.getProductById(id);
        byte[] image = product.getImage();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(image);

    }


}
