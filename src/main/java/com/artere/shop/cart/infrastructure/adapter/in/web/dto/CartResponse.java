package com.artere.shop.cart.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
    Long id,
    BigDecimal totalAmount,
    List<CartItemResponse> items
) {}
