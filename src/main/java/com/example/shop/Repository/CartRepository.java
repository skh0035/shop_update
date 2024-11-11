package com.example.shop.Repository;

import com.example.shop.Entity.Cart;
import com.example.shop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
