package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.attribute.AttributeRequest;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Dtos.variant.VariantRequest;
import com.microservice.product_service.Dtos.variant.VariantResponse;
import com.microservice.product_service.Dtos.variant.VariantValuesRequest;
import com.microservice.product_service.Dtos.variant.VariantValuesResponse;
import com.microservice.product_service.Mappers.AttributeMapper;
import com.microservice.product_service.Mappers.VariantMapper;
import com.microservice.product_service.Models.Attribute;
import com.microservice.product_service.Models.Variant;
import com.microservice.product_service.Repositories.VariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VariantService {
    private final VariantRepository variantRepository;
    private final VariantValuesService variantValuesService;
    @Autowired
    public VariantService(VariantRepository variantRepository,VariantValuesService variantValuesService) {
        this.variantRepository = variantRepository;
        this.variantValuesService = variantValuesService;
    }
    public Long addVariant(VariantRequest<VariantValuesRequest> variantRequest) {
        Variant variant = Variant.builder()
                .variantName(variantRequest.getVariantName())
                .variantDescription(variantRequest.getVariantDescription())
                .build();
        Variant variantId = variantRepository.save(variant);
        if (!variantRequest.getVariantValues().isEmpty()) {
            Boolean isInserted = variantValuesService
                    .addValues(variantRequest.getVariantValues(), variantId);
            if (!isInserted) {
                System.out.println("Error : attribute values not inserted !");
            }
        }
        return variantId.getVariantId();
    }
    public List<VariantResponse> getAll() {
        return variantRepository.findAll().stream()
                .map(VariantMapper::mapToVariantResponse).toList();
    }
    public VariantResponse getVariant(Long id) {
        try {
            return VariantMapper.mapToVariantResponse(variantRepository.getReferenceById(id));
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
    public Variant getFullVariant(Long id) {
        try {
            System.out.println(variantRepository.getReferenceById(id).getVariantId());

            return variantRepository.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
    public Long updateVariant(VariantRequest<VariantValuesResponse> variantRequest, Long id) {
        try {
            Variant variant = variantRepository.getReferenceById(id);
            if (variantRequest.getVariantName() != null) {
                variant.setVariantName(variantRequest.getVariantName());
            }
            if (variantRequest.getVariantDescription() != null) {
                variant.setVariantDescription(variantRequest.getVariantDescription());
            }
            variantValuesService.updateValues(variantRequest.getVariantValues(), variant);
            return variantRepository.save(variant).getVariantId();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    public boolean deleteVariant(Long id) {
        variantRepository.deleteById(id);
        return true;
    }
}
