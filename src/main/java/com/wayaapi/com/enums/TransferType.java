package com.wayaapi.com.enums;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransferType {
    @JsonProperty("Withdrawal")
    WITHDRAWAL,
    @JsonProperty("Deposit")
    DEPOSIT,
    @JsonProperty("Transfer")
    TRANSFER;
}
