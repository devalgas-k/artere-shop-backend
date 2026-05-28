package com.artere.shop.cart.infrastructure.adapter.out.persistence.mapper;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.domain.model.CartItem;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.entity.CartItemJpaEntity;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "cartIdToLong")
    @Mapping(target = "items", source = "items")
    CartJpaEntity toEntity(Cart cart);

    default Cart toDomain(CartJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        List<CartItem> mappedItems = entity.getItems() != null ? 
            entity.getItems().stream().map(this::entityToCartItem).toList() : 
            java.util.List.of();
            
        return new Cart(
            longToCartId(entity.getId()),
            entity.getCreatedAt(),
            mappedItems
        );
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "productId", source = "productId", qualifiedByName = "productIdToLong")
    @Mapping(target = "unitPrice", source = "unitPrice", qualifiedByName = "moneyToBigDecimal")
    CartItemJpaEntity cartItemToEntity(CartItem cartItem);

    @Mapping(target = "productId", source = "productId", qualifiedByName = "longToProductId")
    @Mapping(target = "unitPrice", source = "unitPrice", qualifiedByName = "bigDecimalToMoney")
    CartItem entityToCartItem(CartItemJpaEntity entity);

    @AfterMapping
    default void linkCartItems(@MappingTarget CartJpaEntity cartEntity) {
        if (cartEntity.getItems() != null) {
            for (CartItemJpaEntity item : cartEntity.getItems()) {
                item.setCart(cartEntity);
            }
        }
    }

    @Named("cartIdToLong")
    default Long cartIdToLong(CartId id) {
        return id != null ? id.value() : null;
    }

    @Named("longToCartId")
    default CartId longToCartId(Long id) {
        return id != null ? new CartId(id) : null;
    }

    @Named("productIdToLong")
    default Long productIdToLong(ProductId id) {
        return id != null ? id.value() : null;
    }

    @Named("longToProductId")
    default ProductId longToProductId(Long id) {
        return id != null ? new ProductId(id) : null;
    }

    @Named("moneyToBigDecimal")
    default BigDecimal moneyToBigDecimal(Money money) {
        return money != null ? money.amount() : null;
    }

    @Named("bigDecimalToMoney")
    default Money bigDecimalToMoney(BigDecimal amount) {
        return amount != null ? new Money(amount) : null;
    }
}
