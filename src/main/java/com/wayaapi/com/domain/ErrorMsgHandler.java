package com.wayaapi.com.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Jacksonized
@Builder
public class ErrorMsgHandler {

    @JsonProperty("code")
    @NotNull
    @NotBlank
    private final Integer errorCode;

    @JsonProperty("message")
    @NotNull
    @NotBlank
    private final String errorMsg;
}
