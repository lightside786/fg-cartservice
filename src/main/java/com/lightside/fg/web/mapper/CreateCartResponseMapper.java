package com.lightside.fg.web.mapper;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.web.response.CreateCartResponse;
import org.springframework.stereotype.Component;

/**
 * @author Anwar
 */

@Component
public class CreateCartResponseMapper extends MapperAdapter<Cart, CreateCartResponse> {

    @Override
    public CreateCartResponse map(Cart cart) {
        return CreateCartResponse
                .builder()
                .userId(cart.getUserId())
                .id(cart.getRecordId())
                .itemCount(cart.getItemCount())
                .total(cart.getTotal())
                .shipAddressId(cart.getShipAddressId())
                .billAddressId(cart.getBillAddressId())
                .paymentId(cart.getPaymentId())
                .createdOn(cart.getCreatedOn())
                .updatedOn(cart.getUpdatedOn())
                .valid(true)
                .build();
    }
}
