package com.example.shop.Services;

import com.example.shop.Entity.Cart;
import com.example.shop.Entity.Order;
import com.example.shop.Entity.Products;
import com.example.shop.Entity.User;
import com.example.shop.Repository.CartRepository;
import com.example.shop.Repository.OrderRepository;
import com.example.shop.Repository.ProductRepository;
import com.example.shop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProdService prodService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;


    public void addToCart(Long productId, int quantity, Long userId) {
        Products product = prodService.getProductById(productId);
        User user = userRepository.findUserById(userId);
        if (product != null) {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cart.setUser(user);
            cartRepository.save(cart);
        }
    }
    public List<Cart> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
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

    public void orderCartItems() {
        List<Cart> cartItems = getCartItems();
        List<Order> orders = new ArrayList<>();

        for (Cart cartItem : cartItems) {
            Products product = cartItem.getProduct();
            if (product != null) {
                Order order = new Order();
                order.setProduct(product);
             /*   order.setQuantity(cartItem.getQuantity());
                order.setTotalPrice(product.getPrice() * cartItem.getQuantity());
                order.setOrderDate(LocalDateTime.now());*/
                order.setCart(cartItem); // Optional: Link the order to the cart
                orders.add(order);
            }
        }

        // Save all orders
        orderRepository.saveAll(orders);

        // Clear the cart
        deleteAll();
    }
}
