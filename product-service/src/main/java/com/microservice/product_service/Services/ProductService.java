package com.microservice.product_service.Services;

import com.microservice.product_service.Dtos.ProductRequest;
import com.microservice.product_service.Dtos.ProductResponse;
import com.microservice.product_service.Dtos.ShortProductResponse;
import com.microservice.product_service.Mappers.ProductMapper;
import com.microservice.product_service.Models.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import com.microservice.product_service.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductAttributeVariantService productAttributeVariantService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    public List<ShortProductResponse> getAllProductsShort(Pageable pageable) {
        return productRepository.findAll(pageable).stream().map(ProductMapper::mapToShortProductDTO).toList();
    }

    static List<ShortProductResponse> getShortProductResponses(Set<Product> products2, boolean forAdmin) {
        try {
            List<Product> products = products2.stream().toList();
            if (!forAdmin) {
                products = products.stream().filter(Product::getPublished).toList();
            }
            return products.stream().map(ProductMapper::mapToShortProductDTO).toList();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    public ProductResponse getProduct(Long id) {
        return ProductMapper.mapToProductResponse(productRepository.getReferenceById(id));
    }

    public Long addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productDescription(productRequest.getProductDescription())
                .regularPrice(productRequest.getRegularPrice())
                .discountPrice(productRequest.getDiscountPrice())
                .SKU(productRequest.getSKU())
                .tags(productRequest.getTags().stream().map((id) -> tagService.getFullTag(id)).collect(Collectors.toSet()))
                .categories(productRequest.getCategories().stream().map((id) -> categoryService.getFullCategory(id)).collect(Collectors.toSet()))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        productAttributeVariantService.addValues(product, productRequest.getAttributes(), "attributes");
        productAttributeVariantService.addValues(product, productRequest.getVariants(), "variants");
        return productRepository.save(product).getProductId();
    }

    public Long update(Long id, ProductRequest productRequest) {
        try {
            Product product = productRepository.getReferenceById(id);
            if (productRequest.getProductName() != null) {
                product.setProductName(productRequest.getProductName());
            }
            if (productRequest.getProductDescription() != null) {
                product.setProductDescription(productRequest.getProductDescription());
            }
            if (productRequest.getSKU() != null) {
                product.setSKU(productRequest.getSKU());
            }
            if (productRequest.getRegularPrice() != null) {
                product.setRegularPrice(productRequest.getRegularPrice());
            }
            if (productRequest.getDiscountPrice() != null) {
                product.setDiscountPrice(productRequest.getDiscountPrice());
            }
            if (productRequest.getPublished() != null) {
                product.setPublished(productRequest.getPublished());
            }
            if (productRequest.getAttributes() != null) {
                productAttributeVariantService.addValues(product, productRequest.getAttributes(), "attributes");
            }
            if (productRequest.getVariants() != null) {
                productAttributeVariantService.addValues(product, productRequest.getVariants(), "variants");
            }
            if (productRequest.getTags() != null) {
                product.setTags(productRequest.getTags().stream().map((tagId) -> tagService.getFullTag(tagId)).collect(Collectors.toSet()));
            }
            if (productRequest.getCategories() != null) {
                product.setCategories(productRequest.getCategories().stream().map((catId) -> categoryService.getFullCategory(catId)).collect(Collectors.toSet()));
            }
            product.setUpdatedAt(new Date());
            return productRepository.save(product).getProductId();
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }


}
