package com.lightside.fg.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Anwar
 */

@ToString
@Getter
@Setter
@Builder
public class CreateCartResponse {
    private String userId;
    private String id;
    private Integer itemCount;
    private BigDecimal total;
    private String shipAddressId;
    private String billAddressId;
    private String paymentId;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private boolean valid;
    private Collection<ErrorResponse> errors = Collections.emptyList();
}
