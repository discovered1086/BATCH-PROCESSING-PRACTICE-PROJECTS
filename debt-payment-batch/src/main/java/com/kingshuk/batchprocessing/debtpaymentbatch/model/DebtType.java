package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum DebtType {
    CREDIT_CARD("credit card"),
    LOAN("loan"),
    MORTGAGE("mortgage");

    private final String subType;

    public static DebtType getBySubType(String subType) {
        return EnumSet.allOf(DebtType.class)
                .stream().filter(debtType -> subType.equalsIgnoreCase(debtType.getSubType()))
                .findAny().orElseThrow();
    }
}
