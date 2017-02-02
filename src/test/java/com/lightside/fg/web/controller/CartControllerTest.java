package com.lightside.fg.web.controller;

import com.lightside.fg.domain.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * * * Copyright (c) 2016 SAP/Concur Technologies - Present
 * Created by ummers on 10/11/16.
 * All Rights reserved.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Sql(scripts = "classpath:cart.sql", config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.DEFAULT))
public class CartControllerTest extends GenericControllerTest {

    @Test
    public void testGetCartWithID() throws Exception {
        this.mockMvc.perform(get("/api/v1/cart/init-cart-record-recordId-1000001"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value("init-cart-record-recordId-1000001"))
                .andExpect(jsonPath("$.userId").value("mcrawford0"))
                .andExpect(jsonPath("$.total").value("88"))
                .andDo(print());
    }


    @Test
    public void testDeleteCartByID() throws Exception {
        ResultActions result = this.mockMvc.perform(delete("/api/v1/cart/init-cart-record-recordId-1000001"))
                .andExpect(status().isOk());
    }


    @Test
    public void testAddCart() throws Exception {
        Cart cart = Cart.builder()
                .userId("unittester")
                .total(BigDecimal.valueOf(10.00))
                .build();


        String cartJson = json(cart);

        MvcResult resultCheck = this.mockMvc.perform(post("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

    }

}
