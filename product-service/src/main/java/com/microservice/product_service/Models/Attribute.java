package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Attributes")
@Builder
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attributeId;
    private String attributeName ;
    private String attributeDescription ;
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AttributeValues> attributeValues= new HashSet<>();
}
