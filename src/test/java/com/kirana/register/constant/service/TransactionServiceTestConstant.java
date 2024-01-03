package com.kirana.register.constant.service;

import com.kirana.register.pojo.dto.TransactionDto;
import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.enums.TRANSACTION_TYPE;
import com.kirana.register.pojo.model.Transaction;
import com.kirana.register.pojo.request.repository.TransactionCustomRepositoryRequest;
import com.kirana.register.pojo.request.service.CurrencyExchangeRequest;
import com.kirana.register.pojo.request.service.GetTransactionServiceRequest;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.pojo.response.service.CurrencyExchangeResponse;
import com.kirana.register.pojo.response.service.GetTransactionServiceResponse;
import com.kirana.register.pojo.response.service.RecordTransactionServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nidhi Rani
 * @created 03/01/24 4:49 am
 */

public class TransactionServiceTestConstant {

    public static Double AMOUNT = 11.0;
    public static Long TIMESTAMP = 123456789L;
    public static String DESCRIPTION = "Some Description";
    public static Long TRANSACTION_ID = 1L;
    public static LocalDateTime CREATED_AT = LocalDateTime.of(2023, 12, 28, 7, 0, 0);
    public static String EXCEPTION_IN_RECORD_TRANSACTION = "Exception in recordTransaction:";
    public static String NO_TRANSACTION_FOUND = "No transactions found";


    public static RecordTransactionServiceRequest mockRecordTransactionServiceRequest() {
        return RecordTransactionServiceRequest.builder()
                .amount(AMOUNT)
                .transactionType(TRANSACTION_TYPE.CREDIT)
                .currency(CURRENCY.USD)
                .timestamp(TIMESTAMP)
                .description(DESCRIPTION)
                .build();
    }

    public static Transaction mockTransactionObj() {
        return Transaction.builder()
                .amount(AMOUNT)
                .transactionType(TRANSACTION_TYPE.CREDIT)
                .currency(CURRENCY.INR)
                .timestamp(TIMESTAMP)
                .description(DESCRIPTION)
                .build();
    }

    public static Transaction mockTransaction() {
        return Transaction.builder()
                .id(TRANSACTION_ID)
                .amount(AMOUNT)
                .transactionType(TRANSACTION_TYPE.CREDIT)
                .currency(CURRENCY.INR)
                .timestamp(TIMESTAMP)
                .description(DESCRIPTION)
                .createdAt(CREATED_AT)
                .build();
    }

    public static RecordTransactionServiceResponse mockRecordTransactionServiceResponse() {
        return RecordTransactionServiceResponse.builder()
                .transactionId(TRANSACTION_ID)
                .build();
    }

    public static CurrencyExchangeRequest mockCurrencyExchangeRequest() {
        return CurrencyExchangeRequest.builder()
                .toCurrency(CURRENCY.INR)
                .fromCurrency(CURRENCY.USD)
                .amount(AMOUNT)
                .build();
    }

    public static CurrencyExchangeResponse mockCurrencyExchangeResponse() {
        return CurrencyExchangeResponse.builder()
                .currency(CURRENCY.INR)
                .amount(AMOUNT)
                .build();
    }

    public static TransactionCustomRepositoryRequest mockTransactionCustomRepositoryRequest() {
        return TransactionCustomRepositoryRequest.builder().build();
    }

    public static GetTransactionServiceRequest mockGetTransactionServiceRequest() {
        return GetTransactionServiceRequest.builder().build();
    }

    public static Pageable getMockPageable() {
        return PageRequest.of(0,50);
    }

    public static Page<Transaction> mockTransactionPages() {
        return new PageImpl<>(new ArrayList<>(List.of(mockTransaction())));
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

}
