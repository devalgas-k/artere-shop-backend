package com.artere.shop.catalogue.infrastructure.adapter.out.persistence;

import com.artere.shop.catalogue.domain.model.Product;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.catalogue.domain.port.out.ProductRepositoryPort;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper.ProductMapper;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository repository;
    private final ProductMapper mapper;

    public ProductPersistenceAdapter(ProductJpaRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = mapper.toEntity(product);
        ProductJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }
}
