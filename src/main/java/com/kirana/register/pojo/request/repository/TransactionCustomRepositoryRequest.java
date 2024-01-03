package com.kirana.register.pojo.request.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nidhi Rani
 * @created 02/01/24 11:43 am
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCustomRepositoryRequest {

    private Long startDate;

    private Long endDate;
}
