package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FinancialAccountMapper.class)
public interface DebtPaymentBeanMapper {

    @Mapping(source = "financialAccount", target = "debtAccount")
    @Mapping(target = "debtId", ignore = true)
    @Mapping(target = "aprDetails", ignore = true)
    @Mapping(target = "statementDay", ignore = true)
    @Mapping(target = "paymentDueDay", ignore = true)
    DebtPaymentEntity convertDtoToEntity(DebtPaymentDTO paymentDTO);

    @Mapping(source = "debtAccount", target = "financialAccount")
    DebtPaymentDTO convertEntityToDto(DebtPaymentEntity paymentEntity);
}
