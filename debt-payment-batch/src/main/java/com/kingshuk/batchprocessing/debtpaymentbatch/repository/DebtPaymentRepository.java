package com.kingshuk.batchprocessing.debtpaymentbatch.repository;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtPaymentRepository extends JpaRepository<DebtPaymentEntity, Long> {
}
