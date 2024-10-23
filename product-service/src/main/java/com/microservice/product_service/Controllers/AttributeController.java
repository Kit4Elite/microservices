package com.microservice.product_service.Controllers;

import com.microservice.product_service.Dtos.attribute.AttributeRequest;
import com.microservice.product_service.Dtos.attribute.AttributeResponse;
import com.microservice.product_service.Dtos.attribute.AttributeValuesRequest;
import com.microservice.product_service.Dtos.attribute.AttributeValuesResponse;
import com.microservice.product_service.Services.AttributeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService ;
    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody  @Valid AttributeRequest<AttributeValuesRequest> attributeRequest) {
        return attributeService.addAttribute(attributeRequest);
    }
    @PostMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<AttributeResponse> getAll() {
        return attributeService.getAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttributeResponse getDetails(@PathVariable Long id) {
        return attributeService.getAttribute(id);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    //"attributeValueId": 0: for new attribute value
    public Long update(@RequestBody AttributeRequest<AttributeValuesResponse> attributeRequest, @PathVariable Long id) {
        return attributeService.updateAttribute(attributeRequest,id);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long Delete( @PathVariable Long id) {
        return attributeService.deleteVariant(id)?id:null;
    }
}
