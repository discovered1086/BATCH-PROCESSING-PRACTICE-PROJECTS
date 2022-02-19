package com.kingshuk.batchprocessing.debtpaymentbatch.components;

import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtPaymentDTO;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.DebtType;
import com.kingshuk.batchprocessing.debtpaymentbatch.model.FinancialAccountDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlaidRestServiceImpl implements PlaidRestService {

    @Value("${registration.clientId}")
    private String clientId;

    @Value("${registration.clientSecret}")
    private String clientSecret;

    @Value("${registration.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<DebtPaymentDTO> getDebtPaymentDetails(List<FinancialAccountDTO> financialAccountDTOS) {
        List<DebtPaymentDTO> debtPaymentDTOS = new ArrayList<>();
        for (FinancialAccountDTO financialAccountDTO : financialAccountDTOS) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("client_id", clientId);
            requestBody.put("secret", clientSecret);
            requestBody.put("access_token", financialAccountDTO.getAccessToken());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            if (Objects.nonNull(responseEntity.getBody())) {
                String responseBody = responseEntity.getBody();
                JSONObject jsonObject = new JSONObject(responseBody);
                JSONObject theAccount = (JSONObject) jsonObject.getJSONArray("accounts").get(0);
                String subtype = theAccount.getString("subtype");
                JSONObject balances = theAccount.getJSONObject("balances");
                DebtPaymentDTO debtPaymentDTO = DebtPaymentDTO.builder()
                        .financialAccount(financialAccountDTO)
                        .debtType(DebtType.getBySubType(subtype))
                        .availableCredit(BigDecimal.valueOf(balances.getDouble("available")))
                        .creditLimit(BigDecimal.valueOf(balances.getDouble("limit")))
                        .build();
                debtPaymentDTOS.add(debtPaymentDTO);
            }
        }

        return debtPaymentDTOS;
    }
}
