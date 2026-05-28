package com.artere.shop.catalogue.infrastructure.adapter.out.persistence;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper.CategoryMapper;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryPersistenceAdapterTest {

    @Autowired
    private CategoryPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_save_and_find_category() {
        Category category = new Category(new CategoryId(10L), "Electronics", "All electronic devices", null);

        Category saved = adapter.save(category);

        assertThat(saved.getId()).isNotNull();
        
        Optional<Category> found = adapter.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.orElseThrow().getName()).isEqualTo("Electronics");
    }
}
