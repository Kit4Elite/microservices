package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.attribute.AttributeValuesRequest;
import com.microservice.product_service.Dtos.variant.VariantValuesRequest;
import com.microservice.product_service.Dtos.variant.VariantValuesResponse;
import com.microservice.product_service.Models.Attribute;
import com.microservice.product_service.Models.AttributeValues;
import com.microservice.product_service.Models.Variant;
import com.microservice.product_service.Models.VariantValues;
import com.microservice.product_service.Repositories.VariantValuesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class VariantValuesService {
    private final VariantValuesRepository variantValuesRepository;

    public VariantValuesService(VariantValuesRepository variantValuesRepository) {
        this.variantValuesRepository = variantValuesRepository;
    }

    public Boolean addValues(Set<VariantValuesRequest> variantValuesRequests, Variant variant) {
        if (variant != null) {
            variantValuesRequests.forEach(valuesValue ->
                    variantValuesRepository.save(VariantValues.builder()
                            .value(valuesValue.getValue())
                            .price(valuesValue.getPrice())
                            .variant(variant)
                            .build())
            );
            return true;
        }
        return false;
    }

    public void updateValues(Set<VariantValuesResponse> variantValues, Variant variant) {
        if (variant != null) {
            variantValues.forEach(variantValue -> {
                Optional<VariantValues> variantValuesTemp = variant.getVariantValues().stream().
                        filter(attr -> Objects.equals(variantValue.getVariantValueId(), attr.getVariantValueId()))
                        .findFirst();
                if (variantValuesTemp.isPresent()) {
                    variantValuesTemp.get().setValue(variantValue.getValue());
                    variantValuesTemp.get().setPrice(variantValue.getPrice());
                    variantValuesRepository.save(variantValuesTemp.get());
                } else {
                    variantValuesRepository.save(VariantValues.builder()
                            .value(variantValue.getValue())
                            .price(variantValue.getPrice())
                            .variant(variant).build());
                }
            });
        }
        ;
    }
}
