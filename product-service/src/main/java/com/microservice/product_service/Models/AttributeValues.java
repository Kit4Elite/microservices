package com.microservice.product_service.Models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AttributeValues")
@Builder
@Getter
@Setter
public class AttributeValues {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attributeValueId;
    @ManyToOne
    @JoinColumn(name = "attributeId")
    private Attribute attribute ;
    private String value ;
    private double additionalPrice;
}
