package com.kingshuk.batchprocessing.debtpaymentbatch.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pending_debt_payment")
@Getter
@Setter
@ToString(exclude = "debtAccount")
@RequiredArgsConstructor
public class DebtPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id", nullable = false)
    private Long debtId;

    @Enumerated(EnumType.STRING)
    @Column(name = "debt_type")
    private DebtType debtType;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = FinancialAccountEntity.class)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private FinancialAccountEntity debtAccount;

    @Column(name = "credit_limit", columnDefinition = "NUMBER(10,2)")
    private BigDecimal creditLimit;

    @Column(name = "available_credit", columnDefinition = "NUMBER(10,2)")
    private BigDecimal availableCredit;

    @Column(name = "apr_details")
    private String aprDetails;

    @Column(name = "stmnt_day")
    private int statementDay;

    @Column(name = "pymnt_due_day")
    private int paymentDueDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DebtPaymentEntity that = (DebtPaymentEntity) o;
        return debtId != null && Objects.equals(debtId, that.debtId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
