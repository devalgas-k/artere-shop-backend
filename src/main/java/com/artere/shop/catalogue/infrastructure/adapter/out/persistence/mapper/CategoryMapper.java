package com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.CategoryJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "categoryIdToLong")
    @Mapping(target = "parentId", source = "parentId", qualifiedByName = "optionalCategoryIdToLong")
    CategoryJpaEntity toEntity(Category category);

    @Mapping(target = "id", source = "id", qualifiedByName = "longToCategoryId")
    @Mapping(target = "parentId", source = "parentId", qualifiedByName = "longToOptionalCategoryId")
    Category toDomain(CategoryJpaEntity entity);

    @Named("categoryIdToLong")
    default Long categoryIdToLong(CategoryId id) {
        return id != null ? id.value() : null;
    }

    @Named("optionalCategoryIdToLong")
    default Long optionalCategoryIdToLong(Optional<CategoryId> id) {
        return id.map(CategoryId::value).orElse(null);
    }

    @Named("longToCategoryId")
    default CategoryId longToCategoryId(Long id) {
        return id != null ? new CategoryId(id) : null;
    }

    @Named("longToOptionalCategoryId")
    default CategoryId longToOptionalCategoryId(Long id) {
        return id != null ? new CategoryId(id) : null;
    }
}
