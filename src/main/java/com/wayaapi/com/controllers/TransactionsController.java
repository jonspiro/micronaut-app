package com.wayaapi.com.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wayaapi.com.domain.TransactionRequest;
import com.wayaapi.com.domain.dtos.TransactionDto;
import com.wayaapi.com.enums.CurrencyCode;
import com.wayaapi.com.enums.Errors;
import com.wayaapi.com.enums.TransferType;
import com.wayaapi.com.exception.WayaExceptions;
import com.wayaapi.com.service.AccountService;
import com.wayaapi.com.service.TransactionService;
import com.wayaapi.com.util.Util;

import io.micronaut.core.annotation.Nullable;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import io.reactivex.Single;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import static io.micronaut.http.HttpStatus.FORBIDDEN;
import static io.micronaut.http.HttpResponseFactory.INSTANCE;

@Controller("/transactions")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TransactionsController {

    @Inject
    private TransactionService transactionService;

    @Inject
    private AccountService accountService;

    @Inject
    private Util util;

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/deposit")
    public Single<HttpResponse<TransactionDto>> transactionResponse(@Nullable Principal principal, @NotBlank @Body TransactionRequest transactionRequest) throws JsonProcessingException {

        //validate transfer type
        if(!transactionRequest.getTransferType().equals(TransferType.DEPOSIT)) {
            throw new WayaExceptions(util.getError(Errors.TRANSFER_TYPE_ERROR.getErrorCode(), Errors.TRANSFER_TYPE_ERROR.getErrorMsg()));
        }

        //validate currency code
        if(!transactionRequest.getCurrency().equals(CurrencyCode.GHS) && !transactionRequest.getCurrency().equals(CurrencyCode.USD)
         && !transactionRequest.getCurrency().equals(CurrencyCode.NGN) && !transactionRequest.getCurrency().equals(CurrencyCode.KES)) {
            throw new WayaExceptions(util.getError(Errors.CURRENCY_ERROR.getErrorCode(), Errors.CURRENCY_ERROR.getErrorMsg() + transactionRequest.getCurrency()));
        }

        //validate amt
        if(transactionRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new WayaExceptions(util.getError(Errors.INVALID_AMT.getErrorCode(), Errors.INVALID_AMT.getErrorMsg()));
        }

        //check emptiness of cust name
        if( transactionRequest.getUsername().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "First & Last Name"));
        }

        //check emptiness of cust acct number
        if( transactionRequest.getAccountNumber().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Account number"));
        }

        //check emptiness of amt
        if(transactionRequest.getAmount().toString().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Amount"));
        }

        //check emptiness of currency
        if(transactionRequest.getCurrency().toString().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Currency Code"));
        }

        //check emptiness of transfer type
        if(transactionRequest.getTransferType().toString().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Transfer Type"));
        }

        //check nullness of cust email
        if( transactionRequest.getUsername().trim().equals(null)) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Email address"));
        }

        //check nullness of cust acct number
        if(transactionRequest.getAccountNumber().trim().equals(null)) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Account number"));
        }

        //check nullness of amt
        if(transactionRequest.getAmount().toString().trim().equals(null)) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Amount"));
        }

        //check nullness of currency
        if(transactionRequest.getCurrency().toString().trim().equals(null)) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Currency"));
        }

        //check nullness of currency
        if(transactionRequest.getTransferType().toString().trim().equals(null)) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Transfer Type"));
        }

        //validate acct num
        if(!util.isAccountValid(transactionRequest.getAccountNumber())) {
            throw new WayaExceptions(util.getError(Errors.ACCT_NUM_ERR.getErrorCode(), transactionRequest.getAccountNumber() + Errors.ACCT_NUM_ERR.getErrorMsg()));
        }


        return Single.just(
                accountService.findUser(principal.getName()).map(user ->
                                transactionService.create(transactionRequest, user.getUsername())
                                        .map(transaction -> HttpResponse.created(transaction))
                                        .orElse(INSTANCE.status(FORBIDDEN))
                        )
                        .orElse(HttpResponse.unauthorized())
        );
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/all/")
    public  Single<HttpResponse<List<TransactionDto>>> getMessages(@Nullable Principal principal) {

        return Single.just(
                accountService.findUser(principal.getName()).map(user ->
                                HttpResponse.ok(transactionService.getUserTransactions(user.getUsername()))
                        )
                        .orElse(HttpResponse.unauthorized())
        );
    }


}
