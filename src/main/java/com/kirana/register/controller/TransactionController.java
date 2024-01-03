package com.kirana.register.controller;

import com.kirana.register.converter.TransactionConverter;
import com.kirana.register.payload.CustomApiResponse;
import com.kirana.register.payload.ResponseGenerator;
import com.kirana.register.pojo.request.controller.RecordTransactionRequest;
import com.kirana.register.pojo.request.service.GetTransactionServiceRequest;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.pojo.response.controller.GetTransactionResponse;
import com.kirana.register.pojo.response.controller.RecordTransactionResponse;
import com.kirana.register.pojo.response.service.GetTransactionServiceResponse;
import com.kirana.register.pojo.response.service.RecordTransactionServiceResponse;
import com.kirana.register.service.ITransactionService;
import com.kirana.register.validation.controller.TransactionControllerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kirana.register.constant.controller.TransactionControllerConstant.*;

/**
 * @author Nidhi Rani
 * @created 02/01/24 3:24 am
 */

@RestController
@RequestMapping(API_V1_TRANSACTION)
@Slf4j
public class TransactionController {

    private final ITransactionService transactionService;

    private final TransactionConverter transactionConverter;

    private final TransactionControllerValidator transactionControllerValidator;

    public TransactionController(final ITransactionService transactionService, final TransactionConverter transactionConverter, final TransactionControllerValidator transactionControllerValidator) {
        this.transactionService = transactionService;
        this.transactionConverter = transactionConverter;
        this.transactionControllerValidator = transactionControllerValidator;
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<RecordTransactionResponse>> recordTransaction(final @RequestBody RecordTransactionRequest recordTransactionRequest) {

        log.info(INCOMING_CALL_FOR_RECORD_TRANSACTION, recordTransactionRequest);
        transactionControllerValidator.validateRecordTransactionRequest(recordTransactionRequest);

        RecordTransactionServiceRequest recordTransactionServiceRequest = transactionConverter.recordTransactionControllerRequestToServiceRequest(recordTransactionRequest);
        RecordTransactionServiceResponse recordTransactionServiceResponse = transactionService.recordTransaction(recordTransactionServiceRequest);
        RecordTransactionResponse recordTransactionResponse = transactionConverter.recordTransactionServiceResponseToControllerResponse(recordTransactionServiceResponse);
        log.info(SUCCESSFULLY_COMPLETED_RECORD_TRANSACTION, recordTransactionResponse);
        return new ResponseGenerator<>(HttpStatus.CREATED, recordTransactionResponse, SUCCESSFULLY_RECORDED_TRANSACTION).getResponse();
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse<GetTransactionResponse>> getTransactions(final @RequestParam(required = false) Long startDate,
                                                                                     final @RequestParam(required = false) Long endDate,
                                                                                     final @RequestParam(required = false) Integer pageSize,
                                                                                     final @RequestParam(required = false) Integer pageIndex) {

        log.info(INCOMING_CALL_FOR_GET_TRANSACTION_CONTROLLER);
        GetTransactionServiceRequest getTransactionServiceRequest = GetTransactionServiceRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .build();
        transactionControllerValidator.validateGetTransactionRequest(getTransactionServiceRequest);

        GetTransactionServiceResponse getTransactionServiceResponse = transactionService.getTransactions(getTransactionServiceRequest);
        GetTransactionResponse getTransactionResponse = transactionConverter.getTransactionServiceResponseToControllerResponse(getTransactionServiceResponse);
        log.info(SUCCESSFULLY_COMPLETED_GET_TRANSACTION_CONTROLLER, getTransactionResponse);
        return new ResponseGenerator<>(HttpStatus.OK, getTransactionResponse).getResponse();
    }
}
