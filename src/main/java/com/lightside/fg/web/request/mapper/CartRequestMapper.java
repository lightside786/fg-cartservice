package com.lightside.fg.web.request.mapper;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.domain.CartItem;
import com.lightside.fg.web.mapper.MapperAdapter;
import com.lightside.fg.web.request.CartItemRequest;
import com.lightside.fg.web.request.CartRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Anwar
 */

@Slf4j
@Component
public class CartRequestMapper extends MapperAdapter<CartRequest, Cart> {

    @Autowired
    CartItemRequestMapper cartItemRequestMapper;

    @Override
    public Cart map(CartRequest cartRequest) {
        CartItem cartItem = null;
        Cart cart = Cart.builder()
                .userId(cartRequest.getUserId())
                .total(cartRequest.getTotal())
                .build();
        for (CartItemRequest cartRequestItem : cartRequest.getCartItems()) {
            cartItem = cartItemRequestMapper.map(cartRequestItem);
            cartItem.setCart(cart);
            cart.addCartItem(cartItem);
        }

        return cart;
    }
}
