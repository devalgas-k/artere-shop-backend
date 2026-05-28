package com.artere.shop.catalogue.infrastructure.adapter.in.web;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.model.CategoryNode;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.catalogue.domain.port.in.CategoryTreeQuery;
import com.artere.shop.catalogue.domain.port.in.CategoryUseCase;
import com.artere.shop.catalogue.infrastructure.adapter.in.web.dto.CategoryResponse;
import com.artere.shop.catalogue.infrastructure.adapter.in.web.dto.CategoryTreeResponse;
import com.artere.shop.catalogue.infrastructure.adapter.in.web.dto.CreateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;
    private final CategoryTreeQuery categoryTreeQuery;

    public CategoryController(CategoryUseCase categoryUseCase, CategoryTreeQuery categoryTreeQuery) {
        this.categoryUseCase = categoryUseCase;
        this.categoryTreeQuery = categoryTreeQuery;
    }

    @GetMapping
    public List<CategoryTreeResponse> getCategoryTree() {
        return categoryTreeQuery.getCategoryTree().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CategoryTreeResponse mapToResponse(CategoryNode node) {
        return new CategoryTreeResponse(
                node.id(),
                node.name(),
                node.description(),
                node.children().stream().map(this::mapToResponse).toList()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryId parentId = request.parentId() != null ? new CategoryId(request.parentId()) : null;
        Category category = categoryUseCase.createCategory(request.name(), request.description(), parentId);
        
        return new CategoryResponse(
                category.getId() != null ? category.getId().value() : null,
                category.getName(),
                category.getDescription(),
                category.getParentId().map(CategoryId::value).orElse(null)
        );
    }

    @PostMapping("/{categoryId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignProductToCategory(
            @PathVariable Long categoryId,
            @PathVariable Long productId) {
        categoryUseCase.assignProductToCategory(new CategoryId(categoryId), new ProductId(productId));
    }
}
