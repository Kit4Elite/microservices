package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName ;
    private String SKU ;
    private Double regularPrice ;
    private Double discountPrice ;
    private String productDescription;
    private Boolean published ;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ProductAttributeValues> attributes = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ProductVariantValues> variants = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Gallery> gallery = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_tags", // The join table
            joinColumns = @JoinColumn(name = "productId"), // Foreign key for Product
            inverseJoinColumns = @JoinColumn(name = "tagId") // Foreign key for Tag
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_categories", // The join table
            joinColumns = @JoinColumn(name = "productId"), // Foreign key for Product
            inverseJoinColumns = @JoinColumn(name = "categoryId") // Foreign key for Tag
    )
    private Set<Category> categories  = new HashSet<>();

    private Date createdAt;
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
}
