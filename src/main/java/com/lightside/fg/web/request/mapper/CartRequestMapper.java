package com.lightside.fg.web.request.mapper;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.web.mapper.MapperAdapter;
import com.lightside.fg.web.request.CreateCartRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Anwar
 */

@Slf4j
@Component
public class CartRequestMapper extends MapperAdapter<CreateCartRequest, Cart> {

    @Override
    public Cart map(CreateCartRequest cartRequest) {
        Cart cart = Cart.builder()
                .userId(cartRequest.getUserId())
                .shipAddressId(cartRequest.getShipAddressId())
                .billAddressId(cartRequest.getBillAddressId())
                .paymentId(cartRequest.getPaymentId())
                .itemCount(cartRequest.getItemCount() == 0? Integer.MIN_VALUE :cartRequest.getItemCount())
                .total(cartRequest.getTotal())
                .currencyCode(cartRequest.getCurrencyCode())
                .recordId(UUID.randomUUID().toString())
                .build();

        return cart;
    }
}
