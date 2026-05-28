package com.artere.shop.catalogue.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
    @NotBlank String name,
    String description,
    Long parentId
) {}
