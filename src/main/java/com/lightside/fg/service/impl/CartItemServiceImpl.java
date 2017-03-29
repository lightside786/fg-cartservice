package com.lightside.fg.service.impl;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.domain.CartItem;
import com.lightside.fg.exception.NoRecordFoundException;
import com.lightside.fg.repository.ICartItemRepository;
import com.lightside.fg.service.ICartItemService;
import com.lightside.fg.service.ICartService;
import com.lightside.fg.web.exception.ErrorReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import java.util.Collection;

/**
 * @author Ummer
 */

@Transactional
@Component
@Slf4j
public class CartItemServiceImpl implements ICartItemService {

    private ICartItemRepository cartItemRepository;

    private ICartService cartService;
    private ICartItemService cartItemService;

    private ErrorReporter errorReporter;


    public CartItemServiceImpl(ICartItemRepository cartItemRepository, ICartService cartService,
                               ErrorReporter errorReporter) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.errorReporter = errorReporter;
    }

    @Override
    public Cart createCartItem(final String cartRecordId, CartItem cartItem) {

        Cart cart = cartService.getCartByRecordId(cartRecordId);
        cartItem.setCart(cart);
        log.info("Creating Cart Item : {}", cartItem);
        cartItem = cartItemRepository.save(cartItem);
        return cartService.getCartById(cartItem.getCart().getId());
    }

    @Override
    public Cart updateCartItem(final String cartRecordId, CartItem cartItem) {

        Cart cart = cartService.getCartByRecordId(cartRecordId);
        if (cart == null) {
            log.info("Cart cannot be found for update item request");
            errorReporter.createErrors(new ObjectError("cart.doesnotexist", "cart.doesnotexist"));
        }
        CartItem existingCartItem = cartItemService.getCartItemByRecordId(cartItem.getRecordId());
        if (existingCartItem.getId() == null) {
            errorReporter.createErrors(new ObjectError("cartitem.doesnotexist", "cartitem.doesnotexist"));
        }
        cartItem.setId(existingCartItem.getId());
//        for (CartItem cartItemTemp : cart.getCartItems()) {
//            if (cartItemTemp.getRecordId().equalsIgnoreCase(cartItem.getRecordId())) {
//                cartItem = cartItemTemp;
//
//            }
//        }
        cartItem.setCart(cart);
        cartItem = cartItemRepository.save(cartItem);
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
    public Collection<CartItem> getCartItems(final String cartRecordId) {
        Cart cart = cartService.getCartByRecordId(cartRecordId);
        return cart.getCartItems();
    }

    @Override
    public Cart deleteByRecordId(final String recordIdentifier) {

        CartItem cartItem = getCartItemByRecordId(recordIdentifier);
        String cartId = "";
        if (cartItem != null) {
            cartId = cartItem.getCart().getRecordId();
            cartItemRepository.deleteByRecordId(recordIdentifier);
        }
        return cartService.getCartByRecordId(cartId);
    }


    @Override
    public Cart delete(final Long id) {
        CartItem cartItem = getCartItemById(id);
        Long cartId = Long.MIN_VALUE;
        if (cartItem != null) {
            cartId = cartItem.getCart().getId();
            cartItemRepository.delete(id);
        }
        return cartService.getCartById(cartId);
    }

    @Override
    public Cart deleteByRecordId(final String cartRecordId, final String recordIdentifier) {

        if (StringUtils.isEmpty(cartRecordId)) {
            throw new NoRecordFoundException("cart.cartId.nullorempty.key", "cart.cartId.nullorempty.message");
        }

        if (StringUtils.isEmpty(recordIdentifier)) {
            throw new NoRecordFoundException("cart.cartItemId.nullorempty.key", "cart.cartItemId.nullorempty.message");
        }

        Cart cart = cartService.getCartByRecordId(cartRecordId);
        if (cart == null) {
            errorReporter.createErrors(new ObjectError("cart.doesnotexist.key", "cart.doesnotexist.key"));
        }

        CartItem cartItem = getCartItemByRecordId(recordIdentifier);
        if (!cartRecordId.equalsIgnoreCase(cartItem.getCart().getRecordId())) {
            errorReporter.createErrors(new ObjectError("cart.cartitem.notassociated", "cart.cartitem.notassociated"));
        }
        cartItemRepository.delete(cartItem);
        return cartService.getCartById(cart.getId());
    }

}
