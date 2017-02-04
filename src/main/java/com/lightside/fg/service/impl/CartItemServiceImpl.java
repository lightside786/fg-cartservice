package com.lightside.fg.service.impl;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.domain.CartItem;
import com.lightside.fg.repository.ICartItemRepository;
import com.lightside.fg.service.ICartItemService;
import com.lightside.fg.service.ICartService;
import com.lightside.fg.web.exception.ErrorReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

/**
 * @author Ummer
 */

@Transactional
@Component
@Slf4j
public class CartItemServiceImpl implements ICartItemService {

    private ICartItemRepository cartItemRepository;

    private ICartService cartService;

    private ErrorReporter errorReporter;


    public CartItemServiceImpl(ICartItemRepository cartItemRepository ,ICartService cartService,
                               ErrorReporter errorReporter) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.errorReporter = errorReporter;
    }

    @Override
    public Cart createCartItem(CartItem cartItem) {
        log.info("Creating Cart : {}", cartItem);
        cartItem =  cartItemRepository.save(cartItem);
        return cartService.getCartById(cartItem.getCart().getId());
    }

    @Override
    public Cart updateCartItem(CartItem cartItem) {
        if (cartItem.getId() == null || cartItem.getId() <= 0) {
            errorReporter.createErrors(new ObjectError("cart.doesnotexist", "cart.doesnotexist"));
        }
        cartItem =  cartItemRepository.save(cartItem);
        return cartService.getCartById(cartItem.getCart().getId());
    }

    @Override
    public CartItem getCartItemByRecordId(final String recordIdentifier) {
        return cartItemRepository.findByRecordId(recordIdentifier);
    }

    @Override
    public CartItem getCartItemById(final Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public Page<CartItem> getCartItems(Pageable pageable) {
        return cartItemRepository.findAll(pageable);
    }

    @Override
    public Cart delete(final Long id) {
        CartItem cartItem =  getCartItemById(id);
        Long cartId = Long.MAX_VALUE;
        if(cartItem != null){
            cartId = cartItem.getCart().getId();
            cartItemRepository.delete(id);
        }
        return cartService.getCartById(cartId);
    }

    @Override
    public Cart deleteByRecordId(final String recordIdentifier) {

        CartItem cartItem =  getCartItemByRecordId(recordIdentifier);
        String cartId = "";
        if(cartItem != null){
            cartId = cartItem.getCart().getRecordId();
            cartItemRepository.deleteByRecordId(recordIdentifier);
        }
        return cartService.getCartByRecordId(cartId);
    }


}
