package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.attribute.AttributeValuesRequest;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Models.Attribute;
import com.microservice.product_service.Models.AttributeValues;
import com.microservice.product_service.Repositories.AttributeValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AttributeValuesService {
    private final AttributeValuesRepository attributeValuesRepository;

    @Autowired
    public AttributeValuesService(AttributeValuesRepository attributeValuesRepository) {
        this.attributeValuesRepository = attributeValuesRepository;
    }

    public Boolean addValues(Set<AttributeValuesRequest> attributeValuesRequests, Attribute attribute) {
        if (attribute != null) {
            attributeValuesRequests.forEach(attributeValue ->
                    attributeValuesRepository.save(AttributeValues.builder()
                            .value(attributeValue.getValue())
                            .additionalPrice(attributeValue.getAdditionalPrice())
                            .attribute(attribute)
                            .build())
            );
            return true;
        }
        return false;
    }

    public void updateValues(Set<AttributeValuesResponse> attributeValuesRequests, Attribute attribute) {
        if (attribute != null) {
            attributeValuesRequests.forEach(attributeValue -> {
                Optional<AttributeValues> attributeValues = attribute.getAttributeValues().stream().
                        filter(attr -> Objects.equals(attributeValue.getAttributeValueId(), attr.getAttributeValueId()))
                        .findFirst();
                if (attributeValues.isPresent()) {
                    attributeValues.get().setValue(attributeValue.getValue());
                    attributeValues.get().setAdditionalPrice(attributeValue.getAdditionalPrice());
                    attributeValuesRepository.save(attributeValues.get());
                } else {
                    attributeValuesRepository.save(AttributeValues.builder()
                            .value(attributeValue.getValue())
                            .additionalPrice(attributeValue.getAdditionalPrice())
                            .attribute(attribute).build());
                }
            });
        };
    }

}
