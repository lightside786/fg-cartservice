package com.lightside.fg.web.response.mapper;

import com.lightside.fg.domain.CartItem;
import com.lightside.fg.web.mapper.MapperAdapter;
import com.lightside.fg.web.request.ItemQuantity;
import com.lightside.fg.web.response.CartItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Ummers
 */

@Slf4j
@Component
public class CartItemResponseMapper extends MapperAdapter<CartItem, CartItemResponse> implements Converter<CartItem, CartItemResponse> {

    @Override
    public CartItemResponse map(CartItem cartItem) {

        CartItemResponse cartItemResponse = CartItemResponse.builder()
                .recordId(cartItem.getRecordId())
                .total(cartItem.getTotal())
                .price(cartItem.getPrice())
                .primary(ItemQuantity.builder().quantity(cartItem.getPrimaryQuantity())
                        .unitOfMeasure(cartItem.getPrimaryUnitOfMeasure())
                        .build())
                .secondary(ItemQuantity.builder().quantity(cartItem.getSecondaryQuantity())
                        .unitOfMeasure(cartItem.getSecondaryUnitOfMeasure())
                        .build())
                .createdOn(cartItem.getCreatedOn())
                .updatedOn(cartItem.getUpdatedOn())
                .lastAccessedOn(cartItem.getLastAccessedOn())
                .build();

        return cartItemResponse;
    }

    @Override
    public CartItemResponse convert(CartItem source) {
        return map(source);
    }
}
