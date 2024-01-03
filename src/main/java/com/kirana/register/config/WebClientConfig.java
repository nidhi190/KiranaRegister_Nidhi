package com.kirana.register.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * @author Nidhi Rani
 * @created 03/01/24 3:13 am
 */

@Configuration
public class WebClientConfig {

    private final String CURRENCY_CONVERSION_API_URL;

    public WebClientConfig(final @Value("${currency.conversion.api_url}") String CURRENCY_CONVERSION_API_URL) {
        this.CURRENCY_CONVERSION_API_URL = CURRENCY_CONVERSION_API_URL;
    }

    @Bean
    public WebClient getSearchWebClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true).wiretap(true)))
                .baseUrl(CURRENCY_CONVERSION_API_URL)
                .build();
    }
}
