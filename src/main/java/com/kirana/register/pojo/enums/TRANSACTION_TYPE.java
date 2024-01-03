package com.kirana.register.pojo.enums;

public enum TRANSACTION_TYPE {

    DEBIT("DEBIT"),
    CREDIT("CREDIT");

    private final String transactionType;

    TRANSACTION_TYPE(final String transactionType) {
        this.transactionType = transactionType;
    }
}
