package com.microservice.product_service.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Variants")
@Builder
@Getter
@Setter
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variantId;
    private String variantName ;
    private String variantDescription ;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VariantValues> variantValues= new HashSet<>();


}
