package com.artere.shop.cart.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;

public record CartItemResponse(
    Long productId,
    int quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice
) {}
