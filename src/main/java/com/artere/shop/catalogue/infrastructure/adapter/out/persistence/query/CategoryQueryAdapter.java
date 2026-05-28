package com.artere.shop.catalogue.infrastructure.adapter.out.persistence.query;

import com.artere.shop.catalogue.domain.model.CategoryNode;
import com.artere.shop.catalogue.domain.port.in.CategoryTreeQuery;
import org.jooq.generated.tables.records.CategoryRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.jooq.generated.Tables.CATEGORY;

@Component
public class CategoryQueryAdapter implements CategoryTreeQuery {

    private final DSLContext dsl;

    public CategoryQueryAdapter(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<CategoryNode> getCategoryTree() {
        List<CategoryRecord> allCategories = dsl.selectFrom(CATEGORY).fetch();

        // Build the tree
        Map<Long, List<CategoryRecord>> byParentId = allCategories.stream()
                .filter(c -> c.getParentId() != null)
                .collect(Collectors.groupingBy(CategoryRecord::getParentId));

        return allCategories.stream()
                .filter(c -> c.getParentId() == null)
                .map(c -> mapToTree(c, byParentId))
                .toList();
    }

    private CategoryNode mapToTree(CategoryRecord record, Map<Long, List<CategoryRecord>> byParentId) {
        List<CategoryRecord> childrenRecords = byParentId.getOrDefault(record.getId(), List.of());
        List<CategoryNode> children = childrenRecords.stream()
                .map(child -> mapToTree(child, byParentId))
                .toList();

        return new CategoryNode(
                record.getId(),
                record.getName(),
                record.getDescription(),
                children
        );
    }
}
