package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Dtos.tag.TagFullResponse;
import com.microservice.product_service.Dtos.tag.TagResponse;
import com.microservice.product_service.Dtos.tag.TagsRequest;

import com.microservice.product_service.Mappers.TagMapper;
import com.microservice.product_service.Models.*;
import com.microservice.product_service.Repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.microservice.product_service.Services.ProductService.getShortProductResponses;


@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagResponse> getAll(Pageable pageable) {
        return tagRepository.findAll(pageable).stream().map(TagMapper::mapToTagResponse).toList();
    }

    public Long addTag(TagsRequest tagsRequest) {
        Tag tag = Tag.builder().tagName(tagsRequest.getTagName())
                .tagIcon(tagsRequest.getTagIcon())
                .tagType(tagsRequest.getTagType())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        //.createdBy()
        return tagRepository.save(tag).getTagId();
    }
    public List<ShortProductResponse> getTagProducts(Long id, boolean forAdmin) {
        return getShortProductResponses( tagRepository.getReferenceById(id).getProducts(),forAdmin);
    }

    public Long updateTag(Long id, TagsRequest tagsRequest) {
        try {
            Tag tag = tagRepository.getReferenceById(id);
            if (tagsRequest.getTagName() != null) {
                tag.setTagName(tagsRequest.getTagName());
            }
            if (tagsRequest.getTagIcon() != null) {
                tag.setTagIcon(tagsRequest.getTagIcon());
            }
            if (tagsRequest.getTagType() != null) {
                tag.setTagType(tagsRequest.getTagType());
            }
            tag.setUpdatedAt(new Date());
            return tagRepository.save(tag).getTagId();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    public TagFullResponse getTag(Long id) {
        try {
            Tag tag = tagRepository.getReferenceById(id);
            return TagMapper.mapToTagFullResponse(tag);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
    public Tag getFullTag(Long id) {
        try {
            return tagRepository.getReferenceById(id);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
}
