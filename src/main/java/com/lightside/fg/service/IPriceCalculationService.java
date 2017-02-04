package com.lightside.fg.service;

import com.lightside.fg.web.request.ItemQuantity;

import java.math.BigDecimal;

/**
 * Created by ummers on 01/02/17.
 * All Rights reserved.
 */
public interface IPriceCalculationService {

    BigDecimal calculatePrice(ItemQuantity primaryQuantity, ItemQuantity secondaryQuantity );


}
