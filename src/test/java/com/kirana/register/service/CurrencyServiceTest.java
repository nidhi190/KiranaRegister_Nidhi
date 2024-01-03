package com.kirana.register.service;

import com.kirana.register.exception.CustomInternalServerException;
import com.kirana.register.pojo.response.service.CurrencyExchangeApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.kirana.register.constant.service.CurrencyServiceTestConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * @author Nidhi Rani
 * @created 03/01/24 5:48 am
 */

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private WebClient webClient;

    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        currencyService = new CurrencyService(webClient);
    }

    @Test
    void exchangeCurrencySuccessTest() {

        when(webClient.get()).thenReturn(Mockito.mock(WebClient.RequestHeadersUriSpec.class));
        when(webClient.get().retrieve()).thenReturn(Mockito.mock(WebClient.ResponseSpec.class));
        when(webClient.get().retrieve().bodyToMono(CurrencyExchangeApiResponse.class)).thenReturn(Mono.just(mockCurrencyExchangeApiResponse()));
        assertEquals(mockCurrencyExchangeResponse(), currencyService.exchangeCurrency(mockCurrencyExchangeRequest()));
    }

    @Test
    void exchangeCurrencyApiErrorTest() {

        when(webClient.get()).thenReturn(Mockito.mock(WebClient.RequestHeadersUriSpec.class));
        when(webClient.get().retrieve()).thenReturn(Mockito.mock(WebClient.ResponseSpec.class));
        when(webClient.get().retrieve().bodyToMono(CurrencyExchangeApiResponse.class)).thenReturn(Mono.just(mockCurrencyExchangeApiErrorResponse()));

        assertThrows(CustomInternalServerException.class, () -> currencyService.exchangeCurrency(mockCurrencyExchangeRequest()));
    }

    @Test
    void exchangeCurrencyInvalidCurrencyTest() {

        when(webClient.get()).thenReturn(Mockito.mock(WebClient.RequestHeadersUriSpec.class));
        when(webClient.get().retrieve()).thenReturn(Mockito.mock(WebClient.ResponseSpec.class));
        when(webClient.get().retrieve().bodyToMono(CurrencyExchangeApiResponse.class)).thenReturn(Mono.just(mockCurrencyExchangeApiInvalidCurrencyResponse()));
        assertThrows(CustomInternalServerException.class, () -> currencyService.exchangeCurrency(mockCurrencyExchangeRequest()));
    }
}
