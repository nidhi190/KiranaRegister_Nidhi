package com.kirana.register.service;

import com.kirana.register.pojo.request.service.CurrencyExchangeRequest;
import com.kirana.register.pojo.response.service.CurrencyExchangeResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICurrencyService {

    CurrencyExchangeResponse exchangeCurrency(final CurrencyExchangeRequest currencyExchangeRequest);
}
