package com.kirana.register.repository;

import com.kirana.register.pojo.model.Transaction;
import com.kirana.register.pojo.request.repository.TransactionCustomRepositoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionCustomRepository {

    Page<Transaction> getTransactionsByFilters(final TransactionCustomRepositoryRequest transactionCustomRepositoryRequest, Pageable pageable);
}
