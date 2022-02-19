package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialAccountDTO implements Serializable {
    private String externalAccountId;
    private String accessToken;
}