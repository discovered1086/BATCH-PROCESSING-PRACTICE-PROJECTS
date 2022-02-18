package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.FinancialAccountDTO;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.FinancialAccountEntity;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.FinancialAccountMapper;
import com.kingshuk.batchprocessing.debtpaymentbatch.repository.FinancialAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DebtPaymentItemReader implements ItemReader<List<DebtPaymentDTO>> {

    private final FinancialAccountRepository financialAccountRepository;

    private final FinancialAccountMapper accountMapper;

    private final PlaidRestService plaidRestService;

    @Override
    public List<DebtPaymentDTO> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<FinancialAccountEntity> accountEntities = financialAccountRepository.findAll();
        List<FinancialAccountDTO> accountDTOS = accountEntities.stream()
                .map(accountMapper::convertEntityToDto)
                .collect(Collectors.toList());
        return plaidRestService.getDebtPaymentDetails(accountDTOS);
    }
}
