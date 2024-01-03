package com.kirana.register.service;

import com.kirana.register.converter.TransactionConverter;
import com.kirana.register.exception.CustomBadRequestException;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.repository.ITransactionCustomRepository;
import com.kirana.register.repository.ITransactionRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

import static com.kirana.register.constant.service.TransactionServiceTestConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

/**
 * @author Nidhi Rani
 * @created 03/01/24 4:45 am
 */

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private ITransactionRepository transactionRepository;

    @Mock
    private ITransactionCustomRepository transactionCustomRepository;

    @Mock
    private TransactionConverter transactionConverter;

    @Mock
    private ICurrencyService currencyService;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepository, transactionConverter, transactionCustomRepository, currencyService);
    }

    @Test
    void recordTransactionSuccessTest() {

        RecordTransactionServiceRequest mockRecordTransactionServiceRequest = mockRecordTransactionServiceRequest();
        when(currencyService.exchangeCurrency(mockCurrencyExchangeRequest())).thenReturn(mockCurrencyExchangeResponse());
        when(transactionConverter.recordTransactionServiceRequestToDao(mockRecordTransactionServiceRequest)).thenReturn(mockTransactionObj());
        when(transactionRepository.save(mockTransactionObj())).thenReturn(mockTransaction());
        assertEquals(mockRecordTransactionServiceResponse(), transactionService.recordTransaction(mockRecordTransactionServiceRequest));
    }

    @Test
    void recordTransactionExceptionTest() {

        RecordTransactionServiceRequest mockRecordTransactionServiceRequest = mockRecordTransactionServiceRequest();
        when(currencyService.exchangeCurrency(mockCurrencyExchangeRequest())).thenReturn(mockCurrencyExchangeResponse());
        when(transactionConverter.recordTransactionServiceRequestToDao(mockRecordTransactionServiceRequest)).thenReturn(mockTransactionObj());
        when(transactionRepository.save(mockTransactionObj())).thenThrow(new CustomBadRequestException(null));
        CustomBadRequestException customBadRequestException = assertThrows(CustomBadRequestException.class, () -> transactionService.recordTransaction(mockRecordTransactionServiceRequest));
        assertTrue(StringUtils.contains(customBadRequestException.getMessage(), EXCEPTION_IN_RECORD_TRANSACTION));
    }

    @Test
    void getTransactionsSuccessTest() {

        when(transactionCustomRepository.getTransactionsByFilters(mockTransactionCustomRepositoryRequest(), getMockPageable())).thenReturn(mockTransactionPages());
        when(transactionConverter.daoToDto(anyList())).thenReturn(mockTransactionDtoList());
        assertEquals(mockGetTransactionServiceResponse(), transactionService.getTransactions(mockGetTransactionServiceRequest()));
    }

    @Test
    void getTransactionEmptyListTest() {

        when(transactionCustomRepository.getTransactionsByFilters(mockTransactionCustomRepositoryRequest(), getMockPageable())).thenReturn(new PageImpl<>(new ArrayList<>()));
        CustomBadRequestException customBadRequestException = assertThrows(CustomBadRequestException.class, () -> transactionService.getTransactions(mockGetTransactionServiceRequest()));
        assertTrue(StringUtils.contains(customBadRequestException.getMessage(), NO_TRANSACTION_FOUND));
    }
}
