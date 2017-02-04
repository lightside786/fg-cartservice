package com.lightside.fg.service;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.domain.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Ummer Shervani
 */

public interface ICartItemService {
    /**
     * @param cartItem
     * @return
     */
    Cart createCartItem(CartItem cartItem);


    /**
     * @param cartItem
     * @return
     */
    Cart updateCartItem(CartItem cartItem);

    CartItem getCartItemById(Long id);

    /**
     * @param recordIdentifier
     * @return
     */
    CartItem getCartItemByRecordId(final String recordIdentifier);

    /**
     * @return
     */
    Page<CartItem> getCartItems(Pageable pageable);


    Cart delete(final Long id);

    Cart deleteByRecordId(final String recordId);


}
