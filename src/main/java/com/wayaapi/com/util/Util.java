package com.wayaapi.com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayaapi.com.domain.ErrorMsgHandler;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class Util {

    public Util(){

    }

    public String getError(Integer errorCode, String errorMsg) throws JsonProcessingException {

        ErrorMsgHandler errorMsgHandler = ErrorMsgHandler.builder()
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();

        String payload = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(errorMsgHandler);

        return payload;
    }

    //validate phone
    public boolean phoneIsValid(String phoneNumber) {

        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    //validate name
    public boolean isNameValid(String name) {
        return name.matches("^[\\p{L} .'-]+$");
    }

    //validate acc
    public boolean isAccountValid(String accountNumber) {
        return accountNumber.matches("[0-9]+");
    }

    //validate email
    public boolean emailIsValid(String email){

        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches();
    }

    //generate uid
    public String generateUid() {
        return UUID.randomUUID().toString();
    }

    //generate acc number
    public String generateAcctNumber() {

        Random random = new Random();
        char[] digits = new char[25];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 25; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    //find 76.65% of transf amt
    public BigDecimal findAmountPerc(BigDecimal amt){
        return amt.multiply(new BigDecimal("0.7665"));
    }
}
