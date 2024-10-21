package com.example.shop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prod_name")
    private String name;

    @Column(name = "prod_price")
    private double price;

    @Column(name = "prod_quant")
    private int quantity;

    @Column(name = "category")
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Category> categories;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
    @Column(name = "image_type")
    private String imageType;
}
