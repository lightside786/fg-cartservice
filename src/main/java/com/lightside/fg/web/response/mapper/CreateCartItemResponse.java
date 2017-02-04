package com.lightside.fg.web.response.mapper;

import com.lightside.fg.web.response.CartItemResponse;
import com.lightside.fg.web.response.ErrorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Ummers
 */

@ToString
@Getter
@Setter
@Builder
public class CreateCartItemResponse {
    private CartItemResponse cartItemResponse;
    private boolean valid;
    private Collection<ErrorResponse> errors = Collections.emptyList();
}
