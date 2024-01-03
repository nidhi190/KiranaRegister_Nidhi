package com.kirana.register.controller;

import com.kirana.register.converter.TransactionConverter;
import com.kirana.register.payload.CustomApiResponse;
import com.kirana.register.pojo.response.controller.GetTransactionResponse;
import com.kirana.register.pojo.response.controller.RecordTransactionResponse;
import com.kirana.register.service.ITransactionService;
import com.kirana.register.validation.controller.TransactionControllerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.kirana.register.constant.controller.TransactionControllerTestConstant.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Nidhi Rani
 * @created 03/01/24 6:24 am
 */

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private ITransactionService transactionService;

    @Mock
    private TransactionConverter transactionConverter;

    @Mock
    private TransactionControllerValidator transactionControllerValidator;

    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        transactionController = new TransactionController(transactionService, transactionConverter, transactionControllerValidator);
    }

    @Test
    void recordTransactionTest() {

        when(transactionConverter.recordTransactionControllerRequestToServiceRequest(mockRecordTransactionRequest())).thenReturn(mockRecordTransactionServiceRequest());
        when(transactionService.recordTransaction(mockRecordTransactionServiceRequest())).thenReturn(mockRecordTransactionServiceResponse());
        when(transactionConverter.recordTransactionServiceResponseToControllerResponse(mockRecordTransactionServiceResponse())).thenReturn(mockRecordTransactionResponse());

        ResponseEntity<CustomApiResponse<RecordTransactionResponse>> responseEntity = transactionController.recordTransaction(mockRecordTransactionRequest());

        verify(transactionControllerValidator).validateRecordTransactionRequest(mockRecordTransactionRequest());
        verify(transactionConverter).recordTransactionControllerRequestToServiceRequest(mockRecordTransactionRequest());
        verify(transactionService).recordTransaction(mockRecordTransactionServiceRequest());
        verify(transactionConverter).recordTransactionServiceResponseToControllerResponse(mockRecordTransactionServiceResponse());
    }

    @Test
    void getTransactionTest() {

        when(transactionService.getTransactions(mockGetTransactionServiceRequest())).thenReturn(mockGetTransactionServiceResponse());
        when(transactionConverter.getTransactionServiceResponseToControllerResponse(mockGetTransactionServiceResponse())).thenReturn(mockGetTransactionResponse());

        ResponseEntity<CustomApiResponse<GetTransactionResponse>> responseEntity = transactionController.getTransactions(null, null, null, null);

        verify(transactionControllerValidator).validateGetTransactionRequest(mockGetTransactionServiceRequest());
        verify(transactionService).getTransactions(mockGetTransactionServiceRequest());
        verify(transactionConverter).getTransactionServiceResponseToControllerResponse(mockGetTransactionServiceResponse());
    }
}
