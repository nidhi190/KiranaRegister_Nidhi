package com.kirana.register.pojo.dto;

import com.kirana.register.pojo.enums.CURRENCY;
import com.kirana.register.pojo.enums.TRANSACTION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nidhi Rani
 * @created 02/01/24 11:23 am
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Long id;

    private Double amount;

    private CURRENCY currency;

    private TRANSACTION_TYPE transactionType;

    private String description;

    private Long timestamp;
}
