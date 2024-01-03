package com.kirana.register.pojo.response.service;

import com.kirana.register.pojo.enums.CURRENCY;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nidhi Rani
 * @created 03/01/24 3:24 am
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyExchangeResponse {

    private Double amount;

    private CURRENCY currency;
}
