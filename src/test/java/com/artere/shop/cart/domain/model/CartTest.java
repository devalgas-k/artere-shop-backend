package com.artere.shop.cart.domain.model;

import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    @Test
    void should_add_product_to_cart() {
        Cart cart = new Cart(new CartId(1L));
        ProductId productId = new ProductId(10L);
        Money price = Money.of("10.50");

        cart.addProduct(productId, 2, price);

        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(2);
        assertThat(cart.calculateTotal().amount()).isEqualByComparingTo("21.00");
    }

    @Test
    void should_update_quantity_if_product_already_exists() {
        Cart cart = new Cart(new CartId(1L));
        ProductId productId = new ProductId(10L);
        Money price = Money.of("10.50");

        cart.addProduct(productId, 2, price);
        cart.addProduct(productId, 3, price);

        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(5);
        assertThat(cart.calculateTotal().amount()).isEqualByComparingTo("52.50");
    }

    @Property
    void total_amount_should_always_be_positive_or_zero(
            @ForAll("quantities") int quantity,
            @ForAll("prices") BigDecimal priceValue) {
        
        Cart cart = new Cart(new CartId(1L));
        ProductId productId = new ProductId(1L);
        Money price = new Money(priceValue);
        
        cart.addProduct(productId, quantity, price);
        
        assertThat(cart.calculateTotal().amount()).isGreaterThanOrEqualTo(BigDecimal.ZERO);
    }

    @Provide
    Arbitrary<Integer> quantities() {
        return Arbitraries.integers().between(1, 10000);
    }

    @Provide
    Arbitrary<BigDecimal> prices() {
        return Arbitraries.bigDecimals().between(BigDecimal.ZERO, new BigDecimal("1000000.00"));
    }
}
