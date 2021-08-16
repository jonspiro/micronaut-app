package com.wayaapi.com.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wayaapi.com.domain.dtos.UserDto;
import com.wayaapi.com.enums.Errors;
import com.wayaapi.com.exception.WayaExceptions;
import com.wayaapi.com.service.AccountService;
import com.wayaapi.com.util.Util;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class AccountController {

    @Inject
    AccountService accountService;

    @Inject
    Util util;

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/register")
    public Single<HttpResponse<UserDto>> registerUser(@NotBlank UserDto userDto) throws JsonProcessingException {

        //check emptiness of cust phone
        if(userDto.getPhoneNumber().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Phone number"));
        }

        //check emptiness of cust phone
        if(userDto.getPhoneNumber() == null) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Phone number"));
        }

        //validate phone
        if(!util.phoneIsValid(userDto.getPhoneNumber())){
            throw new WayaExceptions(util.getError(Errors.PHONE_NUMBER_INVALID.getErrorCode(), Errors.PHONE_NUMBER_INVALID.getErrorMsg()));
        }

        //validate email
        if(!util.emailIsValid(userDto.getUsername())){
            throw new WayaExceptions(util.getError(Errors.EMAIL_INVALID.getErrorCode(), Errors.EMAIL_INVALID.getErrorMsg()));
        }

        //check emptiness of cust fname
        if(userDto.getFirstName().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "First Name"));
        }

        //check emptiness of cust lname
        if(userDto.getLastName().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Last Name"));
        }

        //check emptiness of cust email
        if(userDto.getUsername().trim().equals("")) {
            throw new WayaExceptions(util.getError(Errors.EMPTY_INPUT.getErrorCode(), Errors.EMPTY_INPUT.getErrorMsg() + "Email address"));
        }


        Optional<UserDto> existingUser = accountService.findUser(userDto.getUsername());

        return Single.just(existingUser
                .map(HttpResponse::badRequest)
                .orElse(HttpResponse.ok(accountService.createUser(userDto)))
        );
    }

}
