package com.lightside.fg.web.request;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {

    @NotEmpty(message = "productid.required")
    @Size(min = 5, max = 32, message = "productid.length")
    private String productId;

    @Digits(integer=6, fraction=2, message="price.length")
    private BigDecimal price;

    private ItemQuantity primary;

    private ItemQuantity secondary;

}
