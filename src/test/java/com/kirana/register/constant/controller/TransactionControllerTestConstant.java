package com.kirana.register.constant.controller;

import com.kirana.register.pojo.dto.TransactionDto;
import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.enums.TRANSACTION_TYPE;
import com.kirana.register.pojo.request.controller.RecordTransactionRequest;
import com.kirana.register.pojo.request.service.GetTransactionServiceRequest;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.pojo.response.controller.GetTransactionResponse;
import com.kirana.register.pojo.response.controller.RecordTransactionResponse;
import com.kirana.register.pojo.response.service.GetTransactionServiceResponse;
import com.kirana.register.pojo.response.service.RecordTransactionServiceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nidhi Rani
 * @created 03/01/24 6:23 am
 */

public class TransactionControllerTestConstant {

    public static Double AMOUNT = 11.0;
    public static Long TIMESTAMP = 123456789L;
    public static String DESCRIPTION = "Some Description";
    public static Long TRANSACTION_ID = 1L;

    public static RecordTransactionRequest mockRecordTransactionRequest() {
        return RecordTransactionRequest.builder()
                .amount(AMOUNT)
                .transactionType(TRANSACTION_TYPE.CREDIT)
                .currency(CURRENCY.USD)
                .timestamp(TIMESTAMP)
                .description(DESCRIPTION)
                .build();
    }

    public static RecordTransactionServiceRequest mockRecordTransactionServiceRequest() {
        return RecordTransactionServiceRequest.builder()
                .amount(AMOUNT)
                .transactionType(TRANSACTION_TYPE.CREDIT)
                .currency(CURRENCY.USD)
                .timestamp(TIMESTAMP)
                .description(DESCRIPTION)
                .build();
    }

    public static RecordTransactionServiceResponse mockRecordTransactionServiceResponse() {
        return RecordTransactionServiceResponse.builder()
                .transactionId(TRANSACTION_ID)
                .build();
    }

    public static RecordTransactionResponse mockRecordTransactionResponse() {
        return RecordTransactionResponse.builder()
                .transactionId(TRANSACTION_ID)
                .build();
    }

    public static GetTransactionServiceRequest mockGetTransactionServiceRequest() {
        return GetTransactionServiceRequest.builder().build();
    }

    public static TransactionDto mockTransactionDto() {
        return TransactionDto.builder()
                .id(TRANSACTION_ID)
                .amount(AMOUNT)
                .transactionType(TRANSACTION_TYPE.CREDIT)
                .currency(CURRENCY.INR)
                .timestamp(TIMESTAMP)
                .description(DESCRIPTION)
                .build();
    }

    public static List<TransactionDto> mockTransactionDtoList() {
        return new ArrayList<>(List.of(mockTransactionDto()));
    }

    public static GetTransactionServiceResponse mockGetTransactionServiceResponse() {
        return GetTransactionServiceResponse.builder()
                .transactions(mockTransactionDtoList())
                .pageSize(50)
                .pageIndex(1)
                .totalEntries(1L)
                .build();
    }

    public static GetTransactionResponse mockGetTransactionResponse() {
        return GetTransactionResponse.builder()
                .transactions(mockTransactionDtoList())
                .pageSize(50)
                .pageIndex(1)
                .totalEntries(1L)
                .build();
    }
}
