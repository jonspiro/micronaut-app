package com.wayaapi.com.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import io.micronaut.core.annotation.Introspected;

@Introspected
public enum CurrencyCode {
    GHS("GHS"),
    NGN("NGN"),
    KES("KES"),
    USD("USD");

    private String currencyCode;

    CurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode(){
        return currencyCode;
    }

    @JsonCreator
    public static CurrencyCode fromCurrencyCode(String currencyCode){
        for(CurrencyCode a : CurrencyCode.values()){
            if(a.getCurrencyCode().equals(currencyCode)){
                return a;
            }
        }
        throw new IllegalArgumentException();
    }
}

