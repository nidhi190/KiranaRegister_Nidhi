package com.kirana.register.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kirana.register.pojo.dto.TransactionDto;
import com.kirana.register.pojo.model.Transaction;
import com.kirana.register.pojo.request.controller.RecordTransactionRequest;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.pojo.response.controller.GetTransactionResponse;
import com.kirana.register.pojo.response.controller.RecordTransactionResponse;
import com.kirana.register.pojo.response.service.GetTransactionServiceResponse;
import com.kirana.register.pojo.response.service.RecordTransactionServiceResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nidhi Rani
 * @created 02/01/24 3:14 am
 */

@Component
public class TransactionConverter {

    private final ObjectMapper mapper;

    public TransactionConverter() {

        this.mapper =  JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .build();
    }

    public Transaction recordTransactionServiceRequestToDao(final RecordTransactionServiceRequest recordTransactionServiceRequest) {
        return mapper.convertValue(recordTransactionServiceRequest, Transaction.class);
    }

    public RecordTransactionServiceRequest recordTransactionControllerRequestToServiceRequest(final RecordTransactionRequest recordTransactionRequest) {
        return mapper.convertValue(recordTransactionRequest, RecordTransactionServiceRequest.class);
    }

    public RecordTransactionResponse recordTransactionServiceResponseToControllerResponse(final RecordTransactionServiceResponse recordTransactionServiceResponse) {
        return mapper.convertValue(recordTransactionServiceResponse, RecordTransactionResponse.class);
    }

    public TransactionDto daoToDto(final Transaction transaction) {
        return mapper.convertValue(transaction, TransactionDto.class);
    }

    public List<TransactionDto> daoToDto(final List<Transaction> transactions) {
        return transactions.stream().map(this::daoToDto).toList();
    }

    public GetTransactionResponse getTransactionServiceResponseToControllerResponse(final GetTransactionServiceResponse getTransactionServiceResponse) {
        return mapper.convertValue(getTransactionServiceResponse, GetTransactionResponse.class);
    }
}
