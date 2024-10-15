package com.example.shop.Repository;


import com.example.shop.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdRepo extends JpaRepository<Products, Long> {
    Products UpdateByProdId(Long id);
    Products DeleteByProdId(Long id);
}
