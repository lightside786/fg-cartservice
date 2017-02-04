package com.lightside.fg.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Ummers
 */

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    @NotEmpty(message = "userid.required")
    @Size(min = 5, max = 20, message = "userid.length")
    private String userId;

    @NotEmpty(message = "cartitem.required")
    private List<CartItemRequest> cartItems;

}
