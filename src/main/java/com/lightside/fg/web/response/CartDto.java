package com.lightside.fg.web.response;


import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Anwar
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartDto {
    private String id;
    private String userId;
    private Integer itemCount;
    private BigDecimal total;
    private String shipAddressId;
    private String billAddressId;
    private String paymentId;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}
