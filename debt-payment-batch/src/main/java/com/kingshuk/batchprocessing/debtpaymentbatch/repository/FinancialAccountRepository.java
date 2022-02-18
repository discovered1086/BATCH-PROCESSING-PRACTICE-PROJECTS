package com.kingshuk.batchprocessing.debtpaymentbatch.repository;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.FinancialAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialAccountRepository extends JpaRepository<FinancialAccountEntity, Long> {
}
