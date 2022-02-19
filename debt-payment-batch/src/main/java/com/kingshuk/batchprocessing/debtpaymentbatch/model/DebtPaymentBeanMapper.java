package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = FinancialAccountMapper.class)
@DecoratedWith(DebtPaymentBeanDecorator.class)
public interface DebtPaymentBeanMapper {

    @Mapping(target = "debtAccount", ignore = true)
    @Mapping(target = "debtId", ignore = true)
    @Mapping(target = "aprDetails", ignore = true)
    @Mapping(target = "statementDay", ignore = true)
    @Mapping(target = "paymentDueDay", ignore = true)
    DebtPaymentEntity convertDtoToEntity(DebtPaymentDTO paymentDTO);

    @Mapping(source = "debtAccount", target = "financialAccount")
    DebtPaymentDTO convertEntityToDto(DebtPaymentEntity paymentEntity);
}
