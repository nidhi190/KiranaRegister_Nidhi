package com.kirana.register.pojo.request.controller;

import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.enums.TRANSACTION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nidhi Rani
 * @created 02/01/24 2:48 am
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordTransactionRequest {

    private Double amount;

    private CURRENCY currency;

    private TRANSACTION_TYPE transactionType;

    private String description;

    private Long timestamp;
}
