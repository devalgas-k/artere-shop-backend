package com.artere.shop.catalogue.infrastructure.adapter.out.persistence.repository;

import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}
