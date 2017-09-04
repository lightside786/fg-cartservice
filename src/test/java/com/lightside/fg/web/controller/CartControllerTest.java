package com.lightside.fg.web.controller;

import com.lightside.fg.domain.UnitOfMeasure;
import com.lightside.fg.web.request.CartItemRequest;
import com.lightside.fg.web.request.CartRequest;
import com.lightside.fg.web.request.ItemQuantity;
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
import java.util.Arrays;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
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
                .andExpect(jsonPath("$.userId").value("ummerstest"))
                .andExpect(jsonPath("$.total").value("229.0"))
                .andExpect(jsonPath("$.createdOn").isNotEmpty())
                .andExpect(jsonPath("$.updatedOn").isNotEmpty())
                .andExpect(jsonPath("$.cartItems[0].recordId").value("init-cart-item-recordId-1000001"))
                .andExpect(jsonPath("$.cartItems[0].productId").value("init-cart-product-id-100001"))
                .andExpect(jsonPath("$.cartItems[0].primary.quantity").value("75.0"))
                .andExpect(jsonPath("$.cartItems[0].primary.unitOfMeasure").value("EACH"))
                .andExpect(jsonPath("$.cartItems[0].secondary.quantity").doesNotExist())
                .andExpect(jsonPath("$.cartItems[0].createdOn").isNotEmpty())
                .andExpect(jsonPath("$.cartItems[0].updatedOn").isNotEmpty())
                .andExpect(jsonPath("$.cartItems[0].price").value("1.72"))
                .andExpect(jsonPath("$.cartItems[0].total").value("129.0"))
                .andExpect(jsonPath("$.cartItems[1].recordId").value("init-cart-item-recordId-1000002"))
                .andExpect(jsonPath("$.cartItems[1].productId").value("init-cart-product-id-100002"))
                .andExpect(jsonPath("$.cartItems[1].primary.quantity").value("10.0"))
                .andExpect(jsonPath("$.cartItems[1].primary.unitOfMeasure").value("EACH"))
                .andExpect(jsonPath("$.cartItems[1].secondary.quantity").doesNotExist())
                .andExpect(jsonPath("$.cartItems[1].createdOn").isNotEmpty())
                .andExpect(jsonPath("$.cartItems[1].updatedOn").isNotEmpty())
                .andExpect(jsonPath("$.cartItems[1].price").value("10.0"))
                .andExpect(jsonPath("$.cartItems[1].total").value("100.0"))
                .andDo(print());
    }


    @Test
    public void testDeleteCartByID() throws Exception {
        ResultActions result = this.mockMvc.perform(delete("/api/v1/cart/init-cart-record-recordId-1000001"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void testAddCart() throws Exception {

        CartRequest cart = CartRequest.builder()
                .userId("unittester")
                .build();

        CartItemRequest cartItemRequest = CartItemRequest.builder()
                .price(BigDecimal.valueOf(10))
                .primary(ItemQuantity.builder().quantity(Double.valueOf(10))
                        .unitOfMeasure(UnitOfMeasure.BOX)
                        .build())
                .productId(UUID.randomUUID().toString())
                .build();

        CartItemRequest cartItemRequest2 = CartItemRequest.builder()
                .price(BigDecimal.valueOf(20))
                .primary(ItemQuantity.builder().quantity(Double.valueOf(10))
                        .unitOfMeasure(UnitOfMeasure.EACH)
                        .build())
                .productId(UUID.randomUUID().toString())
                .build();

        CartItemRequest cartItemRequest3 = CartItemRequest.builder()
                .price(BigDecimal.valueOf(20))
                .primary(ItemQuantity.builder().quantity(Double.valueOf(10))
                        .unitOfMeasure(UnitOfMeasure.KG)
                        .build())
                .secondary(ItemQuantity.builder().quantity(Double.valueOf(500))
                        .unitOfMeasure(UnitOfMeasure.GM)
                        .build())
                .productId(UUID.randomUUID().toString())
                .build();

        cart.setCartItems(Arrays.asList(cartItemRequest, cartItemRequest2, cartItemRequest3));

        String cartJson = json(cart);

        MvcResult resultCheck = this.mockMvc.perform(post("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cart.total").value("510.0"))
                .andExpect(jsonPath("$.cart.userId").value("unittester"))
                .andExpect(jsonPath("$.cart.cartItems.[0].total").value("100.0"))
                .andExpect(jsonPath("$.cart.cartItems.[1].total").value("200.0"))
                .andExpect(jsonPath("$.cart.cartItems.[2].total").value("210.0"))
                .andReturn();

    }

    @Test
    public void testAddCart_OneItem() throws Exception {

        CartRequest cart = CartRequest.builder()
                .userId("unittester_oneItem")
                .build();

        CartItemRequest cartItemRequest = CartItemRequest.builder()
                .price(BigDecimal.valueOf(32.5))
                .primary(ItemQuantity.builder().quantity(Double.valueOf(10))
                        .unitOfMeasure(UnitOfMeasure.BOX)
                        .build())
                .productId(UUID.randomUUID().toString())
                .build();


        cart.setCartItems(Arrays.asList(cartItemRequest));

        String cartJson = json(cart);

        MvcResult resultCheck = this.mockMvc.perform(post("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cart.cartItems.[0].total").value("325.0"))
                .andReturn();

    }

}
