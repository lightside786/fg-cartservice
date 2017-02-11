package com.lightside.fg.web.controller;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.domain.CartItem;
import com.lightside.fg.exception.NoRecordFoundException;
import com.lightside.fg.service.ICartItemService;
import com.lightside.fg.web.exception.ErrorReporter;
import com.lightside.fg.web.request.CartItemRequest;
import com.lightside.fg.web.request.mapper.CartItemRequestMapper;
import com.lightside.fg.web.response.CartItemResponse;
import com.lightside.fg.web.response.CreateCartResponse;
import com.lightside.fg.web.response.mapper.CartItemResponseMapper;
import com.lightside.fg.web.response.mapper.CartResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author UmmerS
 */

@RestController
@RequestMapping("/api/v1/cart/{cartId}/cartitem")
@Slf4j
public class CartItemController implements ICartItemController {

    private ICartItemService cartItemService;
    private CartItemRequestMapper cartItemRequestMapper;
    private ErrorReporter errorReporter;
    private CartResponseMapper cartResponseMapper;
    private CartItemResponseMapper cartItemResponseMapper;

    public CartItemController(ICartItemService cartItemService,
                              CartItemResponseMapper responseMapper,
                              ErrorReporter errorReporter,
                              CartResponseMapper cartResponseMapper,
                              CartItemRequestMapper cartItemRequestMapper,
                              CartItemResponseMapper cartItemResponseMapper) {
        this.cartItemService = cartItemService;
        this.cartItemRequestMapper = cartItemRequestMapper;
        this.errorReporter = errorReporter;
        this.cartResponseMapper = cartResponseMapper;
        this.cartItemResponseMapper = cartItemResponseMapper;
    }

    public CreateCartResponse createCartItem(@PathVariable(value = "cartId") String cartId,
                                             @RequestBody @Valid CartItemRequest cartItemRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Error creating Cart item: {}", cartItemRequest.toString());
            return CreateCartResponse.builder()
                    .errors(errorReporter.createErrors(bindingResult))
                    .valid(false)
                    .build();
        }
        Cart cart = cartItemService.createCartItem(cartId, cartItemRequestMapper.map(cartItemRequest));
        log.info("Cart item was created in the cart : {}", cart.getId());
        return cartResponseMapper.map(cart);
    }


    public CreateCartResponse updateCartItem(
            @PathVariable(value = "cartId") String cartId,
            @PathVariable(value = "recordId") String recordId,
            @RequestBody @Valid CartItemRequest cartItemRequest, BindingResult bindingResult) {

        CartItem cartItem = null;
        if (bindingResult.hasErrors()) {
            log.info("Error updating Cart : {}", cartItem);
            return CreateCartResponse.builder()
                    .errors(errorReporter.createErrors(bindingResult))
                    .valid(false)
                    .build();
        }

        if (StringUtils.isNotBlank(recordId)) {
            cartItem = cartItemService.getCartItemByRecordId(recordId);
            if (cartItem == null) {
                log.info("Cart cannot be found for update request");
                return CreateCartResponse
                        .builder()
                        .errors(errorReporter.createErrors(new ObjectError("cartitem.doesnotexist", "cartitem.doesnotexist")))
                        .build();
            }
        }
        log.info("Updating Cart with details : {}", cartItemRequest);
        cartItem = cartItemRequestMapper.map(cartItemRequest);
        cartItem.setRecordId(recordId);
        Cart cart = cartItemService.updateCartItem(cartId, cartItem);
        log.info("Cart updated with addition of cart Item with recordId : {}", cartItem.getId());

        return cartResponseMapper.map(cart);

    }

    public CartItemResponse getCartItem(
            @PathVariable(value = "cartId") String cartId,
            @PathVariable(value = "recordId") String recordId) {
        CartItem cartItem = null;
        if (StringUtils.isNotEmpty(recordId)) {
            log.info("Retrieving Cart Item details for recordId : {}", recordId);
            cartItem = cartItemService.getCartItemByRecordId(recordId);
            log.info("Retrieved Cart Item with details : {}", cartItem);
        }
        if (cartItem == null) {
            throw new NoRecordFoundException("notfound.error.key", "notfound.error.message");
        }
        return cartItemResponseMapper.map(cartItem);
    }


    public CreateCartResponse deleteCartItem(
            @PathVariable(value = "cartId") final String cartId,
            @PathVariable(value = "recordId") String recordId) {
        Cart cart = cartItemService.deleteByRecordId(cartId, recordId);
        return cartResponseMapper.map(cart);
    }


}
