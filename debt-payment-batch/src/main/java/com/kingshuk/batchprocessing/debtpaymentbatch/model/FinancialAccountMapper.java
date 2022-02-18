package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialAccountMapper {

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "accountName", ignore = true)
    FinancialAccountEntity convertDtoToEntity(FinancialAccountDTO paymentDTO);

    FinancialAccountDTO convertEntityToDto(FinancialAccountEntity paymentEntity);
}
