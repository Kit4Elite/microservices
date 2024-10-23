package com.microservice.product_service.Controllers;

import com.microservice.product_service.Dtos.attribute.AttributeRequest;
import com.microservice.product_service.Dtos.attribute.AttributeResponse;
import com.microservice.product_service.Dtos.attribute.AttributeValuesRequest;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Dtos.variant.VariantRequest;
import com.microservice.product_service.Dtos.variant.VariantResponse;
import com.microservice.product_service.Dtos.variant.VariantValuesRequest;
import com.microservice.product_service.Dtos.variant.VariantValuesResponse;
import com.microservice.product_service.Services.AttributeService;
import com.microservice.product_service.Services.VariantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variant")
public class VariantController {
    @Autowired
    private VariantService variantService ;
    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody @Valid VariantRequest<VariantValuesRequest> variantRequest) {
        return variantService.addVariant(variantRequest);
    }
    @PostMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<VariantResponse> getAll() {
        return variantService.getAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VariantResponse getDetails(@PathVariable Long id) {
        return variantService.getVariant(id);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    //"variantValueId": 0: for new attribute value
    public Long update(@RequestBody VariantRequest<VariantValuesResponse> variantRequest, @PathVariable Long id) {
        return variantService.updateVariant(variantRequest,id);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long Delete( @PathVariable Long id) {
        return variantService.deleteVariant(id)?id:null;
    }
}
