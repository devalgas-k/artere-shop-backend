package com.artere.shop.cart.infrastructure.adapter.out.persistence;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.mapper.CartMapper;
import com.artere.shop.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.jdbc.Sql;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CartPersistenceAdapterTest {

    @Autowired
    private CartPersistenceAdapter adapter;

    @Test
    @Sql("/insert_test_catalogue.sql")
    void should_save_cart_with_items() {
        Cart cart = new Cart(new CartId(1L));
        cart.addProduct(new ProductId(2L), 3, Money.of("15.50"));

        Cart saved = adapter.save(cart);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getItems()).hasSize(1);

        Optional<Cart> found = adapter.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.orElseThrow().getItems()).hasSize(1);
        assertThat(found.orElseThrow().getItems().get(0).getQuantity()).isEqualTo(3);
    }
}
