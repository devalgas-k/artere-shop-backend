package com.artere.shop.catalogue.domain.port.in;

import com.artere.shop.catalogue.domain.model.CategoryNode;

import java.util.List;

public interface CategoryTreeQuery {
    List<CategoryNode> getCategoryTree();
}
