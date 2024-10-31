package com.example.shop.Repository;

import com.example.shop.Entity.Category;
import com.example.shop.Entity.Client;
import com.example.shop.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByCategoryId(Long categoryId);
}
