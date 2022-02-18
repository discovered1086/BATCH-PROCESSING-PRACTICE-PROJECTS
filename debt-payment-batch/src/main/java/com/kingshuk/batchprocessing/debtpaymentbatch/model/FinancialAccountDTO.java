package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialAccountDTO implements Serializable {
    private String externalAccountId;
    private String accessToken;
}