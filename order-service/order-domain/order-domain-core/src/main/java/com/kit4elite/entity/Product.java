package com.kit4elite.entity;

import com.kit4elite.valueObject.Money;
import com.kit4elite.valueObject.ProductId;

import java.util.UUID;

public class Product extends BaseEntity<ProductId> {
    private String  name;
    private Money price;
    private boolean available ;

    public Product(ProductId productId) {
        super.setId(productId);
    }
    public Product(String name, Money price,boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
