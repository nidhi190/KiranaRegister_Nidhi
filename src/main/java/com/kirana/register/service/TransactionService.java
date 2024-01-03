package com.kirana.register.service;

import com.kirana.register.converter.TransactionConverter;
import com.kirana.register.exception.CustomBadRequestException;
import com.kirana.register.pojo.dto.TransactionDto;
import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.model.Transaction;
import com.kirana.register.pojo.request.repository.TransactionCustomRepositoryRequest;
import com.kirana.register.pojo.request.service.CurrencyExchangeRequest;
import com.kirana.register.pojo.request.service.GetTransactionServiceRequest;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.pojo.response.service.CurrencyExchangeResponse;
import com.kirana.register.pojo.response.service.GetTransactionServiceResponse;
import com.kirana.register.pojo.response.service.RecordTransactionServiceResponse;
import com.kirana.register.repository.ITransactionCustomRepository;
import com.kirana.register.repository.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.kirana.register.constant.service.TransactionServiceConstant.*;

/**
 * @author Nidhi Rani
 * @created 02/01/24 2:35 am
 */

@Service
@Slf4j
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;

    private final TransactionConverter transactionConverter;

    private final ITransactionCustomRepository transactionCustomRepository;

    private final ICurrencyService currencyService;

    @Autowired
    public TransactionService(final ITransactionRepository transactionRepository,
                              final TransactionConverter transactionConverter,
                              final ITransactionCustomRepository transactionCustomRepository,
                              final ICurrencyService currencyService) {
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
        this.transactionCustomRepository = transactionCustomRepository;
        this.currencyService = currencyService;
    }

    @Override
    public RecordTransactionServiceResponse recordTransaction(final RecordTransactionServiceRequest recordTransactionServiceRequest) {

        try {
            log.info(INCOMING_CALL_FOR_RECORD_TRANSACTION, recordTransactionServiceRequest);

            if (!recordTransactionServiceRequest.getCurrency().equals(CURRENCY.INR)) {
                CurrencyExchangeRequest currencyExchangeRequest = CurrencyExchangeRequest.builder()
                        .toCurrency(CURRENCY.INR)
                        .fromCurrency(recordTransactionServiceRequest.getCurrency())
                        .amount(recordTransactionServiceRequest.getAmount())
                        .build();
                CurrencyExchangeResponse currencyExchangeResponse = currencyService.exchangeCurrency(currencyExchangeRequest);
                recordTransactionServiceRequest.setAmount(currencyExchangeResponse.getAmount());
                recordTransactionServiceRequest.setCurrency(currencyExchangeResponse.getCurrency());
            }

            Transaction transactionObj = transactionConverter.recordTransactionServiceRequestToDao(recordTransactionServiceRequest);
            Transaction transaction = transactionRepository.save(transactionObj);

            RecordTransactionServiceResponse recordTransactionServiceResponse = RecordTransactionServiceResponse.builder()
                    .transactionId(transaction.getId())
                    .build();
            log.info(SUCCESSFULLY_COMPLETED_RECORD_TRANSACTION, recordTransactionServiceResponse);
            return recordTransactionServiceResponse;
        } catch (Exception e) {
            log.error(ERROR_IN_RECORD_TRANSACTION_SERVICE, e.toString());
            throw new CustomBadRequestException(EXCEPTION_IN_RECORD_TRANSACTION + e.getMessage());
        }
    }

    @Override
    public GetTransactionServiceResponse getTransactions(final GetTransactionServiceRequest getTransactionServiceRequest) {

        try {
            log.info(INCOMING_CALL_FOR_GET_TRANSACTION_SERVICE, getTransactionServiceRequest);
            int pageSize = Objects.isNull(getTransactionServiceRequest.getPageSize()) ? DEFAULT_PAGE_SIZE : getTransactionServiceRequest.getPageSize();
            int pageIndex = Objects.isNull(getTransactionServiceRequest.getPageIndex()) ? 1 : getTransactionServiceRequest.getPageIndex();

            TransactionCustomRepositoryRequest transactionCustomRepositoryRequest = TransactionCustomRepositoryRequest.builder()
                    .startDate(getTransactionServiceRequest.getStartDate())
                    .endDate(getTransactionServiceRequest.getEndDate())
                    .build();

            Page<Transaction> transactionPages = transactionCustomRepository.getTransactionsByFilters(transactionCustomRepositoryRequest, PageRequest.of(pageIndex-1, pageSize));

            if (transactionPages.getContent().isEmpty()) {
                log.error(NO_TRANSACTIONS_FOUND);
                throw new CustomBadRequestException(NO_TRANSACTIONS_FOUND);
            }

            List<TransactionDto> transactionDtoList = transactionConverter.daoToDto(transactionPages.getContent());

            GetTransactionServiceResponse getTransactionServiceResponse = GetTransactionServiceResponse.builder()
                    .pageIndex(pageIndex)
                    .pageSize(pageSize)
                    .totalEntries(transactionPages.getTotalElements())
                    .transactions(transactionDtoList)
                    .build();
            log.info(SUCCESSFULLY_COMPLETED_GET_TRANSACTION_SERVICE, getTransactionServiceResponse);
            return getTransactionServiceResponse;
        } catch (Exception e) {
            log.error(ERROR_IN_GET_TRANSACTION_SERVICE, e.getMessage());
            throw new CustomBadRequestException(EXCEPTION_IN_GET_TRANSACTION_SERVICE + e.getMessage());
        }
    }
}
