package com.artere.shop.catalogue.infrastructure.adapter.in.web.dto;

public record CategoryResponse(
    Long id,
    String name,
    String description,
    Long parentId
) {}
