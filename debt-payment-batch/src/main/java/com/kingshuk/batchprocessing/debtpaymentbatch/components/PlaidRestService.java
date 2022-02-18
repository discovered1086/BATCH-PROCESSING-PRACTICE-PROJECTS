package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.FinancialAccountDTO;

import java.util.List;

public interface PlaidRestService {

    List<DebtPaymentDTO> getDebtPaymentDetails(List<FinancialAccountDTO> financialAccountDTOS);
}
