package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "Tags")
@Builder
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;
    private String tagName;
    private String tagIcon;
    private String tagType;

    @ManyToMany(mappedBy = "tags")
    private Set<Product> products = new HashSet<>();

    private Date createdAt;
    private Date updatedAt;
}
