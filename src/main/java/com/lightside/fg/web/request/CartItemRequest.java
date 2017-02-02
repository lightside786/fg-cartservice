package com.lightside.fg.web.request;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {

    @NotEmpty(message = "productid.required")
    @Size(min = 5, max = 20, message = "productid.length")
    private String productId;

    @NotEmpty(message = "price.required")
    @Size(min = 1, max = 20, message = "price.length")
    private BigDecimal price;

    @NotEmpty(message = "primary.required")
    private ItemQuantity primary;

    private ItemQuantity secondary;

    private BigDecimal total;

    public BigDecimal getTotal(){
        return BigDecimal.TEN;
    }

}
