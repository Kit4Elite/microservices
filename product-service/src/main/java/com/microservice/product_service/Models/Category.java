package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName ;
    private String categoryDescription ;
    private Boolean isActive ;
    private String categoryicon ;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    private Date createdAt;
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
}
