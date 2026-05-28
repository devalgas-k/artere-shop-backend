package com.artere.shop.catalogue.infrastructure.adapter.in.web.dto;

import java.util.List;

public record CategoryTreeResponse(
    Long id,
    String name,
    String description,
    List<CategoryTreeResponse> children
) {}
