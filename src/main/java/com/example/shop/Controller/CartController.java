package com.example.shop.Controller;

import com.example.shop.Entity.Cart;
import com.example.shop.Entity.Products;
import com.example.shop.Entity.User;
import com.example.shop.Services.CartService;
import com.example.shop.Services.ProdService;
import com.example.shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    ProdService prodService;
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCartItems());
        return "Cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart/cart";
    }
    @GetMapping("/delete")
    public String deleteProduct() {
        cartService.deleteAll();
        return "redirect:/cart/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteByProdId(@PathVariable Long id){
        cartService.deleteById(id);
        return "redirect:/cart/cart";
    }


}
