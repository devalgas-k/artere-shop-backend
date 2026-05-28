package com.artere.shop.cart.infrastructure.adapter.out.persistence;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.domain.port.out.CartRepositoryPort;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.mapper.CartMapper;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartPersistenceAdapter implements CartRepositoryPort {

    private final CartJpaRepository repository;
    private final CartMapper mapper;

    public CartPersistenceAdapter(CartJpaRepository repository, CartMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Cart save(Cart cart) {
        CartJpaEntity entity = mapper.toEntity(cart);
        CartJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Cart> findById(CartId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }
}
