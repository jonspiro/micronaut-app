package com.wayaapi.com.enums;

import lombok.Getter;

@Getter
public enum Errors {

    INVALID_AMT(12, "Invalid amount, amount must be greater than 0"),
    EMPTY_INPUT(13, "This field cannot be blank: "),
    TRANSFER_TYPE_ERROR(14, "Transfer type not allowed:"),
    EMAIL_INVALID(15, "Email address is invalid. Email must be in this format. e.g a@z.com"),
    PHONE_NUMBER_INVALID(164, "Phone number is invalid, Phone number must be in this format e.g +233247252255"),
    CURRENCY_ERROR(149, "Currency code is invalid: "),
    ACCT_NUM_ERR(132, " is invalid, the account number must contain numbers only"),
    NAME_ERROR(176, " is invalid, name must contain alphabets only"),
    TOKEN_NOT_FOUND(536, "Token not found"),
    ACCOUNT_ALREADY(2301, "Account already exist. Please login");

    private final Integer errorCode;
    private final String errorMsg;

    private Errors(Integer errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
