package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebtPaymentItemWriter implements ItemWriter<DebtPaymentDTO> {
    @Override
    public void write(List<? extends DebtPaymentDTO> items) throws Exception {

    }
}
