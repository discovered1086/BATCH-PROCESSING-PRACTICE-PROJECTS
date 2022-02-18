package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentBeanMapper;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentEntity;
import com.kingshuk.batchprocessing.debtpaymentbatch.repository.DebtPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DebtPaymentItemWriter implements ItemWriter<DebtPaymentDTO> {

    private final DebtPaymentRepository debtPaymentRepository;

    private final DebtPaymentBeanMapper beanMapper;

    @Override
    public void write(List<? extends DebtPaymentDTO> items) throws Exception {
        List<DebtPaymentEntity> paymentEntities = items.stream()
                .map(beanMapper::convertDtoToEntity)
                .collect(Collectors.toList());

        debtPaymentRepository.saveAllAndFlush(paymentEntities);
    }
}
