package com.example.shop.Services;

import com.example.shop.Entity.Cart;
import com.example.shop.Entity.Products;
import com.example.shop.Entity.User;
import com.example.shop.Repository.CartRepository;
import com.example.shop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProdService prodService;

    @Autowired
    ProductRepository productRepository;


    public void addToCart(Long productId, int quantity) {
        Products product = prodService.getProductById(productId);
        if (product != null) {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    public List<Cart> getCartItems() {
        return cartRepository.findAll();
    }
    public void deleteAll() {
        cartRepository.deleteAll();
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
