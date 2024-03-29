package com.lightside.fg.web.response;

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
public class CreateCartResponse {
    private CartDto cart;
    private boolean valid;
    private Collection<ErrorResponse> errors = Collections.emptyList();
}
