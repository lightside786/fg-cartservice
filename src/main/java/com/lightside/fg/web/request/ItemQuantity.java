package com.lightside.fg.web.request;

import com.lightside.fg.domain.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;

/**
 * Created by ummers on 25/01/17.
 * All Rights reserved.
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ItemQuantity {

    @Digits(integer=4, fraction=2, message="quantity.length")
    private Double quantity;

    @NotEmpty(message = "unitofmeasure.required")
    private UnitOfMeasure unitOfMeasure;

}
