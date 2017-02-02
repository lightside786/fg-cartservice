package com.lightside.fg.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ummers
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequest {

    @NotEmpty(message = "userid.required")
    @Size(min = 5, max = 20, message = "userid.length")
    private String userId;

    private BigDecimal total;

    @NotEmpty(message = "cartitem.required")
    private List<CartItemRequest> cartItems;

}
