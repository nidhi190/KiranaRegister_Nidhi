package com.kirana.register.service;

import com.kirana.register.pojo.request.service.GetTransactionServiceRequest;
import com.kirana.register.pojo.request.service.RecordTransactionServiceRequest;
import com.kirana.register.pojo.response.service.GetTransactionServiceResponse;
import com.kirana.register.pojo.response.service.RecordTransactionServiceResponse;
import org.springframework.stereotype.Service;

@Service
public interface ITransactionService {

    RecordTransactionServiceResponse recordTransaction(final RecordTransactionServiceRequest recordTransactionServiceRequest);

    GetTransactionServiceResponse getTransactions(final GetTransactionServiceRequest getTransactionServiceRequest);
}
