package com.artere.shop.catalogue.infrastructure.adapter.out.persistence;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.port.out.CategoryRepositoryPort;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.CategoryJpaEntity;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper.CategoryMapper;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final CategoryJpaRepository repository;
    private final CategoryMapper mapper;

    public CategoryPersistenceAdapter(CategoryJpaRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Category save(Category category) {
        CategoryJpaEntity entity = mapper.toEntity(category);
        CategoryJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }
}
