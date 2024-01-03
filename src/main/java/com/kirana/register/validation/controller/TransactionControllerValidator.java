package com.kirana.register.validation.controller;

import com.kirana.register.exception.CustomBadRequestException;
import com.kirana.register.pojo.request.controller.RecordTransactionRequest;
import com.kirana.register.pojo.request.service.GetTransactionServiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nidhi Rani
 * @created 03/01/24 2:08 am
 */

@Component
@Slf4j
public class TransactionControllerValidator {

    public void validateRecordTransactionRequest(final RecordTransactionRequest recordTransactionRequest) {

        List<String> missingParameters = new ArrayList<>();
        if (Objects.isNull(recordTransactionRequest.getAmount())) {
            missingParameters.add("amount");
        }
        if (Objects.isNull(recordTransactionRequest.getCurrency())) {
            missingParameters.add("currency");
        }
        if (Objects.isNull(recordTransactionRequest.getTransactionType())) {
            missingParameters.add("transactionType");
        }
        if (Objects.isNull(recordTransactionRequest.getTimestamp())) {
            missingParameters.add("timestamp");
        }
        if (!missingParameters.isEmpty()) {
            log.error("Missing required parameters: {}", missingParameters);
            throw new CustomBadRequestException("Missing required parameters: " + missingParameters);
        }
    }

    public void validateGetTransactionRequest(final GetTransactionServiceRequest getTransactionServiceRequest) {

        if (Objects.nonNull(getTransactionServiceRequest.getPageIndex()) && getTransactionServiceRequest.getPageIndex() <= 0) {
            log.error("pageIndex must be greater than zero");
            throw new CustomBadRequestException("pageIndex must be greater than zero");
        }
        if (Objects.nonNull(getTransactionServiceRequest.getPageSize()) && getTransactionServiceRequest.getPageSize() > 100) {
            log.error("Maximum limit of pageSize is 100");
            throw new CustomBadRequestException("Maximum limit of pageSize is 100");
        }
        if (Objects.nonNull(getTransactionServiceRequest.getStartDate()) && Objects.isNull(getTransactionServiceRequest.getEndDate())) {
            log.error("Missing endDate");
            throw new CustomBadRequestException("Missing endDate");
        }
        if (Objects.isNull(getTransactionServiceRequest.getStartDate()) && Objects.nonNull(getTransactionServiceRequest.getEndDate())) {
            log.error("Missing startDate");
            throw new CustomBadRequestException("Missing startDate");
        }
        if (Objects.nonNull(getTransactionServiceRequest.getStartDate()) && Objects.nonNull(getTransactionServiceRequest.getEndDate()) && getTransactionServiceRequest.getStartDate() > getTransactionServiceRequest.getEndDate()) {
            log.error("startDate cannot be greater than endDate");
            throw new CustomBadRequestException("startDate cannot be greater than endDate");
        }
    }

}
