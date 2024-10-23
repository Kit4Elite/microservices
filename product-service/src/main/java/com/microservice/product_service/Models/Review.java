package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter

@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    private Float value;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private Date createdAt;
    private Date updatedAt;
}
