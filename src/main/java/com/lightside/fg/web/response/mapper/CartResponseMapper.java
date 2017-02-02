package com.lightside.fg.web.response.mapper;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.web.mapper.MapperAdapter;
import com.lightside.fg.web.response.CreateCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author UmmerS
 */

@Component
public class CartResponseMapper extends MapperAdapter<Cart, CreateCartResponse> {

    @Autowired
    CartItemResponseMapper cartItemResponseMapper;

    @Autowired
    CartDtoMapper cartDtoMapper;

    @Override
    public CreateCartResponse map(Cart cart) {
        return CreateCartResponse
                .builder()
                .cart(cartDtoMapper.map(cart))
                .valid(true)
                .build();

    }
}
