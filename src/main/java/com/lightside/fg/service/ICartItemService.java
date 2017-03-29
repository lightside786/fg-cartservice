package com.lightside.fg.service;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.domain.CartItem;

import java.util.Collection;

/**
 * @author Ummer Shervani
 */

public interface ICartItemService {
    /**
     * @param cartItem
     * @return
     */
    Cart createCartItem(final String cartId, CartItem cartItem);


    /**
     * @param cartItem
     * @return
     */
    Cart updateCartItem(final String cartId, CartItem cartItem);

    CartItem getCartItemById(Long id);

    /**
     * @param recordIdentifier
     * @return
     */
    CartItem getCartItemByRecordId(final String recordIdentifier);

    /**
     * @return
     */
    Collection<CartItem> getCartItems(final String cartRecordId);

    Cart delete(final Long id);

    Cart deleteByRecordId(final String recordId);

    Cart deleteByRecordId(final String cartId, final String recordId);

}
