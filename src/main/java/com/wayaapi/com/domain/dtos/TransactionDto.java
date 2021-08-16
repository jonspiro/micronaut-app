package com.wayaapi.com.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties({"user"})
@Introspected
public class TransactionDto {

    @NonNull
    @NotNull
    @NotBlank
    private Long id;

    @NonNull
    @NotNull
    @NotBlank
    private UserDto user;

    @NonNull
    @NotNull
    @NotBlank
    private String currency;

    @NonNull
    @NotNull
    @NotBlank
    private String transferType;

    @NonNull
    @NotNull
    @NotBlank
    private Instant transactionDate;

    @NonNull
    @NotNull
    @NotBlank
    private String transactionId;

    @NonNull
    @NotNull
    @NotBlank
    private BigDecimal amount;

    public String getUsername() {
        return user.getUsername();
    }

}
