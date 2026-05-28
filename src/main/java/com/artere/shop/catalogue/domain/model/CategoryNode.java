package com.artere.shop.catalogue.domain.model;

import java.util.List;

public record CategoryNode(
    Long id,
    String name,
    String description,
    List<CategoryNode> children
) {}
