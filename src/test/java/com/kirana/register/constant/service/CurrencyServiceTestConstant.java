package com.kirana.register.constant.service;

import com.fasterxml.jackson.databind.node.DoubleNode;
import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.request.service.CurrencyExchangeRequest;
import com.kirana.register.pojo.response.service.CurrencyExchangeApiResponse;
import com.kirana.register.pojo.response.service.CurrencyExchangeResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nidhi Rani
 * @created 03/01/24 5:48 am
 */

public class CurrencyServiceTestConstant {

    public static Double AMOUNT_IN_USD = 1.0;
    public static Double AMOUNT_IN_INR = 80.0;

    public static CurrencyExchangeApiResponse mockCurrencyExchangeApiResponse() {
        return CurrencyExchangeApiResponse.builder()
                .rates(Map.of("USD", 1.0, "INR", 80.0))
                .success(true)
                .build();
    }

    public static CurrencyExchangeApiResponse mockCurrencyExchangeApiErrorResponse() {
        return CurrencyExchangeApiResponse.builder()
                .success(false)
                .build();
    }

    public static CurrencyExchangeApiResponse mockCurrencyExchangeApiInvalidCurrencyResponse() {
        return CurrencyExchangeApiResponse.builder()
                .rates(Map.of("US", 1.0, "INR", 80.0))
                .success(true)
                .build();
    }

    public static CurrencyExchangeRequest mockCurrencyExchangeRequest() {
        return CurrencyExchangeRequest.builder()
                .toCurrency(CURRENCY.INR)
                .fromCurrency(CURRENCY.USD)
                .amount(AMOUNT_IN_USD)
                .build();
    }

    public static CurrencyExchangeResponse mockCurrencyExchangeResponse() {
        return CurrencyExchangeResponse.builder()
                .currency(CURRENCY.INR)
                .amount(AMOUNT_IN_INR)
                .build();
    }
}
