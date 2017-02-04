package com.lightside.fg.service;

import com.lightside.fg.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Ummer Shervani
 */

public interface ICartService {
    /**
     * @param cart
     * @return
     */
    Cart createCart(Cart cart);

    /**
     * @param cart
     * @return
     */
    Cart updateCart(Cart cart);

    Cart getCartById( Long id);

    /**
     * @param recordIdentifier
     * @return
     */
    Cart getCartByRecordId(final String recordIdentifier);

    /**
     * @return
     */
    Page<Cart> getCarts(Pageable pageable);


    void delete(final Long id);

    void deleteByRecordId(final String recordId);

}
