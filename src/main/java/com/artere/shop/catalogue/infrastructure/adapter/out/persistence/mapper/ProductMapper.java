package com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper;

import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.model.Product;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.catalogue.domain.model.StockQuantity;
import com.artere.shop.shared.domain.model.Money;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "productIdToLong")
    @Mapping(target = "price", source = "price", qualifiedByName = "moneyToBigDecimal")
    @Mapping(target = "stockQuantity", source = "stockQuantity", qualifiedByName = "stockQuantityToInteger")
    @Mapping(target = "categoryIds", source = "categoryIds", qualifiedByName = "categoryIdsToLongs")
    ProductJpaEntity toEntity(Product product);

    @Mapping(target = "id", source = "id", qualifiedByName = "longToProductId")
    @Mapping(target = "price", source = "price", qualifiedByName = "bigDecimalToMoney")
    @Mapping(target = "stockQuantity", source = "stockQuantity", qualifiedByName = "integerToStockQuantity")
    @Mapping(target = "categoryIds", ignore = true)
    Product toDomain(ProductJpaEntity entity);

    @AfterMapping
    default void assignCategories(@MappingTarget Product product, ProductJpaEntity entity) {
        if (entity.getCategoryIds() != null) {
            entity.getCategoryIds().forEach(id -> product.assignToCategory(new CategoryId(id)));
        }
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

    @Named("stockQuantityToInteger")
    default Integer stockQuantityToInteger(StockQuantity stock) {
        return stock != null ? stock.value() : null;
    }

    @Named("integerToStockQuantity")
    default StockQuantity integerToStockQuantity(Integer value) {
        return value != null ? new StockQuantity(value) : null;
    }

    @Named("categoryIdsToLongs")
    default Set<Long> categoryIdsToLongs(Set<CategoryId> ids) {
        if (ids == null) return null;
        return ids.stream().map(CategoryId::value).collect(Collectors.toSet());
    }
}
