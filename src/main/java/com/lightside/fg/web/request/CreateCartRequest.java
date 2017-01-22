package com.lightside.fg.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @author Anwar
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCartRequest {

    @NotEmpty(message = "userid.required")
    @Size(min = 5, max = 20, message = "userid.length")
    private String userId;

    @Size(min = 16, max = 36, message = "shippingaddressid.length")
    private String shipAddressId;

    @Size(min = 16, max = 36, message = "billaddressid.length")
    private String billAddressId;

    private Integer itemCount;

    private BigDecimal total;

    @Size(min = 1, max = 3, message = "currencycode.length")
    private String currencyCode;


    @Size(min = 16, max = 36, message = "paymentid.length")
    private String paymentId;

}
