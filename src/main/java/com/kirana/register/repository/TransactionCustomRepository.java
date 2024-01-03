package com.kirana.register.repository;

import com.kirana.register.pojo.model.Transaction;
import com.kirana.register.pojo.request.repository.TransactionCustomRepositoryRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nidhi Rani
 * @created 02/01/24 11:46 am
 */

@Repository
public class TransactionCustomRepository implements ITransactionCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Transaction> getTransactionsByFilters(final TransactionCustomRepositoryRequest transactionCustomRepositoryRequest, final Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = criteriaQuery.from(Transaction.class);

        List<Predicate> predicates = new ArrayList<>();

        // Timestamp filter
        if (Objects.nonNull(transactionCustomRepositoryRequest.getStartDate()) && Objects.nonNull(transactionCustomRepositoryRequest.getEndDate())) {
            Path<Long> timestampPath = root.get("timestamp");
            predicates.add(criteriaBuilder.between(timestampPath, transactionCustomRepositoryRequest.getStartDate(), transactionCustomRepositoryRequest.getEndDate()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Transaction> countRoot = countQuery.from(Transaction.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        if (Objects.nonNull(transactionCustomRepositoryRequest.getStartDate()) && Objects.nonNull(transactionCustomRepositoryRequest.getEndDate())) {
            Path<Long> timestampPath = countRoot.get("timestamp");
            countQuery.where(criteriaBuilder.between(timestampPath, transactionCustomRepositoryRequest.getStartDate(), transactionCustomRepositoryRequest.getEndDate()));
        }

        Long totalEntries = entityManager.createQuery(countQuery).getSingleResult();

        List<Transaction> results = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(results, pageable, totalEntries);
    }
}
