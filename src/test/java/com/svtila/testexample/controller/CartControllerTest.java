package com.svtila.testexample.controller;

import com.svtila.testexample.TestexampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TestexampleApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public class CartControllerTest {

    private final String ENDPOINT = "/api/cart";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showCart() throws Exception {

        mockMvc.perform(
                get(ENDPOINT)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful()).andDo(print())
        ;
    }

    @Test
    public void addProductToCart() throws Exception {
        mockMvc.perform(
                post(ENDPOINT + "/product/1" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful()).andDo(print())
        ;
    }

    @Test
    public void deleteProductFromCart() throws Exception {
        mockMvc.perform(
                delete(ENDPOINT + "/product/1" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful()).andDo(print())
        ;

    }

    @Test
    public void submit() throws Exception {

        mockMvc.perform(
                post(ENDPOINT + "/product/1" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
        ;

        mockMvc.perform(
                post(ENDPOINT + "/product/2" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
        ;mockMvc.perform(
                post(ENDPOINT + "/product/2" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
        ;
        mockMvc.perform(
                post(ENDPOINT + "/product/3" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
        ;
        mockMvc.perform(
                post(ENDPOINT + "/product/3" )
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
        ;

        mockMvc.perform(
                post(ENDPOINT)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful()).andDo(print())
                .andExpect(jsonPath("$.products").isNotEmpty())
                .andExpect(jsonPath("$.rawTotal").value("1500.0"))
                .andExpect(jsonPath("$.totalPromos").value("540.0"))
                .andExpect(jsonPath("$.totalPayable").value("960.0"))

        ;
    }
}