package com.artere.shop.cart.infrastructure.adapter.out.persistence.repository;

import com.artere.shop.cart.infrastructure.adapter.out.persistence.entity.CartJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartJpaEntity, Long> {
    @EntityGraph(attributePaths = {"items"})
    Optional<CartJpaEntity> findById(Long id);
}
