package com.artere.shop.cart.infrastructure.adapter.in.web;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.domain.port.in.CartUseCase;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerApprovalTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartUseCase cartUseCase;

    @Test
    void should_return_cart_details() throws Exception {
        Cart cart = new Cart(new CartId(10L));
        cart.addProduct(new ProductId(100L), 2, new Money(new BigDecimal("15.50")));
        cart.addProduct(new ProductId(101L), 1, new Money(new BigDecimal("42.00")));
        
        Mockito.when(cartUseCase.getCart(new CartId(10L))).thenReturn(cart);

        MvcResult result = mockMvc.perform(get("/api/v1/carts/10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Approvals.verifyJson(jsonResponse);
    }
}
