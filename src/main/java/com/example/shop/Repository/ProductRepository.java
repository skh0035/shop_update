package com.example.shop.Repository;

import com.example.shop.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Products, Long> {
    List<Products> findAllByOrderByPriceAsc();
    List<Products> findAllByOrderByPriceDesc();
    List<Products> findAllByOrderByNameDesc();
    List<Products> findAllByOrderByNameAsc();

}
