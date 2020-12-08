package com.kingshuk.spring.springschedulerasyncevents.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class CommonDatabaseConfig {

    //@Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager(
            @Qualifier("h2TransactionManager") PlatformTransactionManager h2TransactionManager,
            @Qualifier("oracleTransactionManager") PlatformTransactionManager oracleTransactionManager) {
        return new ChainedTransactionManager(h2TransactionManager,
                oracleTransactionManager);
    }
}
