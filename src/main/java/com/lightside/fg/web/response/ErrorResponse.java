package com.lightside.fg.web.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author Anwar
 */

@Builder
@ToString
@EqualsAndHashCode(of = "errorCode")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Comparable<ErrorResponse> {
    private String errorCode;
    private String errorMessage;

    @JsonIgnore
    public static ErrorResponse buildError(String errorCode, String errorMessage) {
        return new ErrorResponse(errorCode, errorMessage);
    }

    @JsonIgnore
    @Override
    public int compareTo(ErrorResponse o) {
        if (o == null) {
            return 1;
        }
        if (errorCode == null) {
            return -1;
        }
        return errorCode.compareTo(o.errorCode);
    }
}
