package com.example.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private String image;

    @Transient
    private MultipartFile file;

    private int quantity;

    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    private Category category;
}