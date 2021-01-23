package com.ex.smp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
public class SelfTransactionManager {

    private TransactionStatus transactionStatus;

    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 手动开启事务
     * @return
     */
    public TransactionStatus begin(){
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    /**
     * 提交事务
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     */
    public void rollBack(){
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}