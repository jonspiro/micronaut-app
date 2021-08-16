package com.wayaapi.com.domain;

import com.wayaapi.com.enums.CurrencyCode;
import com.wayaapi.com.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NonNull
    @NotNull
    @NotBlank
    private String wayaAccountId;

    @NonNull
    @NotNull
    @NotBlank
    private String username;

    @NonNull
    @NotNull
    @NotBlank
    private String accountNumber;

    @NonNull
    @NotNull
    @NotBlank
    private CurrencyCode currency;

    @NonNull
    @NotNull
    @NotBlank
    private TransferType transferType;

    @NonNull
    @NotNull
    @NotBlank
    private BigDecimal amount;

}
