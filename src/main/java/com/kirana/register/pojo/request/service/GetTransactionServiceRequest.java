package com.kirana.register.pojo.request.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nidhi Rani
 * @created 02/01/24 11:22 am
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransactionServiceRequest {

    private Long startDate;

    private Long endDate;

    private Integer pageSize;

    private Integer pageIndex;
}
