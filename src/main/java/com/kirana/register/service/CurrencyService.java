package com.kirana.register.service;

import com.kirana.register.exception.CustomBadRequestException;
import com.kirana.register.exception.CustomInternalServerException;
import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.request.service.CurrencyExchangeRequest;
import com.kirana.register.pojo.response.service.CurrencyExchangeApiResponse;
import com.kirana.register.pojo.response.service.CurrencyExchangeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

import static com.kirana.register.constant.service.CurrencyServiceConstant.*;

/**
 * @author Nidhi Rani
 * @created 03/01/24 3:27 am
 */

@Service
@Slf4j
public class CurrencyService implements ICurrencyService {

    private final WebClient webClient;

    @Autowired
    public CurrencyService(final WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public CurrencyExchangeResponse exchangeCurrency(final CurrencyExchangeRequest currencyExchangeRequest) {

        log.info(INCOMING_CALL_FOR_EXCHANGE_CURRENCY, currencyExchangeRequest);
        try {
            CurrencyExchangeApiResponse currencyExchangeApiResponse = webClient.get()
                    .retrieve()
                    .bodyToMono(CurrencyExchangeApiResponse.class)
                    .block();

            if (Objects.isNull(currencyExchangeApiResponse) || !currencyExchangeApiResponse.isSuccess() || Objects.isNull(currencyExchangeApiResponse.getRates())) {
                throw new CustomInternalServerException(EXCEPTION_IN_CURRENCY_EXCHANGE_API);
            }

            Map<String, Double> rates = currencyExchangeApiResponse.getRates();
            CURRENCY toCurrency = currencyExchangeRequest.getToCurrency();
            CURRENCY fromCurrency = currencyExchangeRequest.getFromCurrency();
            Double amount = currencyExchangeRequest.getAmount();
            if (!rates.containsKey(toCurrency.name()) || !rates.containsKey(fromCurrency.name())) {
                log.error(INVALID_CURRENCY);
                throw new CustomBadRequestException(INVALID_CURRENCY);
            }

            Double toValue = rates.get(toCurrency.name());
            Double fromValue = rates.get(fromCurrency.name());
            Double exchangedValue = amount * toValue / fromValue;

            CurrencyExchangeResponse currencyExchangeResponse = CurrencyExchangeResponse.builder().currency(currencyExchangeRequest.getToCurrency()).amount(exchangedValue).build();
            log.info(SUCCESSFULLY_COMPLETED_EXCHANGE_CURRENCY, currencyExchangeResponse);
            return currencyExchangeResponse;
        } catch (Exception e) {
            log.error(ERROR_IN_EXCHANGE_CURRENCY, e.toString());
            throw new CustomInternalServerException(EXCEPTION_IN_EXCHANGE_CURRENCY + e.getMessage());
        }
    }
}
