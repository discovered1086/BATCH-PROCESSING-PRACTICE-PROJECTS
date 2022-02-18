package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ToString(exclude = "financialAccount")
@AllArgsConstructor
@NoArgsConstructor
public class DebtPaymentDTO implements Serializable {

    private DebtType debtType;

    private String debtAccount;

    private BigDecimal creditLimit;

    private BigDecimal availableCredit;

    private FinancialAccountDTO financialAccount;

}
