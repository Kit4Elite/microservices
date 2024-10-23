package com.microservice.product_service.Controllers;

import com.microservice.product_service.Dtos.CategoryFullResponse;
import com.microservice.product_service.Dtos.CategoryRequest;
import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Dtos.tag.TagFullResponse;
import com.microservice.product_service.Dtos.tag.TagResponse;
import com.microservice.product_service.Dtos.tag.TagsRequest;
import com.microservice.product_service.Services.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private  TagService tagService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> getALL(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tagService.getAll(pageable);
    }
    @PostMapping(path = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCategory(@RequestBody @Valid TagsRequest tagsRequest) {
        return tagService.addTag(tagsRequest);
    }
    @GetMapping("{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortProductResponse> TagProducts(@PathVariable @Valid Long id, @RequestParam(defaultValue = "0") boolean forAdmin, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return tagService.getTagProducts(id, false);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagFullResponse category(@PathVariable @Valid Long id) {
        return tagService.getTag(id);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable @Valid Long id, @RequestBody @Valid TagsRequest tagsRequest) {
        return tagService.updateTag(id, tagsRequest);
    }
}
