package com.example.shop.Controller;

import com.example.shop.Configuration.UserDetailsConfiguration;
import com.example.shop.Entity.Products;

import com.example.shop.Entity.User;
import com.example.shop.Repository.UserRepository;
import com.example.shop.Services.ClientService;
import com.example.shop.Services.ProdService;
import com.example.shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ClientController{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProdService prodService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/{userId}/products")
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
    @GetMapping("/{userId}/single_product/{id}")
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

    @GetMapping("/register")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user){
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
     userRepository.save(user);
        return "redirect:/user/login?success=true";
    }

    @GetMapping("/getUser")
    public String getUser(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("user", users);
        return "user_show";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/loginCheck")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);


        return "redirect:/user/products";
    }



}
