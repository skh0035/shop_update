package com.example.shop.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "category_name")
    private String c_name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Products> products;

    //shodiyooooor
    //aaaaaaaa
    //nooooooooo
    // fkdjhbgfnfbngenmgfk;htm
}
