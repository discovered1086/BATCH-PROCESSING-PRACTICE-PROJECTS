package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import com.kingshuk.batchprocessing.debtpaymentbatch.repository.FinancialAccountRepository;
import org.mapstruct.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
public class DebtPaymentBeanDecorator implements DebtPaymentBeanMapper {

    @Autowired
    @Qualifier("delegate")
    private DebtPaymentBeanMapper beanMapper;

    @Autowired
    private FinancialAccountRepository financialAccountRepository;

    @Override
    public DebtPaymentEntity convertDtoToEntity(DebtPaymentDTO paymentDTO) {
        DebtPaymentEntity debtPaymentEntity = beanMapper.convertDtoToEntity(paymentDTO);
        String externalAccountId = paymentDTO.getFinancialAccount().getExternalAccountId();
        debtPaymentEntity.setDebtAccount(financialAccountRepository.findFinancialAccountEntityByExternalAccountId(externalAccountId));
        return debtPaymentEntity;
    }

    @Override
    public DebtPaymentDTO convertEntityToDto(DebtPaymentEntity paymentEntity) {
        return beanMapper.convertEntityToDto(paymentEntity);
    }
}
