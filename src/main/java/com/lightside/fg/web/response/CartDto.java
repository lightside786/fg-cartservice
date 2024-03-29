package com.lightside.fg.web.response;


import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author Anwar
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartDto {
    private String recordId;
    private String userId;
    private BigDecimal total;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Timestamp lastAccessedOn;
    private Collection<CartItemResponse> cartItems;
}
