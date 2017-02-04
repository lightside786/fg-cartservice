package com.lightside.fg.web.response;

import com.lightside.fg.web.request.ItemQuantity;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private String recordId;

    private String productId;

    private BigDecimal price;

    private ItemQuantity primary;

    private ItemQuantity secondary;

    private BigDecimal total;

    private Timestamp createdOn;

    private Timestamp updatedOn;

    private Timestamp lastAccessedOn;


}
