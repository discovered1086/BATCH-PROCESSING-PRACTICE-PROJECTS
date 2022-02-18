package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DebtPaymentBeanMapper {

    DebtPaymentEntity convertDtoToEntity(DebtPaymentDTO paymentDTO);
}
