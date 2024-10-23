package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Galleries")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long galleryId;
    private String urlId;
    private int imageOrder;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Date createdAt;
    private Date updatedAt;
}
