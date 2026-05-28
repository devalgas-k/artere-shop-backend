package com.artere.shop.cart.infrastructure.adapter.out.persistence.mapper;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.domain.model.CartItem;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.entity.CartItemJpaEntity;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartJpaEntity toEntity(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartJpaEntity entity = new CartJpaEntity();
        entity.setId(cart.getId() != null ? cart.getId().value() : null);
        entity.setCreatedAt(cart.getCreatedAt());
        
        if (cart.getItems() != null) {
            List<CartItemJpaEntity> itemEntities = cart.getItems().stream()
                .map(this::cartItemToEntity)
                .toList();
            entity.setItems(itemEntities);
            for (CartItemJpaEntity item : itemEntities) {
                item.setCart(entity);
            }
        }
        return entity;
    }

    public Cart toDomain(CartJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        List<CartItem> mappedItems = entity.getItems() != null ? 
            entity.getItems().stream().map(this::entityToCartItem).toList() : 
            java.util.List.of();
            
        return new Cart(
            entity.getId() != null ? new CartId(entity.getId()) : null,
            entity.getCreatedAt(),
            mappedItems
        );
    }

    public CartItemJpaEntity cartItemToEntity(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        CartItemJpaEntity entity = new CartItemJpaEntity();
        entity.setProductId(cartItem.getProductId() != null ? cartItem.getProductId().value() : null);
        entity.setUnitPrice(cartItem.getUnitPrice() != null ? cartItem.getUnitPrice().amount() : null);
        entity.setQuantity(cartItem.getQuantity());
        return entity;
    }

    public CartItem entityToCartItem(CartItemJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CartItem(
            entity.getProductId() != null ? new ProductId(entity.getProductId()) : null,
            entity.getQuantity(),
            entity.getUnitPrice() != null ? new Money(entity.getUnitPrice()) : null
        );
    }
}