package com.lightside.fg.web.controller;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.exception.NoRecordFoundException;
import com.lightside.fg.service.CartService;
import com.lightside.fg.web.exception.ErrorReporter;
import com.lightside.fg.web.mapper.CartDtoMapper;
import com.lightside.fg.web.mapper.CreateCartResponseMapper;
import com.lightside.fg.web.request.CreateCartRequest;
import com.lightside.fg.web.request.mapper.CartRequestMapper;
import com.lightside.fg.web.response.CartDto;
import com.lightside.fg.web.response.CreateCartResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

;

/**
 * @author Anwar
 */

@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController implements ICartController {

    private CartService cartService;
    private CartRequestMapper requestMapper;
    private CreateCartResponseMapper responseMapper;
    private CartDtoMapper cartDtoMapper;
    private ErrorReporter errorReporter;

    public CartController(CartService cartService,
                          CartRequestMapper requestMapper,
                          CreateCartResponseMapper responseMapper,
                          CartDtoMapper cartDtoMapper, ErrorReporter errorReporter) {
        this.cartService = cartService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.cartDtoMapper = cartDtoMapper;
        this.errorReporter = errorReporter;
    }

    public CreateCartResponse createCart(@RequestBody @Valid CreateCartRequest createCartRequest,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Error creating Cart : {}", createCartRequest);
            return CreateCartResponse.builder()
                    .errors(errorReporter.createErrors(bindingResult))
                    .valid(false)
                    .build();
        }
        log.info("Creating Cart with details : {}", createCartRequest);
        Cart cart = cartService.createCart(requestMapper.map(createCartRequest));
        log.info("Cart created with id : {}", cart.getId());
        return responseMapper.map(cart);
    }

    public CartDto getCart(@PathVariable(value = "recordId") String recordId) {
        Cart cart = null;
        if (StringUtils.isNotEmpty(recordId)) {
            log.info("Retrieving Cart details for id : {}", recordId);
            cart = cartService.getCartByRecordId(recordId);
            log.info("Retrieved Cart with details : {}", cart);
        }
        if (cart == null) {
            throw new NoRecordFoundException("notfound.error.key", "notfound.error.message");
        }
        return cartDtoMapper.map(cart);
    }

    public Page<CartDto> getCarts(Pageable pageable) {
        log.info("Retrieving Cart details with paging : {}", pageable);
        Page<Cart> page = cartService.getCarts(pageable);
        log.info("Retrieved : {} Cart records out of :{} total records", page.getNumberOfElements(), page.getTotalElements());
        return page.map(cartDtoMapper);
    }

    @Override
    public void deleteCart(@PathVariable(value = "id") String id) {
        cartService.deleteByRecordId(id);
    }
}
