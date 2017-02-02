package com.lightside.fg.web.request.mapper;

import com.lightside.fg.domain.CartItem;
import com.lightside.fg.web.mapper.MapperAdapter;
import com.lightside.fg.web.request.CartItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Anwar
 */

@Slf4j
@Component
public class CartItemRequestMapper extends MapperAdapter<CartItemRequest, CartItem> {

    @Override
    public CartItem map(CartItemRequest cartItemRequest) {

        CartItem cartItem = CartItem.builder()
                .total(cartItemRequest.getTotal())
                .price(cartItemRequest.getPrice())
                .primaryQuantity(cartItemRequest.getPrimary().getQuantity())
                .productId(cartItemRequest.getProductId())
                .secondaryQuantity((cartItemRequest.getSecondary() !=null ?
                        cartItemRequest.getSecondary().getQuantity() : 0))
                .primaryUnitOfMeasure(cartItemRequest.getPrimary().getUnitOfMeasure())
                .secondaryUnitOfMeasure(cartItemRequest.getSecondary() !=null ?
                        cartItemRequest.getSecondary().getUnitOfMeasure():null)
                .build();

        return cartItem;
    }
}
