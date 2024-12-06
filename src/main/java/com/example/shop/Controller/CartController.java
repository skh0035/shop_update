package com.example.shop.Controller;

import com.example.shop.Configuration.UserDetailsConfiguration;
import com.example.shop.Entity.Cart;
import com.example.shop.Entity.Products;
import com.example.shop.Entity.User;
import com.example.shop.Services.CartService;
import com.example.shop.Services.ProdService;
import com.example.shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class CartController extends GlobalController{
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    ProdService prodService;

    @GetMapping("{userId}/cart")
    public String viewCart(@AuthenticationPrincipal UserDetailsConfiguration userDetails,Model model) {
        model.addAttribute("userId",userDetails.getUserId());
        model.addAttribute("cart", cartService.getCartItems());
        return "Cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam int quantity, @AuthenticationPrincipal UserDetailsConfiguration userDetails, Model model) {
        Long userId = userDetails.getUserId();
        model.addAttribute("userId",userId);
        cartService.addToCart(productId, quantity, userId);
        return "redirect:/user/{userId}/cart";
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
