package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentBeanMapper;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentEntity;
import com.kingshuk.batchprocessing.debtpaymentbatch.repository.DebtPaymentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;



public class DebtPaymentItemWriter implements ItemWriter<DebtPaymentDTO> {

    @Autowired
    private DebtPaymentRepository debtPaymentRepository;

    @Autowired
    private DebtPaymentBeanMapper beanMapper;

    @Override
    public void write(List<? extends DebtPaymentDTO> items) throws Exception {
        List<DebtPaymentEntity> paymentEntities = items.stream()
                .map(beanMapper::convertDtoToEntity)
                .collect(Collectors.toList());

        debtPaymentRepository.saveAllAndFlush(paymentEntities);
    }
}
