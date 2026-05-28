package com.artere.shop.catalogue.infrastructure.adapter.in.web;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.port.in.CategoryTreeQuery;
import com.artere.shop.catalogue.domain.port.in.CategoryUseCase;
import com.artere.shop.catalogue.domain.model.CategoryNode;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerApprovalTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryUseCase categoryUseCase;

    @MockBean
    private CategoryTreeQuery categoryTreeQuery;

    @Test
    void should_return_category_tree() throws Exception {
        CategoryNode child = new CategoryNode(2L, "Laptops", "Laptop computers", List.of());
        CategoryNode root = new CategoryNode(1L, "Electronics", "Electronic items", List.of(child));

        Mockito.when(categoryTreeQuery.getCategoryTree()).thenReturn(List.of(root));

        MvcResult result = mockMvc.perform(get("/api/v1/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Approvals.verifyJson(jsonResponse);
    }
    
    @Test
    void should_create_category() throws Exception {
        Category category = new Category(new CategoryId(3L), "Smartphones", "Phones", new CategoryId(1L));
        Mockito.when(categoryUseCase.createCategory("Smartphones", "Phones", new CategoryId(1L)))
                .thenReturn(category);

        String requestBody = """
                {
                  "name": "Smartphones",
                  "description": "Phones",
                  "parentId": 1
                }
                """;

        MvcResult result = mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Approvals.verifyJson(jsonResponse);
    }
}
