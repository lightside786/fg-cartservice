package com.lightside.fg.web.mapper;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.web.response.CartDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Anwar
 */

@Component
public class CartDtoMapper extends MapperAdapter<Cart, CartDto> implements Converter<Cart, CartDto> {


    @Override
    public CartDto map(Cart cart) {
        CartDto cartDto = CartDto
                .builder()
                .recordId(cart.getRecordId())
                .userId(cart.getUserId())
                .itemCount(cart.getItemCount())
                .total(cart.getTotal())
                .shipAddressId(cart.getShipAddressId())
                .billAddressId(cart.getBillAddressId())
                .paymentId(cart.getPaymentId())
                .createdOn(cart.getCreatedOn())
                .build();
        return cartDto;
    }

    @Override
    public CartDto convert(Cart Cart) {
        return map(Cart);
    }
}
