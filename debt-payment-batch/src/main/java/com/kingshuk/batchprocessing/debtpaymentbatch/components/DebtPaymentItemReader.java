package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class DebtPaymentItemReader implements ItemReader<DebtPaymentDTO> {

    @Override
    public DebtPaymentDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
