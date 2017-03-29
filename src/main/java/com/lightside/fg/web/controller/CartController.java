package com.lightside.fg.web.controller;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.exception.NoRecordFoundException;
import com.lightside.fg.service.ICartService;
import com.lightside.fg.web.exception.ErrorReporter;
import com.lightside.fg.web.request.CartRequest;
import com.lightside.fg.web.request.mapper.CartRequestMapper;
import com.lightside.fg.web.response.CartDto;
import com.lightside.fg.web.response.CreateCartResponse;
import com.lightside.fg.web.response.mapper.CartDtoMapper;
import com.lightside.fg.web.response.mapper.CartResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author UmmerS
 */

@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController implements ICartController {

    private ICartService ICartService;
    private CartRequestMapper requestMapper;
    private CartResponseMapper responseMapper;
    private CartDtoMapper cartDtoMapper;
    private ErrorReporter errorReporter;

    public CartController(ICartService ICartService,
                          CartRequestMapper requestMapper,
                          CartResponseMapper responseMapper,
                          CartDtoMapper cartDtoMapper, ErrorReporter errorReporter) {
        this.ICartService = ICartService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.cartDtoMapper = cartDtoMapper;
        this.errorReporter = errorReporter;
    }

    public CreateCartResponse createCart(@RequestBody @Valid CartRequest cartRequest,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Error creating Cart : {}", cartRequest);
            return CreateCartResponse.builder()
                    .errors(errorReporter.createErrors(bindingResult))
                    .valid(false)
                    .build();
        }
        log.info("Creating Cart with details : {}", cartRequest);
        Cart cart = ICartService.createCart(requestMapper.map(cartRequest));
        log.info("Cart created with recordId : {}", cart.getId());
        return responseMapper.map(cart);
    }


    public CreateCartResponse updateCart(@PathVariable(value = "recordId") String recordId, @RequestBody @Valid CartRequest cartRequest, BindingResult bindingResult) {
        Cart cart = null;
        if (bindingResult.hasErrors()) {
            log.info("Error updating Cart : {}", cartRequest);
            return CreateCartResponse.builder()
                    .errors(errorReporter.createErrors(bindingResult))
                    .valid(false)
                    .build();
        }

        if(StringUtils.isNotBlank(recordId)){
            cart = ICartService.getCartByRecordId(recordId);
            if(cart == null){
                log.info("Cart cannot be found for update request");
                return CreateCartResponse
                        .builder()
                        .errors(errorReporter.createErrors(new ObjectError("cart.doesnotexist", "cart.doesnotexist")))
                        .build();
            }
        }
        log.info("Updating Cart with details : {}", cartRequest);
        cart =  requestMapper.map(cartRequest);
        cart.setRecordId(recordId);
        ICartService.updateCart(cart);
        log.info("Cart updated with recordId : {}", cart.getId());
        return responseMapper.map(cart);

    }

    public CartDto getCart(@PathVariable(value = "recordId") String recordId) {
        Cart cart = null;
        if (StringUtils.isNotEmpty(recordId)) {
            log.info("Retrieving Cart details for recordId : {}", recordId);
            cart = ICartService.getCartByRecordId(recordId);
            log.info("Retrieved Cart with details : {}", cart);
        }
        if (cart == null) {
            throw new NoRecordFoundException("notfound.error.key", "notfound.error.message");
        }
        return cartDtoMapper.map(cart);
    }

    public CartDto getCartByUser(@RequestParam(value = "userName") String userId){
        Cart cart = null;
        if (StringUtils.isNotEmpty(userId)) {
            log.info("Retrieving Cart details for userId : {}", userId);
            cart = ICartService.getCartByUserId(userId);
            log.info("Retrieved Cart with details : {}", cart);
        }
        if (cart == null) {
            throw new NoRecordFoundException("notfound.error.key", "notfound.error.message");
        }
        return cartDtoMapper.map(cart);
    }

    public Page<CartDto> getCarts(Pageable pageable) {
        log.info("Retrieving Cart details with paging : {}", pageable);
        Page<Cart> page = ICartService.getCarts(pageable);
        log.info("Retrieved : {} Cart records out of :{} total records", page.getNumberOfElements(), page.getTotalElements());
        return page.map(cartDtoMapper);
    }

    public void deleteCart(@PathVariable(value = "recordId") String recordId) {
        ICartService.deleteByRecordId(recordId);
    }


}
