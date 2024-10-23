package com.microservice.product_service.Services;

import com.microservice.product_service.Models.Product;
import com.microservice.product_service.Models.ProductAttributeValues;
import com.microservice.product_service.Models.ProductVariantValues;
import com.microservice.product_service.Repositories.ProductAttributeValuesRepository;
import com.microservice.product_service.Repositories.ProductVariantValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductAttributeVariantService {
    private final ProductAttributeValuesRepository productAttributeValuesRepository;
    private final ProductVariantValuesRepository productVariantValuesRepository;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private VariantService variantService;

    @Autowired
    public ProductAttributeVariantService(ProductAttributeValuesRepository productAttributeValuesRepository, ProductVariantValuesRepository productVariantValuesRepository) {
        this.productAttributeValuesRepository = productAttributeValuesRepository;
        this.productVariantValuesRepository = productVariantValuesRepository;
    }

    public void addValues(Product product, List<Long> ids, String instance) {
        if (!ids.isEmpty() && ids.get(0) != 0) {
                switch (instance) {
                    case "attributes":
                        ids.forEach((id) -> {
                        productAttributeValuesRepository.save(
                                ProductAttributeValues.builder().attribute(attributeService.getFullAttribute(id))
                                        .product(product).build()
                        );});
                        break;
                    case "variants":
                        ids.forEach((id) -> {
                        productVariantValuesRepository.save(
                                ProductVariantValues.builder().variantValue(variantService.getFullVariant(id))
                                        .product(product).build()
                        );});
                        break;
                }

        }
    }
}
