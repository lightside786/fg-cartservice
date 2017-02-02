package com.lightside.fg.web.response.mapper;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.web.mapper.MapperAdapter;
import com.lightside.fg.web.response.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Ummer
 */

@Component
public class CartDtoMapper extends MapperAdapter<Cart, CartDto> implements Converter<Cart, CartDto> {

    @Autowired
    CartItemResponseMapper cartItemResponseMapper;

    @Override
    public CartDto map(Cart cart) {
        CartDto cartDto = CartDto
                .builder()
                .recordId(cart.getRecordId())
                .userId(cart.getUserId())
                .total(cart.getTotal())
                .createdOn(cart.getCreatedOn())
                .updatedOn(cart.getUpdatedOn())
                .lastAccessedOn(cart.getLastAccessedOn())
                .cartItems(cartItemResponseMapper.map(cart.getCartItems()))
                .build();
        return cartDto;
    }

    @Override
    public CartDto convert(Cart Cart) {
        return map(Cart);
    }
}
