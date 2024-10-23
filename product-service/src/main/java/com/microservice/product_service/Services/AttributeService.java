package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.attribute.AttributeRequest;
import com.microservice.product_service.Dtos.attribute.AttributeResponse;
import com.microservice.product_service.Dtos.attribute.AttributeValuesRequest;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Mappers.AttributeMapper;
import com.microservice.product_service.Models.Attribute;
import com.microservice.product_service.Repositories.AttributeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeValuesService attributeValuesService;

    @Autowired
    public AttributeService(AttributeRepository attributeRepository, AttributeValuesService attributeValuesService) {
        this.attributeRepository = attributeRepository;
        this.attributeValuesService = attributeValuesService;
    }

    public Long addAttribute(AttributeRequest<AttributeValuesRequest> attribute) {
        Attribute attribute1 = Attribute.builder()
                .attributeName(attribute.getAttributeName())
                .attributeDescription(attribute.getAttributeDescription())
                .build();
        Attribute attribute1Id = attributeRepository.save(attribute1);
        if (!attribute.getAttributeValues().isEmpty()) {
            Boolean isInserted = attributeValuesService
                    .addValues(attribute.getAttributeValues(), attribute1Id);
            if (!isInserted) {
                System.out.println("Error : attribute values not inserted !");
            }
        }
        return attribute1Id.getAttributeId();
    }
    public AttributeResponse getAttribute(Long id) {
        try {
            return AttributeMapper.mapToAttributeResponse(attributeRepository.getReferenceById(id));
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
    public Attribute getFullAttribute(Long id) {
        try {
            return attributeRepository.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
    public List<AttributeResponse> getAll() {
        return attributeRepository.findAll().stream()
                .map(AttributeMapper::mapToAttributeResponse).toList();
    }

    public Long updateAttribute(AttributeRequest<AttributeValuesResponse> attributeRequest, Long id) {
        try {
            Attribute attribute = attributeRepository.getReferenceById(id);
            if (attributeRequest.getAttributeName() != null) {
                attribute.setAttributeName(attributeRequest.getAttributeName());
            }
            if (attributeRequest.getAttributeDescription() != null) {
                attribute.setAttributeDescription(attributeRequest.getAttributeDescription());
            }
            attributeValuesService.updateValues(attributeRequest.getAttributeValues(), attribute);
            return attributeRepository.save(attribute).getAttributeId();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    public boolean deleteVariant(Long id) {
        attributeRepository.deleteById(id);
        return true;
    }
}