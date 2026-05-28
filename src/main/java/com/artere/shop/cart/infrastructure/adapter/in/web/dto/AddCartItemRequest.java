package com.artere.shop.cart.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddCartItemRequest(
    @NotNull Long productId,
    @Min(1) int quantity
) {}
