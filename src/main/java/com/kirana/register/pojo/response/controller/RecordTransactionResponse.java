package com.kirana.register.pojo.response.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nidhi Rani
 * @created 02/01/24 2:50 am
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordTransactionResponse {

    private Long transactionId;
}
